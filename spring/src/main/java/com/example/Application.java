package com.example;

import com.example.annotation.MyProperties;
import com.example.annotation.ThirdProperties;
import com.example.dao.ServiceImpl;
import com.example.resource.ResourceType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "nested.xml", "p.xml", "autowiring.xml", "exclude.xml"}, context);
//        TestXmlBean bean = applicationContext.getBean(TestXmlBean.class);
//        System.out.println(bean);
//        System.out.println(applicationContext.getParent().getBean(MyComponent.class));
//
//        OuterBean outerBean = applicationContext.getBean(OuterBean.class);
//        System.out.println(outerBean.getInnerBean());
//
//        PNamespace pNamespace = applicationContext.getBean(PNamespace.class);
//        System.out.println(pNamespace);
//
        ServiceImpl service = applicationContext.getBean(ServiceImpl.class);
        System.out.println(service.getDataSourceDto());

        ResourceType resourceType = applicationContext.getBean(ResourceType.class);
        resourceType.say();

        MyProperties myProperties = applicationContext.getBean(MyProperties.class);
        myProperties.sayGoodbye();

        ThirdProperties thirdProperties = applicationContext.getBean(ThirdProperties.class);
        thirdProperties.sayGoodbye();
//
//        ClassPathXmlApplicationContext subApplicationContext = new ClassPathXmlApplicationContext(new String[]{"exclude.xml"}, applicationContext);
//
//        System.out.println(subApplicationContext.getBean(MySQLDto.class));
//        System.out.println(applicationContext.getBean(MySQLDto.class));

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("我要关闭了");
//            applicationContext.close();
//            context.close();
//        }));
        context.registerShutdownHook();
//        applicationContext.close();
//        context.close();
    }

}
