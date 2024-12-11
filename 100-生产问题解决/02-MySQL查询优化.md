### 场景
在120W数据量的情况下，select count需要十几秒的时间，且只有一个索引生效，其他联合索引失效。

### 优化方案
使用force index，强制使用索引
```sql
select count(1) from table_name force index where ..;
```

查询mysql锁表

SELECT * FROM INFORMATION_SCHEMA.INNODB_TRX;

杀死锁的线程, id就是上述查询结果的 trx_mysql_thread_id

kill id

或者查询 SELECT * FROM information_schema.processlist where command != 'daemon' and command != 'sleep' ORDER BY TIME DESC;

该sql查询结果和 show full processlist; 但是支持按时间排序， 返回的id也就是上述查询结果的 trx_mysql_thread_id