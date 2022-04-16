package com.learn.alg.leetcode.bit;

/**
 * 第461题:
 * <pre>
 *     两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 * </pre>
 */
public class HanmingDistance {
    /**
     * 通过^操作的方式, 可以求出两个数不相同的部分，然后通过x&(x-1)的方式，快速求出二进制中1的个数
     *
     * @param x
     * @param y
     * @return
     */
    public int hanmingDistance(int x, int y) {
        int distance = 0;
        for (int xor = x ^ y; xor != 0; xor &= (xor - 1)) {
            distance++;
        }

        return distance;
    }
}
