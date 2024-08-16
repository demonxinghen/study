### 下载zip包
https://archive.apache.org/dist/rocketmq/4.9.1/rocketmq-all-4.9.1-bin-release.zip

### 解压至D盘
解压完后，目录为D:\rocketmq-all-4.9.1-bin-release

### 配置环境变量
ROCKETMQ_HOME=D:\rocketmq-all-4.9.1-bin-release

Path增加%ROCKETMQ_HOME%\bin

### 启动name server
```shell
mqnamesrv.cmd
```

### 启动broker
```shell
mqbroker.cmd -n localhost:9876
```

### 创建topic
```shell
mqadmin.cmd updateTopic -c DefaultCluster -n localhost:9876 -t test-topic
```

### 查看topic
```shell
mqadmin.cmd topicStatus -n localhost:9876 -t test-topic
```

### 创建消费组
```shell
mqadmin.cmd updateSubGroup -n localhost:9876 -c DefaultCluster -g test-consumer
```

### 发送消息
```shell
mqadmin.cmd sendMessage -n localhost:9876 -t test-topic -p "Hello World"
```

### 消费消息
```shell
mqadmin consumeMessage -n localhost:9876 -t test-topic -g test-consumer
```

### 问题
#### 由于clientId重复导致的问题
场景1:
```text
两个namesrv，每个namesrv分别对应一个broker，在同一个jvm中，创建两个DefaultProducer("生产组"),生产组名称不同，在namesrv1创建topic1,namesrv2创建topic2，两个DefaultProducer分别发送topic1和topic2的消息，会有一个提示topic不存在，原因是同一个jvm，生成的clientId是一样的，就会指向同一个MQClientInstance,MQClientInstance只会有一个namesrv，因此会出现这个问题。
```
场景2:
```text
两个namesrv，每个namesrv分别对应一个broker，在同一台机器，不同jvm中，也会出现和上面一样的问题。
```
原因在于clientId组成clientIP@instanceName@UnitName,但是instanceName不建议修改，所以修改unitName即可，比如修改为集群名称。
```java
public static void main(String[] args) throws MQClientException {
    DefaultMQProducer producer2 = new DefaultMQProducer("test-producer-group2");
    producer2.setNamesrvAddr("127.0.0.1:9876");
    producer2.setUnitName("DefaultCluster2");
    producer2.start();
}
```