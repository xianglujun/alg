package com.learn.alg.leetcode.sort;

/**
 * 给定数组[1,3,4,2,5]
 * <pre>
 *     1左边比1小的数，没有
 *     3左边比3小得数, 1
 *     4左边比4小得数, 1,3
 *     2左边比2小得数, 1
 *     5左边比5小的数, 1,3,4,2
 *
 *     则小和为：1 + 1 +3 +1 + 1 +3 +4 +2 = 16
 * </pre>
 */
public class SmallSum {

    public int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return smallSum(arr, 0, arr.length - 1);
    }

    private int smallSum(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        return smallSum(arr, l, mid) // 左边产生小和
                + smallSum(arr, mid + 1, r) // 右边产生小和
                + merge(arr, l, r, mid); // 在更大的范围产生小和
    }

    private int merge(int[] arr, int l, int r, int mid) {

        int sum = 0;
        int li = l, ri = mid + 1;
        int[] help = new int[r - l + 1];
        int i = 0;
        while (li <= mid || ri <= r) {
            if (li > mid) {
                help[i++] = arr[ri++];
            } else if (ri > r) {
                help[i++] = arr[li++];
            } else if (arr[li] < arr[ri]) {
                sum = sum + (r - ri + 1) * arr[li];
                help[i++] = arr[li++];
            } else {
                help[i++] = arr[ri++];
            }
        }
        for (int in = 0; in < help.length; in++) {
            arr[l + in] = help[in];
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new SmallSum().smallSum(new int[]{1, 3, 4, 2, 5}));
    }
}
