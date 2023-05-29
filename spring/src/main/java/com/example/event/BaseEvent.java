package com.example.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author: xuh
 * @date: 2023/5/29 11:41
 * @description:
 */
public abstract class BaseEvent extends ApplicationEvent {

    private final String address;

    private final String content;

    public BaseEvent(Object source, String address, String content) {
        super(source);
        this.address = address;
        this.content = content;
    }

    public abstract void say();

    public String getAddress() {
        return address;
    }

    public String getContent() {
        return content;
    }
}
