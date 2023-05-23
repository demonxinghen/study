package com.example.dao;

/**
 * @author: xuh
 * @date: 2023/5/23 10:04
 * @description:
 */
public class ServiceImpl {

    private DataSourceDto dataSourceDto;

    public void setDataSourceDto(DataSourceDto dataSourceDto){
        this.dataSourceDto = dataSourceDto;
    }

    public DataSourceDto getDataSourceDto() {
        return dataSourceDto;
    }
}
