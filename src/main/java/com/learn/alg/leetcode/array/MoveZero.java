package com.learn.alg.leetcode.array;

import java.util.Arrays;

/**
 * 移动零:
 * <pre>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 例子:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 必须在不复制数组的情况下原地对数组进行操作。
 * </pre>
 */
public class MoveZero {

    /**
     * 移动零:
     * <pre>
     *  可以采用双指针的方法, 移动非零元素
     *
     *  这种实现方式因为要遍历一次整个数组, 因此时间复杂度为O(n)
     * </pre>
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        // 定义i, j 两个指针
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }

        for (; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {
        // 定义i, j 两个指针
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                int temp = nums[i];
                nums[i++] = nums[j];
                nums[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 0, 3, 12};
        new MoveZero().moveZeroes2(nums);
        System.out.println(Arrays.toString(nums));
    }
}
