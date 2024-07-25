SELECT
table_name as '数据库',
table_rows as '记录数',
truncate(data_length/1024/1024/1024, 2) as '数据容量(GB)',
truncate(index_length/1024/1024/1024, 2) as '索引容量(GB)',
truncate(DATA_FREE/1024/1024, 2) as '碎片占用(MB)'
from information_schema.tables
where table_schema='catkissfish_factory_system'
order by table_rows desc, data_length desc, index_length desc;