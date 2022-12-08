package com.example.datastructure.linkedlist;

/**
 * @author: xuh
 * @date: 2022/12/8 20:30
 * @description: 自定义单向链表
 */
public class MyLinkedList_01<T> {

    /**
     * 链表长度
     */
    private int size;

    /**
     * 链表表头
     */
    private Node<T> first;

    public void add(T obj){
        Node<T> node = new Node<>(obj, null);
        if (first == null){
            first = node;
        }else {
            Node<T> temp = first;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
    }

    public boolean remove(T data){
        // 如果为空，则移除失败
        if (data == null || first == null) return false;
        Node<T> prev = first;
        if (prev.data.equals(data)){
            // 判断第一个节点
            first = first.next;
            size--;
            return true;
        }
        // 取两个节点， prev存放前置节点，以便于在Node就是要移除的节点时，可以找到前置节点prev
        Node<T> node = prev.next;
        while (!node.data.equals(data) && node.next != null){
            prev = prev.next;
            node = prev.next;
        }
        // 进入这个if说明node是最后一个节点了，之前的节点都没有匹配上
        if (node.next == null){
            if (node.data.equals(data)){
                prev.next = null;
                size--;
                return true;
            }else return false;
        }
        // 代码到这里说明前置节点匹配成功
        prev.next = node.next;
        size--;
        return true;
    }

    public int size(){
        return size;
    }

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        if (first == null){
            return stringBuilder.append("]").toString();
        }
        Node<T> node = first;
        while (node.next != null){
            stringBuilder.append(node.data).append(",");
            node = node.next;
        }

        return stringBuilder.append(node.data).append("]").toString();
    }
}
