package com.learn.alg.dp;

public class ClimbStairs {

    /**
     * n阶楼梯
     * f(0) = 1;
     * f(1) = 1;
     * f(2) = f(1) + f(0)
     *
     * @param n 楼梯
     * @return 有多少张方法
     */
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    public int climbStairs2(int n) {
        int[] states = new int[n + 1];
        states[0] = 1;
        states[1] = 1;
        for (int i = 2; i <= n; i++) {
            states[i] = states[i - 1] + states[i - 2];
        }

        return states[n];
    }
}
