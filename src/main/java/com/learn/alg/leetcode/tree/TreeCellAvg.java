package com.learn.alg.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * <pre>
 * 输入： 3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 * </pre>
 * 输出：[3, 14.5, 11] 解释： 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 */
public class TreeCellAvg {

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

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        return hfs(root);
    }

    /**
     * 广度优先遍历
     *
     * @param root 将属性结构按照层级结构横向遍历
     */
    private List<Double> hfs(TreeNode root) {
        // 使用queue存储当前正在遍历的节点数量
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);

        List<Double> sums = new ArrayList<>(16);
        while (!nodes.isEmpty()) {
            // 获取当前的size
            int size = nodes.size();
            Double sum = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.poll();
                sum += node.val;
                TreeNode left = node.left;
                TreeNode right = node.right;
                if (left != null) {
                    nodes.offer(left);
                }
                if (right != null) {
                    nodes.offer(right);
                }
            }
            sum = sum / size;
            sums.add(sum);
        }

        return sums;
    }

    /**
     * 深度优先, 深度优先根据遍历的层数来确定当前数量和数的和
     *
     * @param root 需要遍历的根节点
     * @return 平均值
     */
    private List<Double> averageOfLevelsForDfs(TreeNode root) {
        List<Integer> counts = new ArrayList<>();
        List<Double> sums = new ArrayList<>();

        dfs(root, 0, counts, sums);

        // 计算平均值
        List<Double> averages = new ArrayList<>();
        for (int i = 0; i < sums.size(); i++) {
            Double sum = sums.get(i);
            Integer count = counts.get(i);
            averages.add(sum / count);
        }

        return averages;
    }

    private void dfs(TreeNode node, int level, List<Integer> counts, List<Double> sums) {
        if (node == null) {
            return;
        }

        // 判断当前的层级是否存在
        int size = counts.size();
        if (level >= size) {
            counts.add(1);
            sums.add((double) node.val);
        } else {
            Integer count = counts.get(level);
            count++;
            counts.set(level, count);
            Double sum = sums.get(level);
            sum += node.val;
            sums.set(level, sum);
        }

        // 遍历左节点
        dfs(node.left, level + 1, counts, sums);
        dfs(node.right, level + 1, counts, sums);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
            3,
            new TreeNode(9),
            new TreeNode(20,
                new TreeNode(15), new TreeNode(7))
        );
        TreeCellAvg avg = new TreeCellAvg();
        List<Double> sums = avg.averageOfLevels(root);
        for (Double d : sums) {
            System.out.print(d);
            System.out.print(" ");
        }
    }
}
