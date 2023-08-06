package com.learn.alg.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 二叉树的递归套路:
 * <pre>
 *     给定一颗二叉树头结点head, 和另外两个节点a和b，返回a和b的最低公共祖先
 * </pre>
 */
public class TreeCommonParentNode {
    private static class Node {
        private Object val;
        private Node left;
        private Node right;

        public Node() {

        }

        public Node(Object val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public int hashCode() {
            return this.val.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (!Node.class.isAssignableFrom(obj.getClass())) {
                return false;
            }

            Node target = (Node) obj;
            return target.val.equals(this.val);
        }

        @Override
        public String toString() {
            return this.val.toString();
        }
    }

    /**
     * 采用遍历的方式实现
     *
     * @param head 需要遍历的头节点
     * @param a    需要获取的a节点
     * @param b    需要获取的b节点
     * @return 相交的第一个节点
     */
    private static Node process(Node head, Node a, Node b) {
        Map<Node, Node> childParentMap = new HashMap<>();

        // 根节点对应的是null
        childParentMap.put(head, null);
        // 填充子节点信息
        fillParentMap(head, childParentMap);

        // 开始遍历a节点的父节点
        Set<Node> parents = new HashSet<>();
        Node cur1 = a;
        while (cur1 != null) {
            parents.add(cur1);
            cur1 = childParentMap.get(cur1);
        }

        Node cur2 = b;
        while (!parents.contains(cur2)) {
            cur2 = childParentMap.get(cur2);
        }

        return cur2;
    }

    private static void fillParentMap(Node head, Map<Node, Node> childParentMap) {
        if (head.left != null) {
            childParentMap.put(head.left, head);
            fillParentMap(head.left, childParentMap);
        }

        if (head.right != null) {
            childParentMap.put(head.right, head);
            fillParentMap(head.right, childParentMap);
        }
    }

    /**
     * 递归二叉树实现
     * <pre>
     *  在遍历树的过程中，有以下集中情况:
     *  对于根节点x,需要发现ab节点，有以下四种情况
     *  1. 当a、b节点都在x节点的左子树上
     *  2. 当a、b节点都在x节点的右子树上
     *  3. 当a在节点x的左子树，b在节点x的右子树上, 则交叉点为x本身
     *  4. x整棵树上没有找全a、b节点
     * </pre>
     *
     * @param head 需要遍历的树
     * @param a    需要查找的节点a
     * @param b    需要查找的节点b
     * @return 相交的节点
     */
    private static Node process2(Node head, Node a, Node b) {
        Info info = processInfo(head, a, b);
        return info.across;
    }

    private static Info processInfo(Node head, Node a, Node b) {
        // 递归结束条件
        if (head == null) {
            return new Info(false, false, null);
        }
        // 处理左节点
        Info leftInfo = processInfo(head.left, a, b);
        // 处理有节点
        Info rightInfo = processInfo(head.right, a, b);
        // 判断是否找到了a、b节点
        boolean findA = head.equals(a) || leftInfo.findA || rightInfo.findA;
        boolean findB = head.equals(b) || leftInfo.findB || rightInfo.findB;

        // 判断是否找到了a
        Node ans = null;

        // 左子树上找到了交汇点
        if (leftInfo.across != null) {
            ans = leftInfo.across;
        }

        // 右子树上找到了交汇点
        if (rightInfo.across != null) {
            ans = rightInfo.across;
        }

        // 左右都没有找到
        if (ans == null) {
            if (findA && findB) {
                ans = head;
            }
        }
        return new Info(findA, findB, ans);
    }

    /**
     * 记录中间结果信息
     */
    private static class Info {
        /**
         * 是否发现a节点
         */
        private boolean findA;
        /**
         * 是否发现b节点
         */
        private boolean findB;
        /**
         * 是否包含交叉节点
         */
        private Node across;

        public Info(boolean findA, boolean findB, Node across) {
            this.findA = findA;
            this.findB = findB;
            this.across = across;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1,
                new Node(2, new Node(3, null, null), new Node(4, null, null)),
                new Node(5, new Node(6, null, null), new Node(7, null, null)));

        Node a = new Node(6, null, null);
        Node b = new Node(7, null, null);
        System.out.println(process(root, a, b));
        System.out.println(process2(root, a, b));
    }
}
