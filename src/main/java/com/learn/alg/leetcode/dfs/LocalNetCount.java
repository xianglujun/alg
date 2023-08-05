package com.learn.alg.leetcode.dfs;

import java.util.Scanner;

/**
 * 能组成局域网计算机的数量
 */
public class LocalNetCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println(getResult(matrix, n, m));
    }

    private static int getResult(int[][] matrix, int n, int m) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dfs(matrix, i, j, n, m, 0));
            }
        }
        return ans;
    }

    /**
     * 深度优先遍历
     *
     * @param matrix
     * @param i
     * @param j
     * @return
     */
    private static int dfs(int[][] matrix, int i, int j, int n, int m, int count) {
        if (i < 0 || i >= n || j < 0 || j >= m || matrix[i][j] == 0) {
            return count;
        }
        count++;
        matrix[i][j] = 0; // 能够联通的，标记为0，防止重复标记
        count = dfs(matrix, i - 1, j, n, m, count); // 上方
        count = dfs(matrix, i + 1, j, n, m, count); // 下方
        count = dfs(matrix, i, j - 1, n, m, count); // 左方
        count = dfs(matrix, i, j + 1, n, m, count); // 右方
        return count;
    }
}
