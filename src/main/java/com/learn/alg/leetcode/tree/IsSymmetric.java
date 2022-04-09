package com.learn.alg.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断树是否为对称二叉树
 */
public class IsSymmetric {

    public static class TreeNode {
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

    /**
     * 判断树是否为二叉树, 解法一：采用递归的实现方法
     * <pre>
     *  1. 判断左节点与右节点的值是否相等
     *  2. 判断左节点的左节点是否与右节点的右节点相等
     *  3. 判断左节点的有节点是否与有节点的左节点相等
     *
     *  该方法的时间复杂度如下:
     *  时间复杂度: O(N)
     *  空间复杂度: O(N)
     * </pre>
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return true;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * 通过迭代的方式实现
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {

        // 判断是否包含两个左右节点
        if (root.left == null && root.right == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode l = queue.poll();
            TreeNode r = queue.poll();

            if (l == null && r == null) {
                continue;
            } else if ((l == null || r == null) || l.val != r.val) {
                return false;
            }

            queue.offer(l.left);
            queue.offer(r.right);

            queue.offer(l.right);
            queue.offer(r.left);
        }

        return true;
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1,
//                new TreeNode(2,
//                        new TreeNode(3), new TreeNode(4)),
//                new TreeNode(2,
//                        new TreeNode(4), new TreeNode(3)));

        TreeNode root = new TreeNode(1, new TreeNode(2,
                null, new TreeNode(3)), new TreeNode(2,
                new TreeNode(3), null));

        boolean result = new IsSymmetric().isSymmetric(root);
        System.out.println(result);
    }
}
