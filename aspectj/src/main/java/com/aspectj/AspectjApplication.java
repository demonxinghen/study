package com.aspectj;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

/**
 * @author: xuh
 * @date: 2023/6/5 10:28
 * @description: 运行增加VM options:-javaagent:/usr/local/repo/org/springframework/spring-instrument/6.0.9/spring-instrument-6.0.9.jar --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/sun.net.util=ALL-UNNAMED, 后面两个--add-opens是jdk17需要的
 * <br/>
 * 都需要META-INF/aop.xml
 */
@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.AUTODETECT)
public class AspectjApplication {

    public static void main(String[] args) {
        // 此处是纯注解方式,区别在于需要使用@EnableLoadTimeWeaving注解,其次不能通过getBean方式,也就是非Spring管理的bean
        // aspectjWeaving=AspectJWeaving.AUTODETECT或者spectJWeaving.ENABLED都可以
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AspectjApplication.class);
        StubEntitlementCalculationService calculationService = new StubEntitlementCalculationService();
        calculationService.calculateEntitlement();

        // 此处是读xml配置,可以使用getBean方式来实现加载时编织
        // <context:load-time-weaver aspectj-weaving="on"/> 测试效果只能on,使用autodetect不生效
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // StubEntitlementCalculationService entitlementCalculationService = applicationContext.getBean(StubEntitlementCalculationService.class);
        // entitlementCalculationService.calculateEntitlement();
    }
}
