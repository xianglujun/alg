package com.learn.datastructure.st;

import java.util.LinkedList;

/**
 * 二叉查找树:
 * <pre>
 *     1. 二叉树只有左右两个节点
 *     2. 当子节点为空时, 以null表示
 *     3. 左节点的值小于根节点, 右节点大于根节点
 * </pre>
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/8/8 19:59
 */
public class BST<Key extends Comparable<Key>, Value> extends AbstractSortedST<Key, Value> {

    private Node root;

    @Override
    public Key min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Override
    public Key floor(Key key) {

        Node n = floor(root, key);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    /**
     * 向下取整实现:
     * <pre>
     *     1. 给定Key小于二叉查找树的根节点的key, 那么小于等于key的最大key一定在根节点的左子树中。
     *     2. 给定key大于二叉树的根节点，那么只有当根节点右子树中存在小于等于key的节点时，小于等于key的最大key才会出现在右子树中
     * </pre>
     *
     * @param node 节点
     * @param key  需要查询的key
     * @return 对应的key
     */
    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        }

        if (cmp < 0) {
            Node t = floor(node.right, key);
            if (t != null) {
                return t;
            }
            return node;
        }

        return floor(node.left, key);
    }

    @Override
    public Key ceiling(Key key) {
        Node n = ceiling(root, key);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    /**
     * 向上取整:
     * <pre>
     *           1. 给定Key大于二叉查找树的根节点的key, 那么大于等于key的最小key一定在根节点的右子树中。
     *           2. 给定key小于二叉树的根节点，那么只有当根节点左子树中存在大于等于key的节点时，大于等于key的最小key才会出现在左子树中
     *       </pre>
     *
     * @param node 节点对象
     * @param key  需要遍历的key
     * @return 满足条件的Node
     */
    private Node ceiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        }

        if (cmp < 0) {
            return ceiling(node.right, key);
        }

        Node t = ceiling(node.left, key);
        if (t != null) {
            return t;
        } else {
            return node;
        }
    }

    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node n, Key key) {
        if (n == null) {
            return 0;
        }
        // 比较key
        int cmp = n.key.compareTo(key);
        if (cmp < 0) {
            return rank(n.left, key);
        } else if (cmp > 0) {
            return rank(n.right, key) + size(n.left) + 1;
        } else {
            return size(n.left);
        }
    }

    @Override
    public Key select(int k) {
        Node n = select(root, k);
        return n == null ? null : n.key;
    }

    private Node select(Node n, int k) {
        if (n == null) {
            return null;
        }

        int size = size(n.left);
        if (size > k) {
            // 找左节点
            return select(n.left, k);
        } else if (size < k) {
            return select(n.right, k);
        } else {
            return n;
        }
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        java.util.Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private void keys(Node n, java.util.Queue<Key> queue, Key lo, Key hi) {
        if (n == null) {
            return;
        }
        // 判断当前node与lo,hi的关系
        int cmpLo = lo.compareTo(n.key);
        int cmpHi = hi.compareTo(n.key);
        if (cmpLo < 0) {
            keys(n.left, queue, lo, hi);
        }
        if (cmpLo >= 0 && cmpHi <= 0) {
            queue.add(n.key);
        }
        if (cmpHi > 0) {
            keys(n.right, queue, lo, hi);
        }
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }

        // 寻找对应的key, 如果找到, 则替换，没找到，则新增
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            node.value = value;
            return node;
        } else if (cmp > 0) {
            node.right = put(node, key, value);
        } else {
            node.left = put(node, key, value);
        }
        node.nodes = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {

        if (node == null) {
            return null;
        }

        Node n = root;
        while (n != null) {
            int cmp = n.key.compareTo(key);
            if (cmp == 0) {
                return n.value;
            } else if (cmp > 0) {
                n = n.right;
            } else {
                n = n.left;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node n, Key k) {
        if (n == null) {
            return null;
        }
        // 找到删除的点
        int cmp = k.compareTo(n.key);
        if (cmp == 0) {
            if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            } else {
                Node t = n;
                n = min(t.right);
                n.right = deleteMin(t.right);
                n.left = t.left;
            }
        } else if (cmp < 0) {
            n.left = delete(n.left, k);
        } else {
            n.right = delete(n.right, k);
        }
        n.nodes = size(n.left) + size(n.right) + 1;
        return n;
    }

    private Node deleteMin(Node n) {
        if (n.left == null) {
            return n.right;
        }

        n.left = deleteMin(n.left);
        n.nodes = size(n.left) + size(n.right) + 1;
        return n;
    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node != null) {
            return node.nodes;
        }
        return 0;
    }

    /**
     * 树节点定义
     */
    private class Node {
        /**
         * 以当前节点为根节点，子节点的数量
         */
        private int nodes;
        /**
         * 节点key
         */
        private final Key key;
        /**
         * 节点的值
         */
        private Value value;

        /**
         * 做节点
         */
        private Node left;
        /**
         * 右节点
         */
        private Node right;

        Node(Key key, Value value, int nodes) {
            this.key = key;
            this.value = value;
            this.nodes = nodes;
        }
    }

}
