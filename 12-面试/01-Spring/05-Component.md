### @Component
通用构件

### @Controller
表示层

### Service
服务层

### @Repository
持久层,此标记的用途之一是异常的自动翻译
org.springframework.dao.DataAccessException
org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor

### @Configuration
配置类,可以搭配@ComponentScan

@Bean不只是可以放在@Configuration,事实上你可以放在@Component,@Service等注解类中,同样会被spring管理.区别是非@Configuration的bean不会通过CGLIB增强来拦截方法和字段的调用。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c" xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.example" annotation-config="false"/> <!--这个注解隐式启动了annotation-config,所以无需再配置annotation-config,可以通过annotation-config="false"禁用,禁用之后,AutowiredAnnotationBeanPostProcessor和CommonAnnotationBeanPostProcessor也都不会生效了-->
    <!--<context:annotation-config/>-->
</beans>
```
配置了use-default-filters="false",则不会扫描@Component、@Repository、@Service、@Controller、@RestController或@Configuration以及其他继承了@Component的注解类。

### @Bean
可以通过static修饰,修饰后会在容器生命周期早期就进行初始化.

如果通过static修饰,那么永远不会被容器(包括上面说的@Configuration)拦截.

不能声明为private和final.

### @ComponentScan
虽然类路径扫描非常快,但是可以通过在编译时创建静态列表来提高大型应用程序的启动性能.
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-indexer</artifactId>
        <version>6.0.9</version>
        <optional>true</optional>
    </dependency>
</dependencies>
```
除此之外,需要注册CandidateComponentsIndexer,@ComponentScan也需要保留.

编译时,会在META-INF下生成spring.components,内容如下
```properties
com.example.Application=org.springframework.stereotype.Component,org.springframework.boot.SpringBootConfiguration
com.example.annotation.MyComponent=org.springframework.stereotype.Component
```
里面都是添加了@Indexed注解的类,@Component默认已经添加了这个注解。

启动时CandidateComponentsIndexLoader.loadIndex()会读取这个文件.

如果要回退到类路径扫描,可以配置spring.index.ignore=true,配置到Java的system属性或者spring的properties。

@Indexed使用限制：如果依赖的模块只有部分模块存在spring.components文件，则其他模块的bean也不会被扫描，此时需要回退到类路径扫描。