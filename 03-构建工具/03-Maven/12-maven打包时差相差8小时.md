### 1.ʹ��maven�Դ�������
����ʱ�����ʽ����pom.xml�ļ��м�����������

```xml
<properties>
<maven.build.timestamp.format>yyyyMMddHHmmss</maven.build.timestamp.format>
</properties>
```
�ڴ��plugin�����ø�����

```xml
<finalName>
  ${project.artifactId}-${project.version}_${maven.build.timestamp}
</finalName>
```
Maven�Դ�ʱ���ʹ��${maven.build.timestamp}������ʱ����UTC�� 
���Ҫʹ��GMT+8������Ҫ����ṩ֧�֣����������������ʵ�֡�

### 2.ʹ��buildnubmer-maven-plugin
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
Ĭ��������timestamp���ڴ��plugin�����ø�����
```xml
<finalName>
    ${project.artifactId}-${project.version}_${timestamp}
</finalName>
```

### 3.ʹ��build-helper-maven-plugin(�Ƽ�)
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