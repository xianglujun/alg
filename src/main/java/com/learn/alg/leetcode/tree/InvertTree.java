package com.learn.alg.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode:226题
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * <pre>
 *     <img src="https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg" />
 *
 * 示例:
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * </pre>
 */
public class InvertTree {

    /**
     * 解法一:
     * <pre>
     *     采用递归的方式, 先交换左子树, 再交换右子树
     *
     *     当前的算法时间复杂度为： O(N)
     *     空间复杂度为: O(N)
     * </pre>
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 先反转左子树, 再翻转右子树
        invertTree(root.left);
        invertTree(root.right);

        TreeNode l = root.left;
        root.left = root.right;
        root.right = l;

        return root;
    }

    /**
     * 翻转二叉树:
     * <pre>
     *     该算法采用迭代的方式实现翻转二叉树, 该算法采用广度优先算法
     * </pre>
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode n = queue.poll();
                if (n.left != null) {
                    queue.offer(n.left);
                }

                if (n.right != null) {
                    queue.offer(n.right);
                }

                // 交换Node的左右子树节点
                TreeNode l = n.left;
                n.left = n.right;
                n.right = l;
            }
        }

        return root;
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
