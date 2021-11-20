package com.learn.sort;

import com.learn.io.StdRandom;

/**
 * @author xianglujun
 * @datetime 2018/8/2516:39
 * @project alg
 * @since 1.0
 */
public class Selection extends Example {

    public static void sort(Comparable[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        int len = 21;
        Integer[] a = new Integer[len];
        for (int i = 0; i < len; i++) {
            a[i] = StdRandom.uniform(10000);
        }

        // 直接排序
        show(a);
        sort(a);
        show(a);
    }
}
