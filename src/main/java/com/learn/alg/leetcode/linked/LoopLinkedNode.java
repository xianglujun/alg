package com.learn.alg.leetcode.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * 探测环形链表
 *
 * <pre>
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * <p>
 * </pre>
 */
public class LoopLinkedNode {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 解法:
     * <pre>
     *  利用hash的方式，将遍历过的元素存储在hashmap中，以此来判断是否有环
     *
     *  算法分析：
     *  1. 在该算法实现中, 因为会遍历链表中的所有的值, 因此时间复杂度为O(n)
     *  2. 在实现的过程中，因为引入了hashmap, 因此空间复杂度为O(n)
     * </pre>
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        Map<ListNode, Object> nodeMap = new HashMap<>();
        while (head != null) {
            if (nodeMap.containsKey(head)) {
                return true;
            }

            nodeMap.put(head, null);
            head = head.next;
        }

        return false;
    }

    /**
     * <pre>
     *  这种解法利用了弗洛伊德的判断环形链表的实现，在空间上能够实现O(1)
     * </pre>
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                return true;
            }
        }

        // 代码走到这一步, 就说明链表在尾部的没有了引用, 因此不会产生循环
        return false;
    }


    public static void main(String[] args) {

    }

}
