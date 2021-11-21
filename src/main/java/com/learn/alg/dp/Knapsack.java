package com.learn.alg.dp;

/**
 * 动态规划中的背包问题:
 * <pre>
 *     有如下重量的物品: {2，2，4，6，3}
 *     带有物品的数量5
 *     背包能够装的重量为9
 * </pre>
 */
public class Knapsack {

    /**
     * 计算背包能够装的物品数量
     *
     * @param weight 背包的重量
     * @param n      含有物品的数量
     * @param w      背包最大承受重量
     * @return 物品的个数
     */
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1];
        // 特殊对待第一行
        states[0][0] = true;
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }

        // 开始初始化物品
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j];
                }
            }
            // 把第i个物品放入背包
            for (int j = 0; j <= w - weight[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[n - 1][i]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 使用一维数组解决上述问题
     *
     * @param items 物品数量
     * @param n     个数
     * @param w     重量
     */
    public int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        if (items[0] < w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            // 此处可能导致重复计算问题
            // 因为重量始终都在增加的
            for (int j = w - items[i]; j >= 0; j--) {
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[i]) {
                return i;
            }
        }
        return 0;
    }

}
