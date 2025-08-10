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

9.配置add server
```text
首先是这个Connect to Server,它要求你一定要输入https请求,输入http请求会直接报错,所以一定要输入步骤7的公网url,然后他需要你输入一个端口

在你输入公网url,端口会自动变成8920,但是我始终连接不上,但是我尝试直接访问那个url,他就直接打开了emby主页,说明那个端口可以不需要,结合https默认端口是443,所以我就把端口改成了443,然后就可以连接上了

还有一点就是隧道配置很重要,你看他转发的本地地址是不是https://localhost:8920,一定要是https.

由于我们本地的两个端口,所以转发的最终地址应该是https://localhost:8920或者http://localhost:8096,最后面直接给出可用的tunnel配置
```
![img.png](Connect%20to%20Server.png)

![img.png](报错.png)

### 转发到http://localhost:8096的配置
```yaml
tunnels:
    emby:
        id: 92bd6b00-7ef0-498d-af8c-24fa01767ec5
        proto: http
        addr: "8096"
        inspect: "false"
        bind_tls: both
        disable_keep_alives: "false"
        redirect_https: "true"
        start_type: enable
```
### 转发到https://localhost:8920的配置(区别就是addr后面直接输入完整的链接)
```yaml
tunnels:
  emby:
    id: 92bd6b00-7ef0-498d-af8c-24fa01767ec5
    proto: http
    addr: https://localhost:8920
    inspect: "false"
    bind_tls: both
    disable_keep_alives: "false"
    redirect_https: "true"
    start_type: enable
```