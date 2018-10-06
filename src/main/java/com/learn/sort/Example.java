package com.learn.sort;

import com.learn.io.StdOut;

/**
 * @author xianglujun
 * @datetime 2018/8/2510:26
 * @project alg
 * @since 1.0
 */
public abstract class Example {

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;

    }

    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected static void show(Comparable[] a) {
        for (Comparable b : a) {
            StdOut.print(b + " ");
        }
        StdOut.println();
    }

    protected static boolean isSorted(Comparable[] a) {
        for (int i = 1, len = a.length; i < len; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }
}
