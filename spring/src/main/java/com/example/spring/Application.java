package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "nested.xml", "p.xml", "autowiring.xml"}, context);
        TestXmlBean bean = applicationContext.getBean(TestXmlBean.class);
        System.out.println(bean);
        System.out.println(applicationContext.getParent().getBean(MyComponent.class));

        OuterBean outerBean = applicationContext.getBean(OuterBean.class);
        System.out.println(outerBean.getInnerBean());

        PNamespace pNamespace = applicationContext.getBean(PNamespace.class);
        System.out.println(pNamespace);
        applicationContext.close();
        context.close();
    }

}
