1.安装openssl,下载exe或者直接下载本目录下3.5.2的版本
```text
https://slproweb.com/products/Win32OpenSSL.html
```
2.生成ssl证书,打开cmd
```shell
openssl req -newkey rsa:2048 -nodes -keyout emby.key -x509 -days 365000 -out emby.cer
openssl pkcs12 -export -in emby.cer -inkey emby.key -out emby.pfx
```
3.安装emby,网络中配置上步骤2的emby.pfx,重启emby.server,命令行netstat -ano | findstr 8920确保端口正常启动

4.配置媒体,如果需要编辑音乐的标签,可以使用目录下的mp3tagv330-x64-setup.exe

5.安装cpolar,cpolar-stable-windows-amd64-setup.zip内网穿透工具

6.注册cpolar,token写入配置文件
```shell
./cpolar authtoken YOUR_TOKEN
```
7.登录本地的cpolar web ui,创建隧道,端口选择8920(emby的https端口)

8.登录官网https://app.emby.media/,绑定步骤7生成的公网url
