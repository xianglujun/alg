package com.learn.alg.dp;

/**
 * 动态规划中的背包问题:
 * <pre>
 *     有如下重量的物品: {2，2，4，6，3}
 *     价值为: {3，4，8，9，6}
 *     带有物品的数量5
 *     背包能够装的重量为9
 * </pre>
 */
public class KnapsackValue {

    /**
     * 计算背包能够装的物品数量, 找出最大价值
     *
     * @param weight 背包的重量
     * @param values 对应的价值
     * @param n      含有物品的数量
     * @param w      背包最大承受重量
     * @return 物品的个数
     */
    public static int knapsack(int[] weight, int[] values, int n, int w) {
        int[][] states = new int[n][w + 1];
        // 初始化0行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w; j++) {
                states[i][j] = -1;
            }
        }
        // 将0,0设置为0
        states[0][0] = 0;
        // 开始初始化状态数据
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < w; j++) {
                // 不加入i行数据
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            // 加入i行数据
            for (int j = 0; j < w - weight[i]; j++) {
                if (states[i - 1][j] >= 0) {
                    int v = values[i] + states[i - 1][j];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }

        // 找出最大值
        int maxValue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxValue) {
                maxValue = states[n - 1][j];
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] items = {2, 2, 4, 6, 3};
        // 物品的重量
        int[] value = {3, 4, 8, 9, 6};
        // 物品的价值
        int n = 5;
        // 物品个数
        int w = 9;
        // 背包承受的最大重量
        int maxValue = knapsack(items, value, n, w);
        System.out.println("最大价值为: " + maxValue);
    }
}
