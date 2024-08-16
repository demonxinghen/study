### GC overhead limit exceeded
在垃圾回收过程中，由于某种原因导致垃圾回收花费的时间超过了预期，从而使得应用程序的性能下降，这种错误通常是由于垃圾回收器的效率较低或者应用程序存在内存泄漏等问题导致的。

原因：
```text
1. 内存泄漏
2. 垃圾回收器效率过低
3. 堆内存分配不足
4. 高内存消耗的数据结构
```

Sun公司规定，当垃圾回收器花费的时间超过98%时，而只得到2%的内存回收，则认为垃圾回收器效率过低，这时会抛出OutOfMemoryError异常。

会导致CPU飙升至100%,然后服务卡死。

解决方案：
```text
1. 调整垃圾回收器参数
2. 增大堆内存大小
3. 优化垃圾回收器，尝试使用不同的垃圾回收器
4. 优化代码，修复内存泄漏：使用VisUalVM、MAT对应用程序进行内存分析
5. 分析堆转储文件，当应用程序发生GC Overhead Limit Exceeded时，会自动生成堆转储文件，找出导致内存泄露的代码（需要启动配置参数-XX:+HeapDumpOnOutOfMemoryError）。或者手动生成jmap -dump:format=b,file=<filename.hprof> <pid>
```

获取堆转储文件：
```text
方式1: jmap -dump:live,format=b,file=/app/logs/heapdump.hprof <PID>
方式2: 启动命令里配置参数-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/app/logs/heapdump.hprof
```