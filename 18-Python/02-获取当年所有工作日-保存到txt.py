import requests
import json
import pandas as pd
from datetime import datetime
import numpy as np

# 从百度的php接口中获取到数据
def catch_url_from_baidu(calcultaion_year, month):
    headers = {
        "Content-Type": "application/json;charset=UTF-8"
    }
    param = {
        "query": calcultaion_year + "年" + month + "月",
        "resource_id": "39043",
        "t": "1604395059555",
        "ie": "utf8",
        "oe": "gbk",
        "format": "json",
        "tn": "wisetpl",
        "cb": ""
    }
    # 抓取位置：百度搜索框搜索日历，上面的日历的接口，可以在页面上进行核对
    r = requests.get(url="https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php",
                     headers=headers, params=param).text
    month_data = json.loads(r)["data"][0]["almanac"]
    not_work_day = []  #非工作日
    work_day = [] #工作日
    for one in month_data:
        #工作日
        if ('status' not  in one ):
            if(one["cnDay"] != '日' and one["cnDay"] != '六'):
                #正常工作日
                work_day.append(one)
        if(one["cnDay"] == '日' or one["cnDay"] == '六'):
            if ('status' in one):
                # status为2的时候表示周末的工作日，比如10月10日。即百度工具左上角显示“班”的日期
                if (one["status"] == "2"):
                    work_day.append(one)

    return export_excel(work_day)

#日期格式规范
def addZero(num):
    if int(num) < 10:
        return str( '0' + num)
    else:
        return str(num)

def export_excel(work_day):
    date_list = []
    for one in work_day:
        month = addZero(one['month'])
        day = addZero(one['day'])
        date_list.append(f"{one['year']}-{month}-{day}")
    return date_list

#获取每个月的工作日
def asMonth(date_list):
    month_list = [1,2,3,4,5,6,7,8,9,10,11,12] #月份
    results_list = []
    for m in month_list:
        result_list = []
        j_dates = [date for date in date_list if datetime.strptime(date, '%Y-%m-%d').month == m]
        if len(j_dates)>0:
            result_list.append(str(m)+'月')
            result_list.append(j_dates)
            results_list.append(result_list)
    # print(results_list)
    return results_list

#统一长度
def sameLen(lst):
    new_lst = [np.nan] * 30
    new_lst[:len(lst)] = lst
    return new_lst

if __name__ == '__main__':
    # 国务院是每年12月份才会发布第二年的放假计划
    calculation_year = "2024"
    # 因该接口传入的时间，查询了前一个月，当前月和后一个月的数据，所以只需要2、5、8、11即可全部获取到。比如查询5月份，则会查询4,5,6月分的数据
    calculation_month = ["2", "5", "8", "11"]
    # calculation_month = ["2"]
    all_dates = []
    for one_month in calculation_month:
        e = catch_url_from_baidu(calculation_year, one_month)
        all_dates.extend(e)  # 将每个月的日期添加到总列表中

    # 将所有日期写入TXT文件，每个日期占一行
    with open(r'{}年工作日.txt'.format(calculation_year), 'w') as file:
        for date in all_dates:
            file.write(date + '\n')
