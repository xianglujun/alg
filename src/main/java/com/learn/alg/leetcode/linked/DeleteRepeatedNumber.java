package com.learn.alg.leetcode.linked;

import static com.learn.alg.leetcode.linked.MergeSortedLinkedNode.ListNode;

/**
 * 删除链表中的重复数字:
 * <pre>
 *     给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 *     <img src="https://assets.leetcode.com/uploads/2021/01/04/list1.jpg" />
 *     输入：head = [1,1,2]
 *     输出：[1,2]
 *
 *     <img src="https://assets.leetcode.com/uploads/2021/01/04/list2.jpg" />
 *      输入：head = [1,1,2,3,3]
 *      输出：[1,2,3]
 * </pre>
 */
public class DeleteRepeatedNumber {

    /**
     * 还是采用双指针的方法, 从头向后遍历
     *
     * @param head 头元素
     * @return 结果
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head.next;
        ListNode prev = head;
        while (next != null) {
            if (prev.val == next.val) {
                // 删除next节点
                prev.next = next.next;
                next.next = null;
                next = prev.next;
            } else {
                prev = next;
                next = next.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        new DeleteRepeatedNumber().deleteDuplicates(head);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
