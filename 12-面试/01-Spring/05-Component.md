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