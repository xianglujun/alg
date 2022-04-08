package com.learn.alg.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 实现树的后续遍历
 * <pre>
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 *
 * 树的后续遍历顺序为: 左子树节点 -- > 右子树节点 ---> 根节点
 *
 * <img src="https://assets.leetcode.com/uploads/2020/08/28/pre1.jpg" />
 *
 * 示例:
 * 输入：root = [1,null,2,3]
 * 输出：[3,2,1]
 * </pre>
 */
public class PostOrderTraversal {
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

    /**
     * 树的后续遍历:
     * <pre>
     *     还是采用递归的实现方法, 递归的实现方法中,
     *     时间复杂度为: O(N)
     *     空间复杂度为: O(N)
     * </pre>
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> order = new ArrayList<>();
        postorderTraversal(root, order);
        return order;
    }

    public void postorderTraversal(TreeNode root, List<Integer> order) {
        // 递归结束条件
        if (root == null) {
            return;
        }

        // 遍历左子树节点
        postorderTraversal(root.left, order);
        // 遍历右子树
        postorderTraversal(root.right, order);
        // 遍历根节点
        order.add(root.val);
    }

    /**
     * 解法二:
     * <pre>
     *     采用迭代的方式遍历数据, 迭代的方式算法:
     *     时间复杂度为: O(N)
     *     空间复杂度为: O(n)
     * </pre>
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> orders = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode prevNode = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }

            // 程序走到这里, 说明树的左子树已经遍历完成
            root = stack.pop();
            // 1. 判断root节点是否包含了右子树, 如果不包含，则直接压入结果即可
            // 2. 如果已经遍历过了, 则将当前节点直接压栈
            if (root.right == null || root.right == prevNode) {
                // 当root.right == null时, 表示root节点不需要遍历右子树, 因此可以直接存入结果
                orders.add(root.val);
                prevNode = root;
                root = null;
            } else {
                // 当root节点包含了右子树, 并且右子树没有遍历过时, 将当前节点入栈，并开始遍历右子树
                stack.add(root);
                root = root.right;
            }
        }

        return orders;
    }
}
