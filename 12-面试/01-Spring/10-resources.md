### ResourceLoader
用于加载org.springframework.core.io.Resource(本质上是java.net.URL),Resource的实现都包装了java.net.URL实例。


| 内置实现                   |                                                                          |
|------------------------|--------------------------------------------------------------------------|
| UrlResource            | 包装了一个 java.net.URL,访问文件(file:)、HTTPS目标(https:)、FTP目标(ftp:)等              |
| ClassPathResource      | 类路径加载,(classpath:)                                                       |
| FileSystemResource     | 支持java.io.File,java.nio.file.Path,使用的是相对路径,如果使用绝对路径,使用file:前缀            |
| PathResource           | 支持java.nio.file.Path,java.nio.path.Path                                  |
| ServletContextResource | ServletContext                                                           |
| InputStreamResource    | 与其他Resource实现相比，这是一个已打开资源的描述符,因此isOpen()返回true,仅当没有适用的特定Resource实现时才应使用它 |
| ByteArrayResource      | 字节数组                                                                     |

所有应用程序上下文都实现了 ResourceLoader 接口。因此，所有应用程序上下文都可用于获取 Resource 实例。

### ResourcePatternResolver(接口)
是 ResourceLoader 接口的扩展，它定义了将位置模式（例如，Ant 风格的路径模式）解析为 Resource 对象的策略。

PathMatchingResourcePatternResolver是默认实现

### ResourceLoaderAware
可以获取Resource,也可以通过ApplicationContextAware获取resource,因为所有的ApplicationContext都是Resource

如果想要支持在类路径中多个位置的同一路径下发现的多个模板,可以配置为classpath*:/config/template/*.txt,接收的时候以数组形式

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private final Resource[] templates;

    public MyBean(@Value("${template.path}")Resource[] templates) {
        this.templates = templates;
    }
}
```

### 使用资源创建应用程序上下文

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Demo {
    public static void main(String[] args) {
        // resource没有前缀,比如file:,classpath:,ftp:,https:等,会根据特定的应用程序上下文选择对应的resource,比如此处使用的是ClassPathResource
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/appContext.xml");
        // 即使在有前缀的情况下,当前使用的Resource根据前缀而定,而默认的Resource依然是和ApplicationContext一致,如下实例
        // 加载conf/appContext.xml时使用ClassPathResource,后续加载其他资源时依然使用FileSystemResource,除非其他资源也使用了前缀
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("classpath:conf/appContext.xml");
    }
}
```

classpath*表示项目中有多个classpath路径,比较少见

支持ant-style匹配

`?` 匹配任何单字符

`*` 匹配0或者任意数量字符

`**` 匹配0或者更多的目录