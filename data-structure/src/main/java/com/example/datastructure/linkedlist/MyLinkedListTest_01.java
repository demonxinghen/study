package com.example.datastructure.linkedlist;

/**
 * @author: xuh
 * @date: 2022/12/8 20:37
 * @description:
 */
public class MyLinkedListTest_01 {

    public static void main(String[] args) {
        MyLinkedList_01 list = new MyLinkedList_01();
        list.add("1");
        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
        System.out.println(list);
        System.out.println(list.size());

//        list.remove("1");
        list.remove("1");
//        list.remove("2");
        System.out.println(list);
        System.out.println(list.size());
    }
}
