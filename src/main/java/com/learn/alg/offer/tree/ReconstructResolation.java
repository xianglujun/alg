package com.learn.alg.offer.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * 重构二叉树:
 * 给定二叉树的前序遍历结果为: {1,2,4,7,3,5,6,8}
 * 给定二叉树的后续遍历结果为: {4,7,2,1,5,3,8,6}
 *
 */
public class ReconstructResolation {

    /**
     * 重建二叉树
     * @param pre 前序遍历结果
     * @param in 中序排列结果
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (null == pre || null == in) {
            return null;
        }

        if (pre.length == 0 || in.length == 0) {
            throw new IllegalArgumentException("遍历结果集不正确!");
        }

        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    /**
     * 重构二叉树具体执行方法
     * @param pre 前序遍历结果集
     * @param preStart 开始遍历的节点
     * @param preEnd 前序遍历结束索引
     * @param in 中序遍历结果
     * @param inStart 中序遍历开始索引
     * @param inEnd 中序遍历结束索引
     * @return
     */
    private TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {

        // 对于前序而言, 第一个节点肯定为根节点
        TreeNode rootNode = new TreeNode(pre[preStart]);

        // 定位根节点在中序排序中位置
        int rootIndex = inStart;
        for (; rootIndex < inEnd; rootIndex++) {
            if (in[rootIndex] == rootNode.value) {
                break;
            }
        }

        // 确定左子树和右子树的区间
        int inLeft = rootIndex - inStart;
        int inRight = inEnd - rootIndex;

        // 表明左子树依然存在
        if (inLeft > 0) {
            rootNode.left = reConstructBinaryTree(pre, preStart + 1, preStart + inLeft, in, inStart, rootIndex - 1);
        }

        if (inRight > 0) {
            // 对于
            rootNode.right = reConstructBinaryTree(pre, preStart + inLeft + 1, preEnd, in, rootIndex + 1, inEnd);
        }

        return rootNode;
    }

    @Getter
    @Setter
    public static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value =  value;
        }
    }
}
