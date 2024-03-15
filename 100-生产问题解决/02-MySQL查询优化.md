### 场景
在120W数据量的情况下，select count需要十几秒的时间，且只有一个索引生效，其他联合索引失效。

### 优化方案
使用force index，强制使用索引
```sql
select count(1) from table_name force index where ..;
```
