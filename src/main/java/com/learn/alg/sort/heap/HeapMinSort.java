package com.learn.alg.sort.heap;

import java.util.Arrays;

/**
 * 该算算法实现堆排序：下沉排序
 * 该算法需要少于2N次比较，以及少于N次交换
 * 这种方式的实现，没有使用到0为止，因此：
 * 对应的计算公式位：
 * 左子树: 2 * i
 * 右子树: 2 * i + 1
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
        // 这一步的目的，将数组调整为大顶堆/小顶堆
        // 因为数组的位置根据2 * i 和 2 * i + 1 来实现的，因此可以从数组中间开始实现大顶堆/小顶堆
        int len = items.length - 1;
        for (int k = len / 2; k > 0; k--) {
            // 将元素按照从右到左的顺序，执行下沉操作
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
        while (2 * k <= len) {
            // 左孩子节点
            j = 2 * k;
            // 计算左孩子和右孩子的大小, 取最大孩子节点
            if (j < len && less(items, j, j + 1)) {
                j++;
            }

            // 将最大的节点与当前节点值比较,
            // 如果当前的值比最大值还要大，则不需要下沉
            if (!less(items, k, j)) {
                break;
            }

            // 如果当前k位置的值比最大的位置小，则交换元素
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
