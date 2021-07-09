package com.learn.alg.sort.heap;

import java.util.Arrays;

/**
 * 该算算法实现堆排序：下沉排序
 * 该算法需要少于2N次比较，以及少于N次交换
 */
public class HeapMinSort {

    public static void main(String[] args) {
        Integer[] items = new Integer[10];
        items[1] = 5;
        items[2] = 4;
        items[3] = 6;
        items[4] = 3;
        items[5] = 10;
        items[6] = 1;
        items[7] = 2;
        items[8] = 3;
        items[9] = 4;
        HeapMinSort sort = new HeapMinSort();
        sort.sort(items);

        System.out.println(Arrays.toString(items));
    }

    /**
     * 排揎当前算法
     * @param items
     */
    public void sort(Comparable[] items) {
        // 获取数据的长度
        int len = items.length-1;
        for (int k = len/2; k > 0; k--) {
            // 将元素按照从右到做的顺序，执行下沉操作
            sink(items, k, len);
        }

        // 遍历元素，并将所有元素下沉
        while (len > 1) {
            exch(items, 1, len--);
            sink(items, 1, len);
        }
    }

    private void sink(Comparable[] items, int k, int len) {
        int j = 0;
        while (2*k <= len) {
            j = 2*k;
            if (j < len && less(items, j, j+1)) {
                j++;
            }
            if (!less(items, k, j)) {
                break;
            }

            // 交换元素
            exch(items, k, j);
            k = j;
        }
    }

    private void exch(Comparable[] items, int k, int j) {
        Comparable item = items[k];
        items[k] = items[j];
        items[j] = item;
    }

    private boolean less(Comparable[] items, int j, int i) {
        return items[j].compareTo(items[i]) < 0;
    }
}
