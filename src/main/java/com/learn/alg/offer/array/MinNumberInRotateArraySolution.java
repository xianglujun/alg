package com.learn.alg.offer.array;

import org.junit.Assert;

/**
 * 给定旋转数组, 从旋转数组中获取最小值(二分查找法: O(logN))
 *
 * 前提条件：
 * 1. 数组部分有序
 * 2. 旋转前的数组为升序数组
 * 3.
 *
 * @author xianglujun
 * @date 2019/1/11 12:01
 */
public class MinNumberInRotateArraySolution {

    public int solution(int[] array) {
        if (null == array || array.length == 0) {
            return 0;
        }

        int p1 = 0;
        int p2 = array.length - 1;
        int mid = 0;

        while (array[p1] >= array[p2]) {
            if (p2 - p1 == 1) {
                break;
            }

            mid = (p1 + p2) / 2;

            if (array[p1] < array[mid]) {
                p1 = mid;
            } else {
                p2 = mid;
            }
        }

        return array[p2];
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,6,7,9,11,0,2,3,4};

        MinNumberInRotateArraySolution soluation = new MinNumberInRotateArraySolution();
        int min = soluation.solution(array);

        Assert.assertTrue(min == 0);
    }
}
