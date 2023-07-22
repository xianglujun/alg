package com.learn.datastructure.linked;

import lombok.Data;

public class LinkedListNode {

    /**
     * 反转单向链表
     *
     * @param node 节点信息
     */
    public Node reverseLinkedList(Node head) {
        Node pre = null, cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(3, new Node(4))));
        LinkedListNode linkedListNode = new LinkedListNode();
        Node node = linkedListNode.reverseLinkedList(head);
        printNode(node);

        head = buildDoubleLinkedList();
        Node node1 = linkedListNode.reverseDoubleLinkedList(head);
        printNode(node1);

        // 删除指定值
        head = new Node(1, new Node(2, new Node(3, new Node(4, new Node(4, new Node(5, new Node(4)))))));
        Node node2 = linkedListNode.deleteNode(head, 4);
        printNode(node2);
    }

    private static Node buildDoubleLinkedList() {
        Node head = new Node(1);
        Node next = new Node(2);
        head.next = next;
        next.next = new Node(3);
        next.prev = head;

        Node next1 = next.next;
        next1.next = new Node(4);
        next1.prev = next;

        return head;
    }

    private static void printNode(Node node) {
        Node cur = node;
        while (cur != null) {
            System.out.print("-->" + cur.val);
            cur = cur.next;
        }
        System.out.println("");
    }

    /**
     * 反转双向链表
     *
     * @param node 节点信息
     */
    public Node reverseDoubleLinkedList(Node node) {
        Node cur = node, prev = null, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            cur.prev = next;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 删除指定值的node
     *
     * @param node 节点信息
     * @param val  匹配的值
     */
    public Node deleteNode(Node node, Object val) {
        Node cur = node;
        while (cur != null && cur.val.equals(val)) {
            // cur之前表明与val值相等，则直接去掉
            Node next = cur.next;
            cur.next = null;
            cur = next;
        }

        // 结束条件
        Node head = cur;
        Node prev = cur;
        while (cur != null) {
            if (cur.val.equals(val)) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }

        return head;
    }


    @Data
    public static class Node {
        private Object val;
        private Node next;
        private Node prev;

        public Node(Object val) {
            this.val = val;
        }

        public Node(Object val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(Object val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }
}
