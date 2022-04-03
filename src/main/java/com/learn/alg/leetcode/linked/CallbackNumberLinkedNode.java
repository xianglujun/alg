package com.learn.alg.leetcode.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * <p>
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * <p>
 */
public class CallbackNumberLinkedNode {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一:
     * <pre>
     *     引入hash表, 记录对应的索引位置以及节点, 然后将倒数的节点转换为正序的节点位置
     *
     *     该算法总结如下:
     *     时间复杂度为: O(N)
     *     空间复杂度为: O(N)
     * </pre>
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        Map<Integer, ListNode> indexMap = new HashMap<>();
        int index = 0;
        ListNode node = head;

        while (node != null) {
            indexMap.put(index, node);
            index++;
            node = node.next;
        }

        int orderIndex = index - k;
        return indexMap.get(orderIndex);
    }

    /**
     * 该算法会在上面算法实现中进行优化, 将空间复杂度优化为O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode r = reverse(head);
        ListNode fr = r;
        ListNode result = r;
        int i = 0;
        while (i < k) {
            result = r;
            r = r.next;
            i++;
        }

        reverse(fr);
        return result;
    }

    /**
     * 解法三:
     * <pre>
     *     该解法采用快慢指针实现, 快慢指针的跨度为k-1, 当快指针到达尾部时, 则慢指针所在位置即为解
     * </pre>
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd3(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int skip = k - 1;
        ListNode slow = head, fast = head;

        int i = 0;
        while (i < skip) {
            fast = fast.next;
            i++;
        }

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode preNode = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = preNode;
            preNode = cur;
            cur = next;
        }

        return preNode;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        new CallbackNumberLinkedNode().getKthFromEnd(node, 2);
    }
}
