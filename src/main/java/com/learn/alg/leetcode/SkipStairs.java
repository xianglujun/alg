package com.learn.alg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 跳楼梯算法:
 * <pre>
 *     假设有n个楼梯，并且每次只能跳1个或者2个楼梯，计算n各楼梯有几种方式
 *
 *     分析：
 *     假设有0个楼梯，则只有1中方式
 *     假设有1个楼梯，则只有1中方式
 *     假设有2个楼梯，则有2中方式
 *     假设有3个楼梯，则有3中方式
 *     假设有4个楼梯，则有5中方式
 *     ....
 *
 *     对以上总结，n个楼梯的方式，取决于n-1和n-2方式的数量，因此可以总结为：
 *     f(n) = f(n -1) + f(n-2)
 * </pre>
 */
public class SkipStairs {

    /**
     * 采用递归的方式
     *
     * @param n 计算n各台阶的方式个数
     * @return 方式个数
     */
    public int numWays(int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        return numWays(n - 1) + numWays(n - 2);
    }

    /**
     * 在不同的计算中，可能包含了相同的计算节点, 因此我们采用缓存的方式
     * <pre>
     *                        f(6)
     *                    /         \
     *                  f(5)        f(4)
     *                /   \        /   \
     *              f(3)  f(4)    f(3) f(2)
     * </pre>
     *
     * @param n 楼梯数量
     * @return
     */
    Map<Integer, Integer> stairsMap = new HashMap<>();

    public int numWays2(int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (stairsMap.get(n) != null) {
            return stairsMap.get(n);
        } else {
            int result = numWays2(n - 1) + numWays2(n - 2);
            stairsMap.put(n, result);
            return result;
        }
    }

    /**
     * 通过循环的方式解决问题
     *
     * @param n
     * @return
     */
    public int numWays3(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int c = 1, p = 1, pp = 1;
        for (int i = 2; i <= n; i++) {
            c = (pp + p) % 1000000007;
            pp = p;
            p = c;
        }

        return c;
    }


    public static void main(String[] args) {
        int stairs = new SkipStairs().numWays3(44);
        System.out.println(stairs);
    }
}
