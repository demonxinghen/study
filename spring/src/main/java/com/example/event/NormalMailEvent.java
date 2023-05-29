package com.example.event;

/**
 * @author: xuh
 * @date: 2023/5/29 10:32
 * @description:
 */
public class NormalMailEvent extends BaseEvent {
    public NormalMailEvent(Object source, String address, String content) {
        super(source, address, content);
    }

    public void say(){
        System.out.println("发送成功。地址是：" + getAddress() + ",内容是：" + getContent());
    }
}
