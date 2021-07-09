package com.learn.alg.sort.insert;

import com.learn.alg.sort.AbstractSort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 插入排序:
 * <p>插入排序的特点是: 当前索引左边的元素已经处于有序状态, 在遍历当前索引右边的数组时, 需要和左边的进行比较，并插入到合适位置</p>
 * <ul>
 *     <li>对于随机排列的长度为N且主键不重复的数组, 平均情况下插入排序需要N^2/4次比较以及N^2/4次交换</li>
 *     <li>最坏情况下需要N^2/2次比较以及N^2/2次交换</li>
 * </ul>
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/7/10 0:01
 */
public class InsertSort<T extends Comparable> extends AbstractSort<T> {

    @Override
    public void sort(T[] items, Comparator<T> comparator) {
        if (items == null || items.length == 0 || comparator == null) {
            return;
        }

        for (int i = 1; i < items.length; i++) {
            for (int j = i; j > 0 && less(items, j, j - 1, comparator); j--) {
                exch(items, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{7, 6, 5, 4, 3, 23, 45};
        InsertSort<Integer> selectSort = new InsertSort<Integer>();
        selectSort.sort(items, Comparator.reverseOrder());

        System.out.println(Arrays.toString(items));
    }
}
