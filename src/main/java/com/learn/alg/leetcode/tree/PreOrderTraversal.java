package com.learn.alg.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 对二叉树实现前序遍历
 * <pre>
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * <img src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" />
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 * </pre>
 */
public class PreOrderTraversal {

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
     * 第一种方式:
     * <pre>
     *     采用递归的方式, 所谓前序遍历，则是先遍历根节点, 再遍历左子树, 再遍历右子树
     *
     *     通过递归的方式,
     *     时间复杂度为: O(N)
     *     空间复杂度为: O(N)
     * </pre>
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> orders = new ArrayList<>();
        preorderTraversal(root, orders);

        return orders;
    }

    public void preorderTraversal(TreeNode root, List<Integer> orders) {
        // 递归结束条件
        if (root == null) {
            return;
        }
        // 加入根节点
        orders.add(root.val);
        // 再遍历左子树
        preorderTraversal(root.left, orders);
        // 再遍历右子树
        preorderTraversal(root.right, orders);
    }

    /**
     * 第二种解法是采用迭代的方式, 主要解决方案为: 通过使用栈的方式做缓存, 然后当树返回的时候，从栈中获取数据即可
     *
     * <pre>
     *     这种解法中:
     *     时间复杂度为: o(n)
     *     空间复杂度为: o(n)
     * </pre>
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {

        List<Integer> orders = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                orders.add(root.val);
                if (root.right != null) {
                    stack.add(root.right);
                }
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
            }
        }

        return orders;
    }
}
