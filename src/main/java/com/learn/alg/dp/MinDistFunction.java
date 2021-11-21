package com.learn.alg.dp;

/**
 * 状态转义方程法
 */
public class MinDistFunction {

    private int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    private int n = 4;
    private int[][] states = new int[n][n];

    public int minDist(int i, int j) {
        if (i == 0 && j == 0) {
            return states[i][i];
        }
        if (states[i][j] > 0) {
            return states[i][j];
        }

        int minLeft = Integer.MIN_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }

        int minUp = Integer.MIN_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }

        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        states[i][j] = currMinDist;
        return currMinDist;
    }
}
