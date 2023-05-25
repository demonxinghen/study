### StandardEnvironment
```java
public class Application{
    public static void main(String[] args) {
        ApplicationContext ctx = new GenericApplicationContext();
        Environment env = ctx.getEnvironment();
        boolean containsMyProperty = env.containsProperty("my-property");
        // 可以获取环境中是否有my-property属性
    }
}
```
环境中包含两部分属性，系统属性(System.getProperties())和系统环境变量(System.getenv())。

属性的搜索是分层级的。默认情况下，系统属性优先于环境变量。

也就是说containsProperty会先查找系统属性，再查找环境变量。所以如果同时在系统属性和环境变量中配置了my-property，将会返回系统属性中的值。

搜索层级：
1. ServletConfig参数,web应用,有DispatcherServlet的情况下
2. ServletContext参数,web.xml中的的context-param
3. JNDI环境变量(java:comp/env)
4. JVM系统属性(-D命令行参数,java -jar -Daaa=bbb ccc.jar 不能放在ccc.jar后面,否则获取不到)
5. JVM系统环境变量

可以调整自定义的属性源顺序
```java
public class Application{
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new GenericApplicationContext();
        MutablePropertySources sources = ctx.getEnvironment().getPropertySources();
        sources.addFirst(new MyPropertySource());
    }
}
```
### @PropertySource

由于environment是提前集成在整个容器,所以${}不管是在@Value,@Bean,还是xml中都是可以直接使用的。