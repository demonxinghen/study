package com.example.datastructure.linkedlist;

/**
 * 单链表反转
 */
public class Reverse {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2, listNode1);
        ListNode listNode3 = new ListNode(3, listNode2);
        ListNode listNode4 = new ListNode(4, listNode3);
        System.out.println(listNode4);
        ListNode listNode = reverseList(listNode4);
        System.out.println(listNode);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = null;
        ListNode tail = new ListNode(head.val);
        while (head.next != null){
            next = new ListNode(head.next.val, tail);
            tail = next;
            head = head.next;
        }
        return next;
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
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
