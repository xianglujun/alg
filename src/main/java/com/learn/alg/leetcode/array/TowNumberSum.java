package com.learn.alg.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 求解两个数之和:
 * <pre>
 *     给定数组[2,7,5,3,1]
 *     给定数字9
 *     从数组中找出两个数的和, 并给出两个数对应下标[0,1]
 * </pre>
 */
public class TowNumberSum {

    /**
     * 采用暴力破解发, 遍历数组所有元素，然后结算处结果. 该结果时间复杂度为O(N^2)
     *
     * @param nums   数字列表
     * @param target 目标值
     * @return 两个数之和的下标
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int duration = target - nums[i];
            if (numsMap.containsKey(duration)) {
                return new int[]{numsMap.get(duration), i};
            } else {
                numsMap.putIfAbsent(nums[i], i);
            }
        }

        return null;
    }
}
