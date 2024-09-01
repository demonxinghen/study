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
# 见下面的text内容

# 最后一步
pnpm run dev

# 后台运行则使用
nohup pnpm run dev </dev/null &

# 直接使用nohup pnpm run dev &会报错，nohup会将进程与终端分离，这有时会导致Node.js的标准输入、输出或错误流出现问题。
```
https://pptr.dev/troubleshooting#could-not-find-expected-browser-locally 解决使用puppeteer的问题

```text
由于使用puppeteer需要依赖chrome,但是chrome在Linux上没有提供arm64的包,只有mac arm的arm64包.也就意味着默认下载的chrome中有许多包是无法生效的.
下载chrome
npx puppeteer browsers install chrome
找到对应的chrome安装地址,比如是在/root/RSSHub/node_modules/puppeteer_cache/chrome/linux-123.0.6312.86/chrome-linux64/chrome
查看chrome运行缺失的依赖 ldd /root/RSSHub/node_modules/puppeteer_cache/chrome/linux-123.0.6312.86/chrome-linux64/chrome | grep not
将对应的依赖都下载一遍
```
Debian可能缺失的依赖有:
```text
ca-certificates
fonts-liberation
libasound2
libatk-bridge2.0-0
libatk1.0-0
libc6
libcairo2
libcups2
libdbus-1-3
libexpat1
libfontconfig1
libgbm1
libgcc1
libglib2.0-0
libgtk-3-0
libnspr4
libnss3
libpango-1.0-0
libpangocairo-1.0-0
libstdc++6
libx11-6
libx11-xcb1
libxcb1
libxcomposite1
libxcursor1
libxdamage1
libxext6
libxfixes3
libxi6
libxrandr2
libxrender1
libxss1
libxtst6
lsb-release
wget
xdg-utils
```

Centos可能缺失的依赖有:
```text
alsa-lib.x86_64
atk.x86_64
cups-libs.x86_64
gtk3.x86_64
ipa-gothic-fonts
libXcomposite.x86_64
libXcursor.x86_64
libXdamage.x86_64
libXext.x86_64
libXi.x86_64
libXrandr.x86_64
libXScrnSaver.x86_64
libXtst.x86_64
pango.x86_64
xorg-x11-fonts-100dpi
xorg-x11-fonts-75dpi
xorg-x11-fonts-cyrillic
xorg-x11-fonts-misc
xorg-x11-fonts-Type1
xorg-x11-utils
```
项目根目录下会有个文件.puppeteerrc.cjs，一般不用改，如果要改就改成
```shell
const path = require('path');

/**
 * @type {import("puppeteer").Configuration}
 */
module.exports = {
    // Changes the cache location for Puppeteer.
    cacheDirectory: path.join(__dirname, 'node_modules', 'puppeteer_cache'),
};
```