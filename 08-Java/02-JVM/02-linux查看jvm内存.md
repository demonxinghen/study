### 查看jvm进程
```shell
jps
```

### 查看对应进程的GC使用情况
```shell
jstat -gc pid
```
```text
S0C    S1C    S0U    S1U      EC       EU        OC         OU       PC    PU     YGC     YGCT    FGC    FGCT     GCT
S0C：年轻代中第一个survivor（幸存区）的容量 (字节) 
S1C：年轻代中第二个survivor（幸存区）的容量 (字节) 
S0U：年轻代中第一个survivor（幸存区）目前已使用空间 (字节) 
S1U：年轻代中第二个survivor（幸存区）目前已使用空间 (字节) 
EC：年轻代中Eden（伊甸园）的容量 (字节) 
EU：年轻代中Eden（伊甸园）目前已使用空间 (字节) 
OC：Old代的容量 (字节) 
OU：Old代目前已使用空间 (字节)
YGC：从应用程序启动到采样时年轻代中gc次数 
YGCT：从应用程序启动到采样时年轻代中gc所用时间(s) 
FGC：从应用程序启动到采样时old代(全gc)gc次数 
FGCT：从应用程序启动到采样时old代(全gc)gc所用时间(s) 
GCT：从应用程序启动到采样时gc用的总时间(s) 
```
### 查看堆内存使用情况
```shell
jmap -heap pid
```
```text
Heap Configuration:   #堆配置情况 
   MinHeapFreeRatio         = 40  #堆最小使用比例
   MaxHeapFreeRatio         = 70  #堆最大使用比例
   MaxHeapSize              = 8589934592 (8192.0MB)  #堆最大空间
   NewSize                  = 1363144 (1.2999954223632812MB) #新生代初始化大小
   MaxNewSize               = 5152702464 (4914.0MB)          #新生代可使用最大容量大小
   OldSize                  = 5452592 (5.1999969482421875MB) #老生代大小
   NewRatio                 = 2   #新生代比例
   SurvivorRatio            = 8   #新生代与suvivor的占比
   MetaspaceSize            = 21807104 (20.796875MB) #元数据空间初始大小
   CompressedClassSpaceSize = 1073741824 (1024.0MB) #类指针压缩空间大小, 默认为1G
   MaxMetaspaceSize         = 17592186044415 MB  #元数据空间的最大值, 超过此值就会触发 GC溢出( JVM会动态地改变此值)
   G1HeapRegionSize         = 2097152 (2.0MB) #区块的大小

Heap Usage:
G1 Heap:
   regions  = 4096  # G1区块初始化大小
   capacity = 8589934592 (8192.0MB)  #G1区块最大可使用大小
   used     = 1557972768 (1485.7986145019531MB)  #G1区块已使用内存
   free     = 7031961824 (6706.201385498047MB)   #G1区块空闲内存
   18.137190118432045% used     #G1区块使用比例
G1 Young Generation:  #新生代
Eden Space:  #Eden区空间
   regions  = 670
   capacity = 2699034624 (2574.0MB)
   used     = 1405091840 (1340.0MB)
   free     = 1293942784 (1234.0MB)
   52.05905205905206% used
Survivor Space: #Survivor区
   regions  = 3
   capacity = 6291456 (6.0MB)
   used     = 6291456 (6.0MB)
   free     = 0 (0.0MB)
   100.0% used
G1 Old Generation: #老生代
   regions  = 72
   capacity = 1589641216 (1516.0MB)
   used     = 146589472 (139.79861450195312MB)
   free     = 1443051744 (1376.2013854980469MB)
   9.221544492213267% used
```
