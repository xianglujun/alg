package com.learn.alg.sort.select;

import com.learn.alg.sort.AbstractSort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 选择排序: 选择排序是左边已经排定的顺序，只会将最大最小的元素向左边移动
 * <ul>
 *     <li>关于插入排序，有以下两点需要注意:</li>
 *     <li>运行时间与输入无关，不管是否输入的数组是否有序，都需要执行相同的时间</li>
 *     <li>数据移动最少，对于选择排序，最多N次交换</li>
 *     <li>选择需要执行N^2/2次比较</li>
 * </ul>
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/7/9 23:37
 */
public class SelectSort<T extends Comparable> extends AbstractSort<T> {

    @Override
    public void sort(T[] items, Comparator<T> comparator) {
        if (items == null || items.length == 0) {
            return;
        }

        for (int i = 0; i < items.length; i++) {
            int min = i;
            for (int j = i + 1; j < items.length; j++) {
                if (less(items, j, min, comparator)) {
                    min = j;
                }
            }
            exch(items, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{7, 6, 5, 4, 3, 23, 45};
        SelectSort<Integer> selectSort = new SelectSort<Integer>();
        selectSort.sort(items, Comparator.reverseOrder());

        System.out.println(Arrays.toString(items));
    }
}
