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
- 使用官方安装脚本自动安装（已作废，国内镜像被禁用）
```shell
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
```
- 使用yum安装(可用)
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
sudo yum -y install docker-ce-<VERSION_STRING>

或者

sudo yum install docker-ce docer-ce-cli containerd.io
```
5. 启动docker并设置开机自启
```shell
sudo systemctl start docker
sudo systemctl enable docker
```
6. sudo设置无需密码
```shell
# 查看用户组及成员
sudo cat /etc/group | grep docker
# 没有的话添加docker组
sudo groupadd docker
# 将当前用户添加到docker组
sudo gpasswd -a ${USER} docker
# 增加读写权限
sudo chmod a+rw /var/run/docker.sock
# 重启docker
sudo systemctl restart docker
# 开机自启
sudo systemctl enable docker
```
7. docker配置镜像源
```shell
sudo vim /etc/docker/daemon.json
{
    "registry-mirrors": [
        "https://docker.registry.cyou/",
        "https://docker-cf.registry.cyou/"
    ]
}
# 保存退出
sudo systemctl daemon-reload
sudo systemctl restart docker
```