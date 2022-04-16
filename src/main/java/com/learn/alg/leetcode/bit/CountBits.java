package com.learn.alg.leetcode.bit;

/**
 * leetcode 361题:
 * <p>
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 */
public class CountBits {
    /**
     * 解法一：
     * <pre>
     *     针对一个数字, 可以通过多次&的方式，计算1的数量:
     *
     *      例如:
     *      21 的二进制1的数量:
     *      1. 21 & 20 =
     *         10101
     *        &10100 = 10100 = 20;
     *      2. 20 & 19 = 10100 & 10010 = 10000 = 16;
     *      3. 16 & 15 = 10000 & 01111 = 0
     *
     * 因此可以看出, 当经过3次的计算后，可以得出21一共有3个1
     * </pre>
     *
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i < n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }

    /**
     * 采用奇偶性来实现:
     * <pre>
     * 例如: 计算21数字的二进制1的个数, 可以通过以下方式:
     * 8 --> 1000
     * 8/2=4 --> 100
     * 4/2=2 --> 10
     * 2/2 =1 --> 1
     *
     * 因此当我们/2的时候, 如果是偶数, 则获取一半的值即可. 如果是奇数，则是对应的偶数+1
     * </pre>
     *
     * @param n
     * @return
     */
    public int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 判断i的奇偶性
            bits[i] = ((i & 1) == 1) ? bits[i - 1] + 1 : bits[i >> 1];
        }
        return bits;
    }
}
