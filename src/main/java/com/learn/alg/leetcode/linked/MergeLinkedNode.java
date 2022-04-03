package com.learn.alg.leetcode.linked;

/**
 * 合并两个链表:
 * <pre>
 * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
 *
 * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
 *
 * 下图中蓝色边和节点展示了操作后的结果：
 *
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/28/fig1.png" />
 * </pre>
 */
public class MergeLinkedNode {

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

    /**
     * 该方法主要是遍历list1, 然后将在[a, b]之间的元素从链表中删除, 然后重新拼接到list2上面
     *
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        int index = 0;
        ListNode prevNode = null;
        ListNode cur = list1;
        ListNode nextNode = null;
        ListNode head = null;
        while (index <= b) {
            if (index >= a) {
                if (prevNode != null) {
                    prevNode.next = cur.next;
                    cur.next = null;
                    cur = prevNode.next;
                    index++;
                    continue;
                } else {
                    ListNode n = cur;
                    cur = cur.next;
                    n.next = null;
                    index++;
                    continue;
                }
            }
            if (head == null) {
                head = cur;
            }
            prevNode = cur;
            cur = cur.next;
            index++;
        }

        nextNode = cur;
        ListNode n2 = list2;
        while (n2.next != null) {
            n2 = n2.next;
        }

        n2.next = nextNode;

        if (prevNode != null) {
            prevNode.next = list2;
        }

        return prevNode == null ? list2 : head;
    }

    /**
     * 找到a-1与b+1的位置， 然后与list2拼接即可
     *
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween2(ListNode list1, int a, int b, ListNode list2) {
        ListNode beforeInsert = list1;
        for (int i = 1; i < a; i++) {
            beforeInsert = beforeInsert.next;
        }

        ListNode afterInsert = list1;
        for (int i = 0; i <= b; i++) {
            afterInsert = afterInsert.next;
        }

        // 找到list2的结尾节点
        ListNode start2 = list2, end2 = list2;
        while (end2.next != null) {
            end2 = end2.next;
        }

        beforeInsert.next = start2;
        end2.next = afterInsert;

        return list1;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(0,
                new ListNode(1,
                        new ListNode(2,
                                new ListNode(3,
                                        new ListNode(4,
                                                new ListNode(5))))));

        ListNode list2 = new ListNode(10001,
                new ListNode(10002,
                        new ListNode(10003)));

        new MergeLinkedNode().mergeInBetween2(list1, 3, 4, list2);

        while (list1 != null) {
            System.out.print(list1.val + " ");
            list1 = list1.next;
        }
    }
}
