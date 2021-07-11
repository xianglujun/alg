package com.learn.alg.sort.insert;

import com.learn.alg.sort.AbstractSort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 希尔排序:
 * 希尔排序是由插入排序变种而来，根据分组规则，将数组达到部分有序状态。
 * <li>
 * 使用递增序列1, 4, 13, 40, 121, 364..(3 * k + 1). 的希尔排序所需的比较次数不会超过N的若干倍乘以递增序列的长度.
 * </li>
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/7/10 11:33
 */
public class ShellSort<T extends Comparable> extends AbstractSort<T> {

    @Override
    public void sort(T[] items, Comparator<T> comparator) {
        if (items == null || items.length == 0 || comparator == null) {
            return;
        }

        int h = 1;
        int n = items.length;
        while (h < n / 3) {
            // 生成 1, 4, 13, 40, 121 , 364, 1093...序列
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(items, j, j - h, comparator); j -= h) {
                    exch(items, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{7, 6, 5, 4, 3, 23, 45};
        ShellSort<Integer> selectSort = new ShellSort<Integer>();
        selectSort.sort(items, Comparator.reverseOrder());

        System.out.println(Arrays.toString(items));
    }
}
