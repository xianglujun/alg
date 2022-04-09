package com.learn.alg.leetcode.tree;

/**
 * <pre>
 *     给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 *  一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * </pre>
 */
public class TreeIsBalance {

    /**
     * 判断树是否为高度平衡树, 该算法采用了递归的算法实现, 主要比较每一层的左右子树的节点高度有没有超过1层
     * <p>
     * 该实现方式主要实现了自底向上的递归方式.
     * <pre>
     *     该解法的时间复杂度如下:
     *     时间复杂度: O(N)
     *     空间复杂度: O(N)
     * </pre>
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return depth(root) != -1;
    }

    /**
     * 获取每个节点的深度信息
     *
     * @param root
     * @return
     */
    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }

    /**
     * 该方式采用了自顶向下的递归方式
     *
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced2(root.left)
                && isBalanced2(root.right);
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 为什么要+1? 因为层数没有算上当前这一层
        return Math.max(height(root.left), height(root.right)) + 1;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
