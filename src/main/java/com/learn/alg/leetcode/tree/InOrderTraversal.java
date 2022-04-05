package com.learn.alg.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历二叉树
 * <p>
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * <pre>
 *     <img src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" />
 *     输入：root = [1,null,2,3]
 *     输出：[1,3,2]
 * </pre>
 */
public class InOrderTraversal {
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
     * 这种方式中序遍历树, 采用递归的方式遍历.
     * <pre>
     *     这种遍历方式，时间与空间复杂度如下：
     *     时间复杂度: O(N)
     *     空间复杂度: O(N)
     * </pre>
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    public void inorderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left, result);
        result.add(node.val);
        inorderTraversal(node.right, result);
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // 队列用于存储还没有处理的节点
        Stack<TreeNode> queue = new Stack<>();
        while (root != null || !queue.isEmpty()) {
            while (root != null) {
                queue.push(root);
                root = root.left;
            }

            // 当执行到异步时, 说明root == null, 则需要遍历右节点
            root = queue.pop();
            result.add(root.val);
            root = root.right;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null,
                new TreeNode(2, new TreeNode(3), null));

        List<Integer> result = new InOrderTraversal().inorderTraversal2(root);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
