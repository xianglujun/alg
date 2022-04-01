package com.learn.alg.leetcode.linked;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * <pre>
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" />
 * 题目数据 保证 整个链式结构中不存在环。
 *
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 *
 * 自定义评测：
 *
 * 评测系统 的输入如下（你设计的程序 不适用 此输入）：
 *
 * intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
 * listA - 第一个链表
 * listB - 第二个链表
 * skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
 * skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
 * 评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 视作正确答案 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 */
public class CrossLinkedNode {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 第一种解法:
     * <pre>
     *     还是采用hash的算法, 遍历两个链表，然后判断节点是否相同, 如果有相同的, 则返回对应节点即可
     *
     *     这种算法： 时间复杂度为O(M + N)
     *     空间复杂度为: O(M + N)
     * </pre>
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> nodes = new HashSet<>();
        ListNode node = headA;
        while (node != null) {
            nodes.add(node);
            node = node.next;
        }

        node = headB;
        while (node != null) {
            if (nodes.contains(node)) {
                return node;
            }
            node = node.next;
        }

        return null;
    }

    /**
     * 采用空间复杂度O(1)的算法，时间复杂度为O(M + N)
     *
     * <pre>
     *     采用双指针发, 将两个链表都向后移动, 当headA链表遍历完成后，接着遍历headB.一次类推。
     *     在遍历几次之后, headA与headB一定会在某一个点同步
     * </pre>
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;
    }

    /**
     * 解法:
     * <pre>
     *     求出两个链表的长度，并求出长度差值, 将较长的链表向前移动差值, 然后同步向后移动
     * </pre>
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lena = 0;
        ListNode node = headA;
        while (node != null) {
            lena++;
            node = node.next;
        }

        int lenb = 0;
        node = headB;
        while (node != null) {
            lenb++;
            node = node.next;
        }

        int diff = lena - lenb;
        ListNode diffNode = null;
        ListNode otherNode = null;
        if (diff > 0) {
            diffNode = headA;
            otherNode = headB;
        } else {
            diffNode = headB;
            otherNode = headA;
            diff = -diff;
        }

        int i = 0;
        while (i < diff) {
            diffNode = diffNode.next;
            i++;
        }

        while (diffNode != null && otherNode != null) {
            diffNode = diffNode.next;
            otherNode = otherNode.next;
            if (diffNode == otherNode) {
                return diffNode;
            }
        }

        return null;
    }
}
