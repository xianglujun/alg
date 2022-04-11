package com.learn.alg.leetcode.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 给你一个整数数组 nums，请你将该数组升序排列。
 */
public class SortArray {

    public int[] sortArray(int[] nums) {
        int length = nums.length;
        if (length < 15) {
            insertSort(nums);
        } else {
            quickSort(nums, 0, nums.length - 1);
        }
        return nums;
    }

    /**
     * 采用快速排序
     *
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    private void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 获取分区的索引
        int base = randomSizePartition(nums, lo, hi);
        quickSort(nums, lo, base - 1);
        quickSort(nums, base + 1, hi);
    }

    private int randomSizePartition(int[] nums, int lo, int hi) {
        // 随机选择一个元素作为主元base
        int i = new Random().nextInt((hi - lo + 1)) + lo;
        exch(nums, hi, i);
        return partition(nums, lo, hi);
    }

    private int partition(int[] nums, int lo, int hi) {

        int base = nums[hi];
        int i = lo - 1;
        for (int j = lo; j <= hi - 1; j++) {
            if (nums[j] <= base) {
                i = i + 1;
                exch(nums, i, j);
            }
        }
        exch(nums, i + 1, hi);
        return i + 1;
    }

    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] items = new int[]{-74, 48, -20, 2, 10, -84, -5, -9, 11, -24, -91, 2, -71, 64, 63, 80, 28, -30, -58, -11, -44, -87, -22, 54, -74, -10, -55, -28, -46, 29, 10, 50, -72, 34, 26, 25, 8, 51, 13, 30, 35, -8, 50, 65, -6, 16, -2, 21, -78, 35, -13, 14, 23, -3, 26, -90, 86, 25, -56, 91, -13, 92, -25, 37, 57, -20, -69, 98, 95, 45, 47, 29, 86, -28, 73, -44, -46, 65, -84, -96, -24, -12, 72, -68, 93, 57, 92, 52, -45, -2, 85, -63, 56, 55, 12, -85, 77, -39};
        SortArray quickSort = new SortArray();
        quickSort.sortArray(items);

        System.out.println(Arrays.toString(items));
    }
}
