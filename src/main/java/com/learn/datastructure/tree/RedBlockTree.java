package com.learn.datastructure.tree;

/**
 * 该实例是对红黑树的实现: 在这里我们需要说明，红黑树其实是2-3树实现的一种。
 * <p>红黑树中的链接分为两种类型:</p>
 * <ul>
 *     <li>1. 红链接将两个2-节点链接起来构成3-节点</li>
 *     <li>2. 黑链接则是2-3数中的普通链接</li>
 * </ul>
 * 红黑树的另外一种定义是含有红黑链接并满足下列条件的二叉查找树:
 * <ul>
 *     <li>1. 红链接均为左连接</li>
 *     <li>2. 没有任何一个节点同时和两条红链接相连</li>
 *     <li>3. 该树是完美黑色平衡的。即任意空链接到根节点的路径上的黑链接数量相同</li>
 *     <li>4. 该树的所有节点都是有序的</li>
 *     <li>5. 根节点总是黑色的</li>
 * </ul>
 */
public class RedBlockTree<Key extends Comparable<Key>, Value> {

    private Node root;

    /**
     * 树中的节点信息
     */
    private class Node {
        /**
         * 键
         */
        Key key;
        /**
         * 相关联的值
         */
        Value val;
        /**
         * 左右子树
         */
        Node left, right;
        /**
         * 这个子树中的节点总数
         */
        int n;
        /**
         * 由其父节点指向它的链接的颜色
         */
        Color color;

        Node(Key key, Value val, int n, Color color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }

    /**
     * 链接的颜色
     */
    private enum Color {
        RED, BLACK;
    }

    /**
     * 判断到达当前Node的链接是否为红色
     *
     * @param n 节点信息
     * @return true - 红色; false - 黑色。
     */
    private boolean isRed(Node n) {
        if (n == null) {
            return false;
        }
        return n.color == Color.RED;
    }

    /**
     * 左旋操作
     *
     * @param n 需要旋转的节点
     * @return 旋转后的节点
     */
    private Node rotateLeft(Node n) {
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        x.color = n.color;
        n.color = Color.RED;
        x.n = n.n;
        n.n = 1 + size(n.left) + size(n.right);
        return x;
    }

    /**
     * 右旋操作
     *
     * @param h 需要旋转的node
     * @return 旋转后的node
     */
    private Node rotateRight(Node h) {
        Node left = h.left;
        h.left = left.right;
        left.right = h;
        left.color = h.color;
        h.color = Color.RED;
        left.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return left;

    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        // 始终保持根节点为黑色
        root.color = Color.BLACK;
    }

    /**
     * 删除最小值
     */
    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = Color.RED;
        }

        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = Color.BLACK;
        }
    }

    /**
     * 删除最小节点，最小节点始终应该处于左子树上，当左子树为null的时候，就表示找到了最小节点
     *
     * @param n 需要删除最小值的节点
     * @return 删除后的节点
     */
    public Node deleteMin(Node n) {
        if (n.left == null) {
            return null;
        }

        // 代表当前节点不是一个4-节点
        if (!isRed(n.left) && !isRed(n.left.left)) {
            n = moveRedLeft(n);
        }
        n.left = deleteMin(n.left);
        return balance(n);
    }

    /**
     * 删除某个key
     *
     * @param key 需要比较的key
     */
    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = Color.RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = Color.BLACK;
        }
    }

    public Node delete(Node n, Key key) {
        // key与当前node进行比较
        int cmp = n.key.compareTo(key);
        if (cmp < 0) {
            // 左子树, 判断颜色
            if (!isRed(n.left) && !isRed(n.left.left)) {
                n = moveRedLeft(n);
            }
            n.left = delete(n.left, key);
        } else {
            if (isRed(n.left)) {
                n = rotateRight(n);
            }
            if (key.compareTo(n.key) == 0 && n.right == null) {
                return null;
            }
            if (!isRed(n.right) && !isRed(n.right.left)) {
                n = moveRedRight(n);
            }
            if (key.compareTo(n.key) == 0) {
                n.val = get(n.right, min(n.right).key);
                n.key = min(n.right).key;
                n.right = deleteMin(n.right);
            } else {
                n.right = delete(n.right, key);
            }
        }
        return balance(n);
    }

    /**
     * 获取树中的最小值
     *
     * @param n 需要遍历的节点
     * @return 最小值所在的节点
     */
    private Node min(Node n) {
        if (n == null) {
            return null;
        }

        Node cur = n;
        while (true) {
            if (cur.left == null) {
                return cur;
            } else {
                cur = cur.left;
            }
        }
    }

    /**
     * 获取对应的value值
     *
     * @param n   需要查看的节点
     * @param key 遍历的key
     * @return key所对应的value的值
     */
    private Value get(Node n, Key key) {
        if (n == null || key == null) {
            return null;
        }

        Node cur = n;
        while (cur != null) {
            int cmp = cur.key.compareTo(key);
            if (cmp == 0) {
                return cur.val;
            } else if (cmp < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    /**
     * 删除最大节点
     */
    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = Color.RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = Color.BLACK;
        }
    }

    private Node deleteMax(Node n) {
        // 如果左节点是红色, 表示n是一个3-节点, 因此需要向4-节点转换
        if (isRed(n.left)) {
            n = rotateRight(n);
        }

        // 代表n.right为最大值
        if (n.right == null) {
            return null;
        }

        // 左节点不为红色，表示当前n节点为2-节点, 此时要构造4-节点
        if (!isRed(n.right) && !isRed(n.right.left)) {
            n = moveRedRight(n);
        }
        n.right = deleteMax(n.right);
        return balance(n);

    }

    private Node moveRedRight(Node n) {
        // 假设节点n为红色, h.right和h.right.left都是黑色,
        // 将h.right或者h.right的子节点之一变成红色
        flipColors(n);
        if (!isRed(n.left.left)) {
            n = rotateRight(n);
        }
        return n;
    }

    /**
     * 重新平衡树
     *
     * @param h 需要平衡的节点
     * @return 平衡后的节点
     */
    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }

        // 判断左节点与做节点的左节点，是否都是为红色
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * 节点一到左侧节点
     *
     * @param n 需要移动的节点
     * @return 移动后的节点
     */
    private Node moveRedLeft(Node n) {
        // 假设节点n为红色, h.left和h.left.left都是黑色,
        // 将h.left或者h.left的子节点之一变成红色
        flipColors(n);
        if (isRed(n.right.left)) {
            n.right = rotateRight(n.right);
            n = rotateLeft(n);
        }
        return n;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, 1, Color.RED);
        }

        // 和当前节点的key进行比较，
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }

        // 判断左节点与右节点的颜色
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        // 判断左节点与做节点的左节点，是否都是为红色
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * 转换一个节点的两个子节点的颜色
     *
     * @param h 需要变换颜色的节点
     */
    private void flipColors(Node h) {
        h.color = Color.RED;
        h.left.color = Color.BLACK;
        h.right.color = Color.BLACK;
    }


    /**
     * 获取子树所包含的节点数量
     *
     * @param n 节点
     * @return 子树节点数量
     */
    private int size(Node n) {
        if (n != null) {
            return n.n;
        }
        return 0;
    }
}
