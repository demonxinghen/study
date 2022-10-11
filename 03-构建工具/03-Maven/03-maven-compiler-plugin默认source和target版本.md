上来就摆结果.

| 版本                   | 默认source | 默认target |
|----------------------|----------|----------|
| version<=2.2         | 1.3      | 1.1      |
| 2.2<version<3.8.0    | 1.5      | 1.5      |
| 3.8.0<=version<3.9.0 | 1.6      | 1.6      |
| 3.9.0<=version       | 1.7      | 1.7      |

如何查看默认版本:

    2.3以上版本(包含2.3)的maven-compiler-plugin-{version}.jar,,打开jar包,可以在AbstractCompilerMojo中看见.
    2.3以前版本,可以通过将JAVA_HOME设置为jdk17以上(jdk8测不出来,应该是还支持低版本编译,jdk11没试过),通过执行mvn clean compile的错误提示可以看出默认的source和target版本.

以下是我的测试结果
#### 版本2.2
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.2</version>
        </plugin>
    </plugins>
</build>
```
错误信息是
```text
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:2.2:compile (default-compile) on project hello-world: Compilation failure
[ERROR] Failure executing javac, but could not parse the error:
[ERROR] 错误: 不再支持源选项 1.3。请使用 7 或更高版本。
[ERROR] 错误: 不再支持目标选项 1.1。请使用 7 或更高版本。
```

#### 版本2.3
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.3</version>
        </plugin>
    </plugins>
</build>
```
错误信息是
```text
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:2.3:compile (default-compile) on project hello-world: Compilation failure
[ERROR] Failure executing javac, but could not parse the error:
[ERROR] 错误: 不再支持源选项 5。请使用 7 或更高版本。
[ERROR] 错误: 不再支持目标选项 5。请使用 7 或更高版本。
```
#### 版本3.7.0
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.7.0</version>
        </plugin>
    </plugins>
</build>
```
错误信息是
```text
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.7.0:compile (default-compile) on project hello-world: Compilation failure: Compilation failure:
[ERROR] 不再支持源选项 5。请使用 7 或更高版本。
[ERROR] 不再支持目标选项 5。请使用 7 或更高版本。
```
#### 版本3.8.0
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.0</version>
        </plugin>
    </plugins>
</build>
```
错误信息是
```text
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.0:compile (default-compile) on project hello-world: Compilation failure: Compilation failure: 
[ERROR] 不再支持源选项 6。请使用 7 或更高版本。
[ERROR] 不再支持目标选项 6。请使用 7 或更高版本。
```

但是,有个点要注意,maven-compiler-plugin不管是2.2版本,还是3.10.1版本,配置source和target版本都是在javac后面添加参数,所以你可以在2.2的maven-compiler-plugin中配置source和target为1.7,1.8,甚至17,19,都是可以正常编译的.