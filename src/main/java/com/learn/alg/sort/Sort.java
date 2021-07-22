package com.learn.alg.sort;

import java.util.Comparator;

/**
 * 对数据排序
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/7/9 23:34
 */
public interface Sort<T> {

    void sort(T[] items, Comparator<T> comparator);

    void exch(T[] items, int i, int k);

    boolean less(T[] items, int i, int k, Comparator<T> comparator);

    boolean less(T i, T k, Comparator<T> comparator);
}
