package com.learn.alg.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *     给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 *    请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 *  例如:
 *  输入：nums = [4,3,2,7,8,2,3,1]
 *  输出：[5,6]
 * </pre>
 */
public class LoseNumber {

    /**
     * <pre>
     *     解法:
     *     因为数组的长度为n, 并且数组内的数字为[1,n], 因此我们遍历数组，
     *     并将每个元素-1, 再将-1后的值作为下标，更新数组对应的值，可以让其值为负数或者超过n, 然后再
     *     遍历数组，找出不为负数或者没有超过n的数组下标+1, 就是最终结果。
     *
     *     原理:
     *     例如下面的例子中, [4,3,2,7,8,2,3,1]
     *     在经过上面步骤修改之后, 最终结果为 [-4,-3,-2,-7,8,2,-3,-1]
     *     其实就只有下标为4和5的数字没有发生变化，根据我们上面的公式，如果要让4和5位置的值发生变化，数组中就必须包含5和6
     *     但是数组中没有，因此对应下标的值没有变动。也就是确实的数字。
     * </pre>
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        for (int i = 0; i < nums.length; i++) {

            // 此时数字可能被改过, 因此需要还原
            int index = (nums[i] < 0 ? -nums[i] : nums[i]) - 1;
            if (index >= 0 && nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        for (int i = 0, len = nums.length; i < len; i++) {

            int num = nums[i];
            int index = (num - 1) % len;
            nums[index] += len;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0, len = nums.length; i < len; i++) {
            if (nums[i] <= len) {
                result.add(i + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = new LoseNumber().findDisappearedNumbers2(nums);
        result.forEach(System.out::println);
    }
}
