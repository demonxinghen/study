import requests
import json
import pandas as pd
from datetime import datetime
import numpy as np


# 从百度的php接口中获取到数据
def catch_url_from_baidu(year, month):
    headers = {
        "Content-Type": "application/json;charset=UTF-8"
    }
    param = {
        "query": year + "年" + month + "月",
        "resource_id": "52109",
        "t": "1604395059555",
        "ie": "utf8",
        "apiType": "yearMonthData",
        "type": "json",
        "tn": "reserved_all_res_tn",
        "cb": ""
    }
    # 抓取位置：百度搜索框搜索日历，上面的日历的接口，可以在页面上进行核对
    r = requests.get(url="https://opendata.baidu.com/data/inner",
                     headers=headers, params=param).text
    month_data = json.loads(r)["Result"][0]["DisplayData"]["resultData"]["tplData"]["data"]["almanac"]
    work_day = []  # 工作日
    for one in month_data:
        # 工作日
        if 'status' not in one:
            if one["cnDay"] != '日' and one["cnDay"] != '六':
                # 正常工作日
                work_day.append(one)
        if one["cnDay"] == '日' or one["cnDay"] == '六':
            if 'status' in one:
                # status为2的时候表示周末的工作日，比如10月10日。即百度工具左上角显示“班”的日期
                if one["status"] == "2":
                    work_day.append(one)

    return export_excel(work_day)


# 日期格式规范
def add_zero(num):
    if int(num) < 10:
        return str('0' + num)
    else:
        return str(num)


def export_excel(work_day):
    date_list = []
    for one in work_day:
        month = add_zero(one['month'])
        day = add_zero(one['day'])
        date_list.append(f"{one['year']}-{month}-{day}")
    return date_list


if __name__ == '__main__':
    # 国务院是每年12月份才会发布第二年的放假计划
    calculation_year = "2025"
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
