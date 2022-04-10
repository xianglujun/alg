package com.learn.alg.sort;

import java.util.Arrays;

/**
 * 冒泡排序实现方式
 */
public class PuppleSort {

    public static void main(String[] args) {
        int[] elements = new int[]{34, 12, 44, 56, 7, 80, 55, 224, 33, 22};
        sort(elements);
    }

    private static void sort(int[] elements) {
        if (elements == null || elements.length == 0) {
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length - 1 - i; j++) {
                if (elements[i] > elements[j]) {
                    int temp = elements[i];
                    elements[i] = elements[j];
                    elements[j] = temp;
                }
                System.out.println(Arrays.toString(elements));
            }
            System.out.println("---------------------");
        }
    }
}
