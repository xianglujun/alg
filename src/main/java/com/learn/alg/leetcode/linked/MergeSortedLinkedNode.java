package com.learn.alg.leetcode.linked;

/**
 * <pre>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <img src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" />
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * </pre>
 */
public class MergeSortedLinkedNode {

    /**
     * 解法:
     * <pre>
     *     和之前的数组的实现方式相似, 可以采用双指针法，依次来实现
     * </pre>
     *
     * @param list1 链表结果集
     * @param list2 链表结果集
     * @return 结果
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode next = head;
        while (list1 != null && list2 != null) {
            // 判断两者的大小
            if (list1.val < list2.val) {
                next.next = list1;
                list1 = list1.next;
            } else {
                next.next = list2;
                list2 = list2.next;
            }
            next = next.next;
        }

        if (list1 != null) {
            next.next = list1;
        }
        if (list2 != null) {
            next.next = list2;
        }

        return head.next;
    }

    /**
     * 采用递归实现
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            // list1遍历完成
            return list2;
        }

        if (list2 == null) {
            // list2遍历完成
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists2(list1, list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode result = new MergeSortedLinkedNode().mergeTwoLists(list1, list2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
        System.out.println("----------------");

        list1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        result = new MergeSortedLinkedNode().mergeTwoLists2(list1, list2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public static class ListNode {
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
    }
}
