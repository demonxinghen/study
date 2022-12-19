package com.example.datastructure.linkedlist;

/**
 * 寻找倒数第k个节点<br/>
 * 快慢指针, 快指针走到第k个(索引k-1), 慢指针开始走, 快指针走到头, 慢指针就是倒数第K个<br/>
 * 假设倒数第一个, 当快指针走到索引0的时候, 慢指针在0, 快指针走到最后的时候, 慢指针也到了最后一个<br/>
 * 假设总长度10, 倒数第5个(也就是顺数第6个), 当快指针走到索引4的时候, 慢指针在0, 快指针走到最后的时候, 慢指针到了(length - 5)索引<br/>
 * 由于索引从0开始, 倒数从1开始数, 所以倒数第k个, 也就成了快指针先走k步即可<br/>
 * 1 2 3 4 5 6 7 8 9 10
 */
public class FindKthNode {

    public static void main(String[] args) {
        getKthFromEnd(new ListNode(1), 2);
    }

    public static ListNode getKthFromEnd(ListNode head, int k) {
        if (k <= 0 || head == null) {
            return null;
        }
        ListNode former = head, latter = head;
        for(int i = 0; i < k; i++) {
            if(former == null) return null;
            former = former.next;
        }
        while(former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}