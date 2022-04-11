package com.learn.alg.leetcode.sort;

/**
 * <pre>
 *     给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现三次。找出那个只出现了一次的元素。
 *
 * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 *
 * 示例 2:
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 * </pre>
 */
public class SingleNumberII {

    /**
     * 137题解分析:
     * <pre>
     *     由于只有一个元素出现一次，其他元素都出现了三次，因此我们可以考虑将其结果相加, 判断与3的余数，此时就可以得到一个数字的结果了
     *
     * 时间复杂度：O(nlogC)，其中 nn 是数组的长度，CC 是元素的数据范围，在本题中  logC=log2^32=32，也就是我们需要遍历第 0\sim310∼31 个二进制位。
     *
     * 空间复杂度：O(1)O(1)。
     * </pre>
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += (num >> i & 1);
            }
            if (total % 3 != 0) {
                result |= (1 << i);
            }
        }

        return result;
    }
}
