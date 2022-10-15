### 仓库布局
存储路径path是如何生成的
```text
1.将groupId的.替换为/,末尾追加/
2.追加artifactId,末尾追加/
3.追加baseVersion,末尾追加/  什么是baseVersion,比如version是1.0-SNAPSHOT,则baseVersion就是1.0,这也就是我们能看见同一版本的SNAPSHOT都在同一目录的原因
4.追加artifactId,追加.,追加版本号
5.如果classifier不为空,追加-,追加classifier
6.追加.,追加扩展名,也就是packaging中的配置,默认为jar
```

### 仓库分类
1.本地仓库

2.远程仓库
* 中央仓库
* 私服
* 其他公共库

### 默认仓库配置
maven安装目录/lib有个maven-model-builder-XX.jar,解压该jar,进入目录org/apache/maven/model,有个pom-XXX.xml,内有这么一段配置
```xml
  <repositories>
    <repository>
      <id>central</id>
      <name>Central Repository</name>
      <url>https://repo.maven.apache.org/maven2</url>
      <layout>default</layout>
      <snapshots>
        <enabled>false</enabled><!-- 表示不下载SNAPSHOT版本 -->
      </snapshots>
    </repository>
  </repositories>
```
就是在这里配置了maven的默认仓库,也是所有maven项目要继承的顶级pom.xml.

同样也包含了插件的默认仓库
```xml
  <pluginRepositories>
    <pluginRepository>
      <id>central</id>
      <name>Central Repository</name>
      <url>https://repo.maven.apache.org/maven2</url>
      <layout>default</layout>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <releases>
        <updatePolicy>never</updatePolicy>
      </releases>
    </pluginRepository>
  </pluginRepositories>
```
### 构建私服的理由
* 节省自己的外网带宽(重复构件无需下载)
* 加速maven构建
* 部署第三方构建
* 提高稳定性,增强控制
* 降低中央仓库的负荷
建议个人电脑也应在本地建立私服

### 远程仓库的配置
配置在pom.xml中
```xml
<repositories> <!-- 可以配置多个repository -->
    <repository>
        <id>jboss</id> <!-- id必须唯一,默认仓库的id是central,如果此处配置为central,则会覆盖掉中央仓库的配置 -->
        <name>JBoss repository</name>
        <url>http://repository.jboss.com/maven2/</url>
        <releases>
            <enabled>true</enabled>
            <updatePolicy>daily</updatePolicy> <!-- maven从远程仓库检查更新的频率,默认daily,其他值never,always每次构建都检查,interVal:x每隔x分钟检查一次,x为整数 -->
            <checksumPolicy>ignore</checksumPolicy> <!-- 检查校验和文件的策略.当构件被部署到maven仓库时,同时会部署校验和文件,在下载的时候maven会验证,如果验证失败,则根据该值处理.warn警告,fail构建失败,ignore忽略 -->
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <layout>default</layout> <!-- 表示仓库的布局是maven2和maven3的默认布局, 如果是maven1, 则需要配置为legacy -->
    </repository>
</repositories>
```

#### 远程仓库的认证配置
管理员可以为每个仓库配置一组用户名和密码,此时,访问仓库的时候,就需要配置认证信息.

认证信息必须配置在settings.xml中,因为pom.xml一般会提交到代码仓库供所有人访问,而settings.xml只在本机中.

```xml
<settings>
    <servers>
        <server>
            <id>jboss</id> <!-- 此处id必须和上面repository配置的id保持一致 -->
            <username>123</username>
            <password>456</password>
        </server>
    </servers>
</settings>
```

#### 部署至远程仓库
配置在pom.xml中
```xml
<distributionManagement>
    <repository>
        <id>project-releases</id> <!-- 远程仓库的唯一标识 -->
        <name>Project Release Repository</name>
        <url>http://192.168.0.1/content/repositories/project-releases</url>
    </repository>
    <snapshotRepository>
        <id>project-snapshots</id>
        <name>Project Snapshot Repository</name>
        <url>http://192.168.0.1/content/repositories/project-snapshots</url>
    </snapshotRepository>
</distributionManagement>
```
配置成功后,执行mvn clean deploy即可部署到对应的远程仓库.
### 快照版本
为什么需要快照版本,比如两个项目A和B,A依赖B,在联调测试时,B不停地变动,不停地部署,在不改变版本号的情况下,A由于第一次已经下载过对应的版本,maven就不会对远程仓库进行更新,除非手动删除本地仓库.快照版本就是为了解决这个问题.

在将构建的version设置为-SNAPSHOT结尾,部署到仓库时,maven会自动为构建打上时间戳.

依赖方构建项目时可以在不改动依赖版本的情况下下载最新的快照.
* 依据updatePolicy的值,maven会自动下载
* 执行mvn clean install -U 强制检查更新

### 仓库解析依赖的机制
1. 当scope是system的时候, maven直接从本地文件系统中解析构建.
2. 否则根据构件坐标,尝试中本地仓库中寻找,如果找到,则构建成功.
3. 本地没找到,如果版本是显式指定的话,则遍历所有的远程仓库,下载并解析使用.
4. 如果是以RELEASE或LATEST结尾的版本号,则基于updatePolicy读取所有远程仓库的元数据groupId/artifactId/maven-metadata.xml,将其与本地的对应元数据合并后,计算出真实的版本号,然后基于这个版本号检查本地和远程仓库,如步骤2,3.
5. 如果依赖的版本号是SNAPSHOT,则基于updatePolicy读取所有远程仓库的元数据groupId/artifactId/version/maven-metadata.xml,将其与本地的对应元数据合并后,计算出对应的快照版本值,然后基于该值检查本地和远程仓库.
6. 如果解析得到的版本号是时间戳格式的快照,如1.4-20091104.121450-121,则复制其时间戳格式的文件至非时间戳格式,如SNAPSHOT,并使用该非时间戳格式的构件.
```text
构件坐标由什么组成:
1.groupId
2.artifactId
3.version
4.packaging
5.classifier
```
groupId/artifactId/maven-metadata.xml示例
```xml
<!-- 此示例2022-10-15 12:14下载自https://repo1.maven.org/maven2/org/apache/commons/commons-lang3/maven-metadata.xml -->
<metadata>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <versioning>
        <latest>3.12.0</latest> <!-- 这两行可以得出latest和release对应的版本号,再查看本地有没有3.12.0,没有,则从远程下载 -->
        <release>3.12.0</release>
        <versions>
            <version>3.0</version>
            <version>3.0.1</version>
            <version>3.1</version>
            <version>3.2</version>
            <version>3.2.1</version>
            <version>3.3</version>
            <version>3.3.1</version>
            <version>3.3.2</version>
            <version>3.4</version>
            <version>3.5</version>
            <version>3.6</version>
            <version>3.7</version>
            <version>3.8</version>
            <version>3.8.1</version>
            <version>3.9</version>
            <version>3.10</version>
            <version>3.11</version>
            <version>3.12.0</version>
        </versions>
        <lastUpdated>20210301214036</lastUpdated>
    </versioning>
</metadata>
```
groupId/artifactId/version/maven-metadata.xml示例
```xml
<!-- 此示例模拟的,不真实存在 -->
<metadata>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>1.1.0-SNAPSHOT</version>
    <versioning>
        <snapshot>
            <timestamp>20221015.195145</timestamp>
            <buildNumber>120</buildNumber> <!-- 此示例中的版本号应为1.1.0-20221015.195145-120 -->
        </snapshot>
        <lastUpdated>20221015195145</lastUpdated>
    </versioning>
</metadata>
```

### 镜像
如果仓库A可以提供仓库B中所有的构件,那么就可以说A是B的一个镜像.

比如,https://maven.aliyun.com/repository/central是中央仓库https://repo1.maven.org/maven2/的阿里镜像,我们使用阿里镜像可以更快地下载,因此我们可以配置镜像来替代中央仓库.

tips:
```text
关于中央仓库地址,前面有提到中央仓库地址是https://repo.maven.apache.org/maven2, 这里提到的地址是https://repo1.maven.org/maven2/.
经查询资料,maven3.0.3(包含3.0.3)以前默认中央仓库地址都是https://repo1.maven.org/maven2/,3.0.4(包含3.0.4)以后改为了https://repo.maven.apache.org/maven2. 本人未验证.

阿里常用的镜像地址是https://maven.aliyun.com/repository/public,而不是上面提到的https://maven.aliyun.com/repository/central, 因为前者不止包括了后者,还包括了源地址为http://jcenter.bintray.com/的jcenter仓库.
```
镜像的配置
```xml
<mirrors>
    <mirror>
        <id>ali</id>
        <name>阿里仓库地址</name>
        <url>https://maven.aliyun.com/repository/public/</url>
        <mirrorOf>central</mirrorOf> <!-- 该配置是重点,与仓库id对应,此处配置central表示对于中央仓库的请求会转到该镜像,如果镜像需要认证的话,也可以在server中配置,同样id要对应上 -->
    </mirror>
</mirrors>
```
mirrorOf的配置
```text
1.与仓库id保持一致
2.通配符*,表示匹配所有远程仓库
3.external:*,匹配所有远程仓库,使用localhost的除外,使用file://协议的除外
4.repo1,repo2 同时匹配多个仓库,以逗号分隔
5.*,!repo1 匹配所有远程仓库,repo1除外
6.匹配顺序:自上而下,所以如果有配置*的,要放在最后
```

### 仓库搜索服务
* Sonatype Nexus: https://repository.sonatype.org
* 不知道和上面那个有没有关系: https://central.sonatype.dev
* mvnrepository: https://mvnrepository.com/