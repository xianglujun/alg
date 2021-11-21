package com.learn.alg.dp;

import java.util.Arrays;

public class CoinCharge {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        if (amount <= 0) {
            return -1;
        }

        int[] states = new int[amount + 1];
        Arrays.fill(states, amount + 1);

        states[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    states[i] = Math.min(states[i], states[i - coins[j]] + 1);
                }
            }
        }
        return states[amount] > amount ? -1 : states[amount];
    }
}
