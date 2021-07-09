package com.learn.alg.sort;

import java.util.Comparator;

/**
 * TODO
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/7/9 23:42
 */
public abstract class AbstractSort<T extends Comparable> implements Sort<T> {

    @Override
    public void exch(T[] items, int i, int k) {
        T tmp = items[i];
        items[i] = items[k];
        items[k] = tmp;
    }

    @Override
    public boolean less(T[] items, int i, int k, Comparator<T> comparator) {
        return comparator.compare(items[i], items[k]) < 0;
    }
}
