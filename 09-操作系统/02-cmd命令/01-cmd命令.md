#### 1.查看端口占用
```shell
netstat -ano | findstr {port}
```
#### 2.查看进程id对应的进程信息
```shell
tasklist | findstr {pid}
```
#### 3.强杀进程
```shell
taskkill /f /pid {pid}
```