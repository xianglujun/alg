package com.learn.alg.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * <pre>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 * </pre>
 */
public class TreeMaxLength {
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
     * 这种实现的是深度优先算法:
     * <pre>
     *     深度优先算法是一层一层的遍历, 然后取左子树与右子树的深度最大值
     *     在这个算法中:
     *     时间复杂度为: O(N)
     *     空间复杂度为: O(height)
     *     其中height是指树的深度
     * </pre>
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 解法二中采用的广度优先算法(BFS),
     * <pre>
     *     其中算法性能为:
     *     时间复杂度为： O(N)
     *     空间复杂度为: O(N)
     * </pre>
     *
     * @param root
     * @return
     */
    public int maxDept(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int dept = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 这里为什么不判断队列是否为空, 是因为队列在处理数据的同事
            // 还是存放下一层级的节点列表, 如果直接判断空, 则可能会导致结果不正确
            while (size-- > 0) {
                // 判断是否包含了左子树
                TreeNode n = queue.poll();
                if (n.left != null) {
                    queue.offer(n.left);
                }

                if (n.right != null) {
                    queue.offer(n.right);
                }
            }
            dept++;
        }

        return dept;
    }

}
