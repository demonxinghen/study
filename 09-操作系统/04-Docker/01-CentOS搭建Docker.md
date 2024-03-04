### 更新yum包
```shell
sudo yum -y update
```
sudo yum -y update和sudo yum -y upgrade的区别
- update：升级所有包，同时也升级软件和系统内核
- upgrade：升级所有包，不升级软件和系统内核
### 卸载docker旧版本
```shell
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```
### 安装方式
- 使用官方安装脚本自动安装
```shell
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
```
- 使用yum安装
1. 安装需要的软件包
```shell
sudo yum install -y yum-utils \
  device-mapper-persistent-data \
  lvm2
```
2. 设置yum源
```shell
# 中央仓库
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
    
# 阿里仓库
sudo yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```
3. 选择docker版本
```shell
yum list docker-ce --showduplicates | sort -r
```
4. 安装docker
```shell
sudo yum install docker-ce-<VERSION_STRING> docker-ce-cli-<VERSION_STRING> containerd.io
```
5. 启动docker并设置开机自启
```shell
sudo systemctl start docker
sudo systemctl enable docker
```