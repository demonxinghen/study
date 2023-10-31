package com.catkissfish.factory.utils;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ElasticsearchUtils {

    @Resource
    private ElasticsearchClient elasticsearchClient;

    public <U> void insert(U u, ElasticsearchCondition<U> elasticsearchCondition) {
        String indexName = elasticsearchCondition.getIndexName();
        String id = getIdFromBean(u);
        log.error("执行保存操作,index为{},id为{}", indexName, id);

        IndexResponse response;
        try {
            response = elasticsearchClient.index(i -> i.index(indexName).id(id).document(u));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response != null) {
            log.error("响应结果为{}", toJSONString(response));
        }
    }

    public <U> U queryById(String id, ElasticsearchCondition<U> elasticsearchCondition) {
        String indexName = elasticsearchCondition.getIndexName();
        log.error("执行查询操作,index为{},id为{}", indexName, id);

        GetResponse<U> response;
        try {
            response = elasticsearchClient.get(i -> i.index(indexName).id(id), elasticsearchCondition.getClazz());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response.found()) {
            return response.source();
        }
        log.error("查询id为{}的{}对象不存在", id, indexName);
        return null;
    }

    public <U> void updateById(U u, ElasticsearchCondition<U> elasticsearchCondition) {
        String indexName = elasticsearchCondition.getIndexName();
        String id = getIdFromBean(u);
        log.error("执行更新操作,index为{},id为{}", indexName, id);

        UpdateResponse<U> response;
        try {
            response = elasticsearchClient.update(i -> i.index(indexName).id(id).upsert(u), elasticsearchCondition.getClazz());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.error("更新结束,更新结果为{}", toJSONString(response));
    }

    public <U> void deleteById(U u, ElasticsearchCondition<U> elasticsearchCondition) {
        String indexName = elasticsearchCondition.getIndexName();
        String id = getIdFromBean(u);
        log.error("执行删除操作,index为{},id为{}", indexName, id);

        DeleteResponse response;
        try {
            response = elasticsearchClient.delete(i -> i.index(indexName).id(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.error("删除结束,删除结果为{}", toJSONString(response));
    }

    public <U> IPage<U> queryPage(ElasticsearchCondition<U> elasticSearchCondition) {
        try {
            SearchRequest.Builder builder = elasticSearchCondition.getBuilder();
            builder.query(elasticSearchCondition.getBool().build()._toQuery());
            SearchResponse<U> search = elasticsearchClient.search(builder.build(), elasticSearchCondition.getClazz());
            IPage<U> page = new Page<>();
            List<Hit<U>> records = search.hits().hits();
            if (search.hits().total() != null
                    && search.hits().total().value() != 0 && CollectionUtils.isNotEmpty(records)) {
                page.setRecords(records.stream().map(Hit::source).collect(Collectors.toList()));
                long total = search.hits().total().value();
                page.setTotal(total);
                page.setPages(total / elasticSearchCondition.getPageSize() + 1);
            } else {
                page.setRecords(new ArrayList<>());
                page.setTotal(0);
                page.setPages(0);
            }
            if (elasticSearchCondition.getPageSize() != null && elasticSearchCondition.getPageNum() != null) {
                page.setSize(elasticSearchCondition.getPageSize());
                page.setCurrent(elasticSearchCondition.getPageNum());
            }
            log.error("查询所有数据{}", toJSONString(records));
            return page;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getIdFromBean(Object object) {
        String id;
        try {
            id = BeanUtils.getProperty(object, "id");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    private String toJSONString(Object object) {
        return new Gson().toJson(object);
    }
}
