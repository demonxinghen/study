package com.catkissfish.factory.utils;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.SourceConfig;
import co.elastic.clients.json.JsonData;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.catkissfish.factory.annotation.ElasticsearchIndex;
import com.google.common.collect.Lists;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class ElasticsearchCondition<T> {

    BoolQuery.Builder bool = QueryBuilders.bool();

    Integer pageSize;

    Integer pageNum;

    Integer from;

    private final Class<T> clazz;

    private final String indexName;

    SortOptions.Builder sort = new SortOptions.Builder();

    SourceConfig.Builder filter;

    SearchRequest.Builder builder = new SearchRequest.Builder();

    /**
     * 创建方式必须以匿名内部类的方式 ElasticsearchCondition<OSOrder> elasticsearchCondition = new ElasticsearchCondition<OSOrder>(){}
     */
    public ElasticsearchCondition() {
        Type type = getClass().getGenericSuperclass();
        clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];

        ElasticsearchIndex annotation = clazz.getAnnotation(ElasticsearchIndex.class);
        if (annotation == null || StringUtils.isBlank(annotation.value())) {
            indexName = toCamelCase(clazz.getSimpleName());
        } else {
            indexName = annotation.value();
        }
    }

    public void or(ElasticsearchCondition<T> elasticsearchCondition) {
        bool.should(elasticsearchCondition.getBool().build().should());
    }

    public void and(ElasticsearchCondition<T> elasticsearchCondition) {
        bool.must(elasticsearchCondition.getBool().build().must());
    }

    public void eq(SFunction<T, ?> column, String value) {
        bool.must(QueryBuilders.term(s -> s.field(getColumnName(column) + ".keyword").value(value)));
    }

    public void in(SFunction<T, ?> column, List values) {
        List<FieldValue> list = new ArrayList<>();
        values.forEach(value -> {
            if (value instanceof String) {
                list.add(FieldValue.of((String) value));
            } else if (value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long) {
                list.add(FieldValue.of((long) value));
            } else if (value instanceof Double) {
                list.add(FieldValue.of((double) value));
            } else if (value instanceof Boolean) {
                list.add(FieldValue.of((boolean) value));
            } else {
                list.add(FieldValue.of(String.valueOf(value)));
            }
        });
        bool.must(QueryBuilders.terms(s -> s.field(getColumnName(column)).terms(termsQueryBuilder -> termsQueryBuilder.value(list))));
    }

    public void ne(SFunction<T, ?> column, String value) {
        bool.mustNot(QueryBuilders.term(s -> s.field(getColumnName(column) + ".keyword").value(value)));
    }

    public void eq(SFunction<T, ?> column, long value) {
        bool.must(QueryBuilders.term(s -> s.field(getColumnName(column)).value(value)));
    }

    public void ne(SFunction<T, ?> column, long value) {
        bool.mustNot(QueryBuilders.term(s -> s.field(getColumnName(column)).value(value)));
    }

    public void like(SFunction<T, ?> column, String value) {
        bool.must(QueryBuilders.match(s -> s.field(getColumnName(column)).query(value)));
    }

    public void between(SFunction<T, ?> column, String from, String to) {
        bool.must(QueryBuilders.range(s -> s.field(getColumnName(column)).from(from).to(to)));
    }

    public void gt(SFunction<T, ?> column, String value) {
        bool.must(QueryBuilders.range(s -> s.field(getColumnName(column)).gt(JsonData.of(value))));
    }

    public void ge(SFunction<T, ?> column, String value) {
        bool.must(QueryBuilders.range(s -> s.field(getColumnName(column)).gte(JsonData.of(value))));
    }

    public void lt(SFunction<T, ?> column, String value) {
        bool.must(QueryBuilders.range(s -> s.field(getColumnName(column)).lt(JsonData.of(value))));
    }

    public void le(SFunction<T, ?> column, String value) {
        bool.must(QueryBuilders.range(s -> s.field(getColumnName(column)).lte(JsonData.of(value))));
    }

    private void sort(SFunction<T, ?> column, SortOrder sortOrder) {
        sort.field(fieldBuilder -> fieldBuilder.field(getColumnName(column)).order(sortOrder));
    }

    public void orderByAsc(SFunction<T, ?> column) {
        sort(column, SortOrder.Asc);
    }

    public void orderByDesc(SFunction<T, ?> column) {
        sort(column, SortOrder.Desc);
    }

    @SafeVarargs
    public final void select(SFunction<T, ?>... columns) {
        filter = new SourceConfig.Builder();
        List<String> fields = Lists.newArrayList();
        for (SFunction<T, ?> column : columns) {
            fields.add(getColumnName(column));
        }
        filter.filter(sourceFilterBuilder -> sourceFilterBuilder.includes(fields));
    }

    public void page(int pageNum, int pageSize) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.from = pageSize * (pageNum - 1);
        builder.from(this.from);
        builder.size(this.pageSize);
    }

    private String getColumnName(SFunction<T, ?> column) {
        Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(clazz);
        LambdaMeta meta = LambdaUtils.extract(column);
        String fieldName = PropertyNamer.methodToProperty(meta.getImplMethodName());
        ColumnCache columnCache = columnMap.get(LambdaUtils.formatKey(fieldName));
        Assert.notNull(columnCache, "can not find lambda cache for this property [%s] of entity [%s]",
                fieldName, clazz.getName());
        return columnCache.getColumn();
    }

    private String toCamelCase(String simpleName) {
        if (StringUtils.isBlank(simpleName)) {
            return simpleName;
        }
        char c = simpleName.charAt(0);
        int length = simpleName.length();
        char[] chars = new char[length];
        simpleName.getChars(0, length, chars, 0);
        if (c > 64 && c < 91) {
            chars[0] = (char) (c + 32);
        }
        return new String(chars);
    }
}
