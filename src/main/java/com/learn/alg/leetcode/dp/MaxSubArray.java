package com.learn.alg.leetcode.dp;

/**
 * 53题: 最大数组和
 * <pre>
 *     给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。子数组 是数组中的一个连续部分。
 * </pre>
 */
public class MaxSubArray {
    /**
     * 该算法采用了动态规划的方式，其中采用了数组记录中间的结果值
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        // 初始化第一个元素的值
        dp[0] = nums[0];

        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            // 判断上一个索引位的值与当前索引位的值和之间的最大值
            int num = nums[i];
            int sum = num + dp[i - 1];
            sum = Math.max(num, sum);
            if (result < sum) {
                result = sum;
            }
            dp[i] = sum;
        }
        return result;
    }

    /**
     * 采用动态规划的方式，但是不降值记录到数组中
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {

        int pre = nums[0];
        int result = pre;
        for (int i = 1; i < nums.length; i++) {
            int sum = Math.max(nums[i] + pre, nums[i]);
            if (sum > result) {
                result = sum;
            }
            pre = sum;
        }
        return result;
    }
}
