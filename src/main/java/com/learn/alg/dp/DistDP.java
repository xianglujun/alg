package com.learn.alg.dp;

/**
 * 状态转移表法实现:
 * 根据动态实现最短路径信息
 */
public class DistDP {

    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        // 初始化第0行的数据
        for (int j = 0; j < n; ++j) {
            sum += matrix[0][j];
            states[0][j] = sum;
        }

        // 初始化第一列
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += matrix[i][0];
            states[i][0] = sum;
        }

        // 初始化状态
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                states[i][j] = matrix[i][j]
                        + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }
        return states[n - 1][n - 1];
    }
}
