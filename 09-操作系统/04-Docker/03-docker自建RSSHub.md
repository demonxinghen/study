### 拉取镜像
```shell
docker pull diygod/rsshub
docker run -d --name rsshub -p 1200:1200 --restart=always diygod/rsshub
```

### 文件拷贝
docker cp <容器id>:path 本机path 从容器拷贝到本机

docker cp 本机path <容器id>:path 从本机拷贝到容器
```shell
docker cp rsshub:/app ~/ 
```

### 映射后重新启动docker
```shell
docker run -d --name rsshub -p 1200:1200 --restart=always -v ~/app:/app diygod/rsshub
```

### 后续更新后只需要将映射文件夹替换，重启即可

### 以上方式失败，采用自行拉取git项目
```shell
yum install -y git
git clone https://github.com/DIYgod/RSSHub.git
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.0/install.sh | bash
# 重启terminal
nvm install 20
npm install -g pnpm
# 重启terminal
pnpm i
# 如果项目中使用了puppeteer，则需要执行接下去的命令，否则直接执行最后一步
npx puppeteer browsers install chrome
yum install -y libatk-1.0.so.0
# 最后一步
pnpm run dev
```