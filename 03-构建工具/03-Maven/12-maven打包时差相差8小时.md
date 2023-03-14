### 1.使用maven自带的属性
设置时间戳格式：在pom.xml文件中加入以下配置

```xml
<properties>
<maven.build.timestamp.format>yyyyMMddHHmmss</maven.build.timestamp.format>
</properties>
```
在打包plugin中引用该属性

```xml
<finalName>
  ${project.artifactId}-${project.version}_${maven.build.timestamp}
</finalName>
```
Maven自带时间戳使用${maven.build.timestamp}，但是时区是UTC。 
如果要使用GMT+8，就需要插件提供支持，以下两个插件可以实现。

### 2.使用buildnubmer-maven-plugin
```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>buildnumber-maven-plugin</artifactId>
    <version>1.4</version>
    <configuration>
        <timestampFormat>yyyyMMdd</timestampFormat>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>create-timestamp</goal>
            </goals>
        </execution>
    </executions>
    <inherited>false</inherited>
</plugin>
```
默认属性是timestamp，在打包plugin中引用该属性
```xml
<finalName>
    ${project.artifactId}-${project.version}_${timestamp}
</finalName>
```

### 3.使用build-helper-maven-plugin(推荐)
```xml
<build>
    <finalName>${project.name}-${project.version}-${current.time}</finalName>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>build-helper-maven-plugin</artifactId>
            <executions>
                <execution>
                    <id>timestamp-property</id>
                    <goals>
                        <goal>timestamp-property</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <name>current.time</name>
                <pattern>yyyyMMdd-HHmmss</pattern>
                <timeZone>GMT+8</timeZone>
            </configuration>
        </plugin>
    </plugins>
</build>
```