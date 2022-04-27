package com.learn;

public class Example4 {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 2, 3};
        System.out.println(subLen(nums));
    }

    private static int subLen(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLen = 0;
        int curLen = 0;
        for (int i = 0; i < nums.length; i++) {
            curLen = 1;
            int pre = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j] && pre < nums[j]) {
                    curLen++;
                    pre = nums[j];
                }
            }
            if (curLen > maxLen) {
                maxLen = curLen;
            }
        }

        return maxLen;
    }
}
