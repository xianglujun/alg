package com.learn.alg.leetcode.dp;

/**
 * <pre>
 *     给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票
 * </pre>
 */
public class MaxProfit {

    /**
     * 该实现方式采用了动态规划的方式实现，通过定义一个二位数组，长度分别为天数和买入、卖出的标识。
     * <pre>
     *     // 当天不做任何操作, 应该为上一天的不做操作与当天卖出的最大值
     *     dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
     *     dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
     * </pre>
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        // 初始化起始的值
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 通过0和1的方式，判断是买入还是卖出
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        // 获取最后的最大值
        return dp[n - 1][0];
    }

    /**
     * 该方法不适用动态规划的方式, 采用直接计算的方式
     * <pre>
     *     改题目中主要求解的是,
     * </pre>
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int maxSum = 0;
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if (price < minPrice) {
                minPrice = price;
            }

            int sum = price - minPrice;
            if (maxSum < sum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }

}
