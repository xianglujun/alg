package com.learn.alg.sort.quick;

import com.learn.alg.sort.AbstractSort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 快速排序算法:
 * 快速排序也是采用了分治思想，将数组切分为不同的小数组, 然后实行排序
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/7/11 11:58
 */
public class QuickSort<T extends Comparable> extends AbstractSort<T> {

    @Override
    public void sort(T[] items, Comparator<T> comparator) {
        sort(items, 0, items.length - 1, comparator);
    }

    /**
     * 排序指定范围
     *
     * @param items 需要排序数组
     * @param lo    低位索引
     * @param hi    高位索引
     */
    private void sort(T[] items, int lo, int hi, Comparator<T> comparator) {
        // 排序结束条件
        if (lo >= hi) {
            return;
        }

        int j = partition(items, lo, hi, comparator);
        sort(items, lo, j, comparator);
        sort(items, j + 1, hi, comparator);
    }

    /**
     * 将数组分区:
     * 数组分区则是根据低位，将小于lo的元素放入到左边，大于lo的元素放入到右边
     *
     * @param items 需要排序数组
     * @param lo    低位索引
     * @param hi    高位索引
     * @return 分区后低位所在索引位置
     */
    private int partition(T[] items, int lo, int hi, Comparator<T> comparator) {
        if (lo >= hi) {
            return lo;
        }
        // 选取第一个元素
        T v = items[lo];
        int j = hi + 1;
        int i = lo;
        while (true) {
            // 扫描左右，并检查扫描是否结束并交换元素
            while (less(items[++i], v, comparator)) {
                if (i == hi) {
                    break;
                }
            }

            // 判断右边的元素是否都比当前v大，如果不是，则交换
            while (less(v, items[--j], comparator)) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            exch(items, i, j);
        }
        exch(items, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{7, 45, 5, 4, 3, 23, 6};
        QuickSort<Integer> quickSort = new QuickSort<Integer>();
        quickSort.sort(items, Comparator.reverseOrder());

        System.out.println(Arrays.toString(items));
    }
}
