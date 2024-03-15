### 查询mysql版本
```shell
docker search mysql
```
### 获取镜像的版本
```shell
# docker.hub.com源
curl -s "https://hub.docker.com/v2/repositories/library/mysql/tags/" | jq '.results[].name'
```
### 拉取mysql镜像
```shell
docker pull mysql:5.7
```