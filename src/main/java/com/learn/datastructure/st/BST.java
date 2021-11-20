package com.learn.datastructure.st;

/**
 * TODO
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/8/8 19:59
 */
public class BST<Key extends Comparable, Value> extends AbstractSortedST<Key, Value> {

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
        return null;
    }

    private Node max(Node node) {
        // todo
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
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
        Node n = node;
        while (n != null) {
            int cmp = n.key.compareTo(key);
            if (cmp == 0) {
                n.value = value;
                return node;
            } else if (cmp > 0) {
                n.right = put(n, key, value);
            } else {
                n.left = put(n, key, value);
            }
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

    private class Node {
        private int nodes;
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        Node(Key key, Value value, int nodes) {
            this.key = key;
            this.value = value;
            this.nodes = nodes;
        }
    }

}
