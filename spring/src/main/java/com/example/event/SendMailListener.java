package com.example.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: xuh
 * @date: 2023/5/29 11:24
 * @description: 实现ApplicationListener<BlockedListEvent>或者使用注解@EventListener
 */
@Component
public class SendMailListener {

    @EventListener({BlockedListEvent.class, NormalMailEvent.class})
    @Order(2)
    public void processBlockedListEvent(BaseEvent event) {
        System.out.println("监听到地址：" + event.getAddress());
        event.say();
    }
}
