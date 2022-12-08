package com.example.datastructure.stack;

import java.util.EmptyStackException;

/**
 * @author: xuh
 * @date: 2022/12/8 21:07
 * @description: 链表栈
 */
public class MyStack<T> {

    /**
     * 栈元素个数
     */
    private int size;

    /**
     * 栈顶元素
     */
    private Node<T> first;

    public int size(){
        return size;
    }

    /**
     * 入栈
     * @param data
     */
    public void push(T data){
        Node<T> node = new Node<>(data, null);
        if (first == null) {
            first = node;
            size++;
            return;
        }
        Node<T> x = first;
        while (x.next != null){
            x = x.next;
        }
        x.next = node;
        size++;
    }

    /**
     * 出栈
     * @return
     */
    public T pop() {
        if (first == null) throw new EmptyStackException();
        Node<T> x = first;
        T data;
        if (first.next == null){
            data = first.data;
            first = null;
            size--;
            return data;
        }
        while (x.next.next != null){
            x = x.next;
        }
        data = x.next.data;
        x.next = null;
        size--;
        return data;
    }

    @Override
    public String toString() {
        if (size == 0){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<T> x = first;
        while (x.next != null){
            sb.append(x.data).append(",");
            x = x.next;
        }
        return sb.append(x.data).append("]").toString();
    }

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
    }

}
