package com.learn.alg.leetcode.linked;

import java.util.Stack;

/**
 * 反转链表:
 * <pre>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * </pre>
 */
public class ReverseLinkedNode {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 解法一:
     * <pre>
     *     通过栈的方式缓存结果，然后缓存再回放回来
     *
     *     时间复杂度为: O(N)
     *     空间复杂度为: O(N)
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.add(head);
            head = head.next;
        }

        // 从栈中获取链表, 并重组
        ListNode h = new ListNode(-1);
        ListNode next = h;
        while (!stack.isEmpty()) {
            next.next = stack.pop();
            next.next.next = null;
            next = next.next;
        }

        return h.next;
    }

    /**
     * 采用双指针的方式实现
     * <pre>
     *     这种解法使用了双指针实现了链表的反转
     *
     *     时间复杂度: O(N)
     *     空间复杂度: O(N)
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode preNode = null, curNode = head;
        while (curNode != null) {
            ListNode n = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = n;
        }

        return preNode;
    }
}
