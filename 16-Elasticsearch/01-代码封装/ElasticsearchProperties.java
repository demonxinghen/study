package com.catkissfish.factory.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "xxx.elasticsearch")
public class ElasticsearchProperties {

    String schema;

    String address;

    Integer connectTimeout;

    Integer socketTimeout;

    Integer connectionRequestTimeout;

    Integer maxConnectNum;

    Integer maxConnectPerRoute;

    String username;

    String password;
}
