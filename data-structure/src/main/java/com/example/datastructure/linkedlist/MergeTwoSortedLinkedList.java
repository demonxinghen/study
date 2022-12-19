package com.example.datastructure.linkedlist;

/**
 * @author: xuh
 * @date: 2022/12/17 22:15
 * @description: 合并两个有序列表
 */
public class MergeTwoSortedLinkedList {

    private static void showList(ListNode listNode){
        ListNode cur = listNode;
        while (cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        showList(listNode1);

        ListNode listNode8 = new ListNode(4);
        ListNode listNode7 = new ListNode(3, listNode8);
        ListNode listNode6 = new ListNode(2, listNode7);
        ListNode listNode5 = new ListNode(1, listNode6);
        showList(listNode5);
        ListNode listNode = mergeTwoLists(listNode1, listNode5);
        while (listNode != null){
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val + '}';
        }
    }
}

