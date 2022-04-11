package com.learn.alg.leetcode.sort;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，请你将该数组升序排列。该方法采用了最大堆的排序方法
 */
public class SortArrayMaxHeap {

    public int[] sortArray(int[] nums) {
        int length = nums.length;
        if (length < 15) {
            insertSort(nums);
        } else {
            heapSort(nums);
        }
        return nums;
    }

    private void heapSort(int[] nums) {
        int len = nums.length - 1;
        buildMaxHeap(nums, len);
        for (int i = len; i >= 1; --i) {
            exch(nums, i, 0);
            len -= 1;
            maxHeapify(nums, 0, len);
        }
    }

    private void buildMaxHeap(int[] nums, int len) {
        for (int i = len / 2; i >= 0; i--) {
            maxHeapify(nums, i, len);
        }
    }

    private void maxHeapify(int[] nums, int i, int len) {
        for (; (i << 1) + 1 < len; ) {
            int lson = (i << 1) + 1;
            int rson = (i << 1) + 2;

            int large;
            if (lson <= len && nums[lson] > nums[i]) {
                large = lson;
            } else {
                large = i;
            }

            if (rson <= len && nums[rson] > nums[large]) {
                large = rson;
            }

            if (large != i) {
                exch(nums, large, i);
                i = large;
            } else {
                break;
            }
        }
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
        SortArrayMaxHeap quickSort = new SortArrayMaxHeap();
        quickSort.sortArray(items);

        System.out.println(Arrays.toString(items));
    }
}
