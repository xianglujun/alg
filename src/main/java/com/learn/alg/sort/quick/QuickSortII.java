package com.learn.alg.sort.quick;

import java.util.Arrays;

/**
 * 快排实现
 */
public class QuickSortII {

    /**
     * 给定一个数，将数组中小于num的数放在左边，大于num的数放在右边
     *
     * @param arr 查找的数组
     * @param num 对应的数
     */
    public int splitNum(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        return process(arr, 0, arr.length - 1, num);
    }

    /**
     * 给定一个数字，将小于num的数字放在左边，相等的数字放在中间，大于Num的数字放在右边
     *
     * @param arr 数组
     * @param num 需要判定的num
     * @return 相等位置的开始左边和结束坐标
     */
    public int[] splitThreeNum(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return new int[]{0, 0};
        }
        return processThree(arr, 0, arr.length - 1, num);
    }

    /**
     * 数字的选择，则使用随机的数字选择
     *
     * @param arr 需要排序的数组
     * @param l   左索引
     * @param r   右索引
     * @return 等于value开始和结束索引
     */
    public int[] netherLandFlags(int[] arr, int l, int r) {
        return new int[]{0, 0};
    }

    private int[] processThree(int[] arr, int l, int r, int num) {
        int left = l - 1, right = r + 1;
        while (l < right) {
            // 如果小于num,则与left + 1的位置进行交换，并右移left
            if (arr[l] < num) {
                int temp = arr[left + 1];
                arr[++left] = arr[l];
                arr[l++] = temp;
            } else if (arr[l] > num) {
                // 当大于num时, 则将right位与l位置元素交换, 并左移right索引位
                int temp = arr[right - 1];
                arr[--right] = arr[l];
                arr[l] = temp;
            } else {
                // 与num相等则继续后移
                l++;
            }
        }
        return new int[]{left + 1, right};
    }

    private int process(int[] arr, int l, int r, int num) {
        int leftMin = l - 1;
        // 开始遍历
        while (l <= r) {
            if (arr[l] < num) {
                swap(arr, l, ++leftMin);
            }
            l++;
        }
        return leftMin;
    }

    private static void swap(int[] arr, int l, int leftMin) {
        int temp = arr[leftMin];
        arr[leftMin] = arr[l];
        arr[l] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 2, 5, 7, 2, 3};

        QuickSortII quickSort = new QuickSortII();
        System.out.println(quickSort.splitNum(arr, 4));
        System.out.println(Arrays.toString(arr));

        arr = new int[]{1, 4, 2, 5, 7, 2, 3};
        System.out.println(Arrays.toString(quickSort.splitThreeNum(arr, 4)));
        System.out.println(Arrays.toString(arr));
    }
}
