package com.learn.alg.leetcode.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询循环链表中入环的第一个元素
 * <pre>
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * <img src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png" />
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点
 * </pre>
 */
public class QueryLoopLinkedNode {

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
     *     采用hash的方式, 该方式用来探测是否循环链表, 并把第一次重复的元素记为第一个入环元素
     *
     *     这种算法时间复杂度为: O(N)
     *     空间复杂度为: O(N)
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        Map<ListNode, Object> nodeMap = new HashMap<>();
        while (head != null) {
            if (nodeMap.containsKey(head)) {
                return head;
            }
            nodeMap.put(head, null);
            head = head.next;
        }

        return null;
    }

    /**
     * 采用双指针方法,
     * <pre>
     * 1. 计算是否有环，如果有环，则记录环相遇的位置
     * 2. 将慢指针移动到列表头部，快指针和慢指针依次向后移动,当两个指针相遇时，就是入口
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }

        boolean isCycle = false;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isCycle = true;
                break;
            }
        }

        if (isCycle) {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }

            return slow;
        }

        return null;
    }
}
