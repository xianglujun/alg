package com.learn.alg.leetcode.sort;

/**
 * <pre>
 *     给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * </pre>
 */
public class SingleNumber {

    /**
     * 136题解分析:
     * <pre>
     *     根据以上的题目可以知道, 只有一个元素只出现一次，其他的出现两次, 因此可以采用异或(^)的方式处理：
     *     因为异或的运算如下:
     *     1^1 = 0
     *     0^0 = 0
     *     1^0 = 1
     *
     *     因此当我们两个相同的数异或的时候，最终结果等于: 0
     *     即: a^a = 0
     * </pre>
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }

        return result;
    }
}
