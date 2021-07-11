package com.learn.alg.sort.merge;

import com.learn.alg.sort.AbstractSort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 归并排序:
 * 归并排序采用了分支思想, 将数组按照规则拆分
 * <li>
 * 对于任意N, 归并排序需要 NlgN/2至 NlgN次比较
 * </li>
 * <li>
 * 对于任意N， 自底向上归并需要至多6NlgN次访问数组
 * </li>
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/7/10 22:59
 */
public class MergeSort<T extends Comparable> extends AbstractSort<T> {

    private Comparable[] aux;

    @Override
    public void sort(T[] items, Comparator<T> comparator) {
        if (items == null || items.length == 0 || comparator == null) {
            return;
        }

        aux = new Comparable[items.length];
        // 执行排序操作
        sort(items, 0, items.length - 1, comparator);
    }

    public void sort(T[] items, int lo, int high, Comparator<T> comparator) {
        // 排序操作
        if (lo >= high) {
            return;
        }

        int mid = lo + ((high - lo) >>> 1);
        sort(items, lo, mid, comparator);
        sort(items, mid + 1, high, comparator);
        // 执行合并操作
        merge(items, lo, mid, high, comparator);
    }

    /**
     * 执行合并操作
     *
     * @param items 需要排序的元素列表
     * @param lo    低位索引
     * @param mid   中间索引
     * @param high  高位索引
     */
    private void merge(T[] items, int lo, int mid, int high, Comparator<T> comparator) {
        // 第一步：将items[lo..high] 拷贝到 aux[lo..high]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= high; k++) {
            aux[k] = items[k];
        }

        // 执行合并操作
        for (int k = lo; k <= high; k++) {
            if (i > mid) {
                // 因为低位已经超过中线, 所以低位不再包含元素, 所以只拷贝高位就可以了
                items[k] = (T) aux[j++];
            } else if (j > high) {
                // 高位已经拷贝完成, 只需要拷贝低位就可以了
                items[k] = (T) aux[i++];
            } else if (less((T[]) aux, j, i, comparator)) {
                // 该处的比较是比较的缓存中元素, 因为缓存中元素是不会发生位置的变化的
                // 如果中位数两边, 右边小于左边，则交换两边元素, 因为采用了另外一个数组实现，因此只拷贝高位即可
                items[k] = (T) aux[j++];
            } else {
                // 相反, 低位小于高位, 则只拷贝低位即可
                items[k] = (T) aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{7, 6, 5, 4, 3, 23, 45};
        MergeSort<Integer> mergeSort = new MergeSort<Integer>();
        mergeSort.sort(items, Comparator.reverseOrder());

        System.out.println(Arrays.toString(items));
    }
}
