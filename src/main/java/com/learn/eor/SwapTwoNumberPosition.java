package com.learn.eor;

import java.util.Arrays;

/**
 * 交换两个数的位置
 */
public class SwapTwoNumberPosition {

    public void swap(int a, int b) {
        System.out.println(a + "," + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a + "," + b);
    }

    public void swap(int[] arr, int a, int b) {
        System.out.println(Arrays.toString(arr));
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        SwapTwoNumberPosition swapTwoNumberPosition = new SwapTwoNumberPosition();
        swapTwoNumberPosition.swap(2, 5);

        swapTwoNumberPosition.swap(new int[]{3, 4, 5, 6}, 1, 2);
    }
}
