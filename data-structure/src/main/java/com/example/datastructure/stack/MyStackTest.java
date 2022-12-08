package com.example.datastructure.stack;

/**
 * @author: xuh
 * @date: 2022/12/8 21:34
 * @description: 自定义栈测试
 */
public class MyStackTest {

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        stack.push("1");
        System.out.println("栈内容为：" + stack + ",栈的长度为：" + stack.size());

        stack.push("2");
        System.out.println("栈内容为：" + stack + ",栈的长度为：" + stack.size());

        stack.pop();
        System.out.println("栈内容为：" + stack + ",栈的长度为：" + stack.size());

        stack.push("3");
        System.out.println("栈内容为：" + stack + ",栈的长度为：" + stack.size());

        stack.push("4");
        System.out.println("栈内容为：" + stack + ",栈的长度为：" + stack.size());

        stack.pop();
        System.out.println("栈内容为：" + stack + ",栈的长度为：" + stack.size());

        stack.pop();
        System.out.println("栈内容为：" + stack + ",栈的长度为：" + stack.size());

        stack.pop();
        System.out.println("栈内容为：" + stack + ",栈的长度为：" + stack.size());
    }
}
