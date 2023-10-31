package com.catkissfish.factory.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.google.common.base.Splitter;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ElasticsearchConfig {

    @Bean
    public ElasticsearchProperties elasticSearchProperties(){
        return new ElasticsearchProperties();
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(){
        ElasticsearchProperties properties = elasticSearchProperties();
        List<String> addresses = Splitter.on(";").splitToList(properties.getAddress());
        HttpHost[] hosts = new HttpHost[addresses.size()];
        for (int i = 0; i < addresses.size(); i++) {
            String address = addresses.get(i);
            List<String> host = Splitter.on(":").splitToList(address);
            hosts[i] = new HttpHost(host.get(0), Integer.parseInt(host.get(1)), properties.getSchema());
        }
        RestClientBuilder client = RestClient.builder(hosts);
        client.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(properties.getConnectTimeout());
            requestConfigBuilder.setSocketTimeout(properties.getSocketTimeout());
            requestConfigBuilder.setConnectionRequestTimeout(properties.getConnectionRequestTimeout());
            return requestConfigBuilder;
        });
        // 配置密码
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
        client.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(properties.getMaxConnectNum());
            httpClientBuilder.setMaxConnPerRoute(properties.getMaxConnectPerRoute());
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return httpClientBuilder;
        });
        RestClientTransport transport = new RestClientTransport(client.build(), new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

}
