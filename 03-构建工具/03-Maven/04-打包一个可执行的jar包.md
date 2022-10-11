
默认情况下, mvn clean package打包出来的jar无法通过直接执行java -jar来运行,会提示找不到主类,是因为META-INF/MANIFEST.MF中缺少Main-Class信息, 怎么解决呢?

通过插件maven-shade-plugin可以帮助我们完成这件事.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.4.0</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <transformers>
                            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                <mainClass>com.juvenxu.mvnbook.HelloWorld</mainClass>
                            </transformer>
                        </transformers>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
mainClass中填写的是main方法所在类的全限定名.

再重新执行mvn clean package, 打包出来的jar就可以java -jar直接运行了.

ManifestResourceTransformer支持设置:
```java
String mainClass;
Map<String, Object> manifestEntries; // 在这里面可以任意设置想要的key-value到manifest.mf中.
private List<String> additionalAttributes;
```