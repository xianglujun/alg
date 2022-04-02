package com.learn.alg.leetcode.linked;

import java.util.Stack;

/**
 * 判断链表是否为会问链表
 * <pre>
 *     给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * </pre>
 */
public class PalindromeLinkedNode {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 根据栈的方式, 将元素放入到栈中, 然后比较栈中顺序是否与列表顺序一致
     * <pre>
     *     这种解法因为需要用到额外的空间, 因此:
     *     时间复杂度为: O(N)
     *     空间复杂度为: O(N)
     * </pre>
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        node = head;
        while (node != null) {
            ListNode sn = stack.pop();
            if (sn.val != node.val) {
                return false;
            }

            node = node.next;
        }

        return true;
    }

    /**
     * 解法2:
     * <pre>
     * 1. 当链表长度有奇数长度时, 快指针走两步, 慢指针走一步, 当快指针走到尾部的时候, 慢指针刚好处于中间, 将中间元素归于左侧列表
     * 2. 当链表长度由偶数长度时, 快指针走两步, 慢指针走一步, 当快指针走到尾部的时候, 慢指针处于会问链表的头部
     *
     * 因此当我们在一轮遍历完成之后, 将慢指针链表进行反转, 然后和快指针的链表一一对比
     * </pre>
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 通过判断slow是否为空, 判断链表的长度是奇数还是偶数
        if (fast != null) {
            slow = slow.next;
        }

        // 反转链表
        slow = reverse(slow);
        ListNode sr = slow;
        fast = head;

        boolean result = true;
        ListNode fastPrev = null;
        while (slow != null) {
            if (slow.val != fast.val) {
                result = false;
            }
            fastPrev = fast;
            slow = slow.next;
            fast = fast.next;
        }

        // 还原链表
        fastPrev.next = reverse(sr);
        return result;
    }

    /**
     * {@link #isPalindrome2(ListNode)}该方法在实现的时候, 因为最终可能导致fast溢出了界限之外, 这个方法没有溢出界限之外, 保障了安全
     *
     * @param head
     * @return
     */
    public boolean isPalindrome4(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 当执行完成之后, slow在左链表的终点. 而fast则在链表的起点, 因此将fast与slow交换位置
        ListNode firstEnd = slow;
        slow = slow.next;

        // 反转链表
        slow = reverse(slow);
        ListNode secondStart = slow;
        fast = head;

        boolean result = true;
        while (slow != null) {
            if (slow.val != fast.val) {
                result = false;
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }

        // 还原链表
        firstEnd.next = reverse(secondStart);
        return result;
    }

    private ListNode reverse(ListNode node) {

        if (node == null) {
            return null;
        }

        ListNode prev = null, cur = node;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    /**
     * 采用递归的算法实现
     * <pre>
     * 时间复杂度：O(n)O(n)，其中 nn 指的是链表的大小。
     * 空间复杂度：O(n)O(n)，其中 nn 指的是链表的大小。我们要理解计算机如何运行递归函数，
     * 在一个函数中调用一个函数时，计算机需要在进入被调用函数之前跟踪它在当前函数中的位置（以及任何局部变量的值），
     * 通过运行时存放在堆栈中来实现（堆栈帧）。在堆栈中存放好了数据后就可以进入被调用的函数。在完成被调用函数之后，
     * 他会弹出堆栈顶部元素，以恢复在进行函数调用之前所在的函数。在进行回文检查之前，递归函数将在堆栈中创建 nn 个堆栈帧，
     * 计算机会逐个弹出进行处理。所以在使用递归时空间复杂度要考虑堆栈的使用情况。
     * </pre>
     *
     * @param node
     * @return
     */
    ListNode frontHead;

    public boolean isPalindrome3(ListNode node) {
        frontHead = node;
        return recursivelyCheck(node);
    }

    public boolean recursivelyCheck(ListNode node) {
        if (node != null) {
            if (!recursivelyCheck(node.next)) {
                return false;
            }

            // 表明前节点与从后面遍历的节点不相等, 直接返回false
            if (frontHead.val != node.val) {
                return false;
            }

            // 走到这里说明前后节点相等, 因此继续向后移动比较
            frontHead = frontHead.next;
        }

        return true;
    }

    public static void main(String[] args) {
//        ListNode node = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
//        boolean result = new PalindromeLinkedNode().isPalindrome(node);
//        System.out.println(result);

        ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        boolean r = new PalindromeLinkedNode().isPalindrome4(node1);
        System.out.println(r);

        // 输出链表
        while (node1 != null) {
            System.out.println(node1.val + " ");
            node1 = node1.next;
        }
    }
}
