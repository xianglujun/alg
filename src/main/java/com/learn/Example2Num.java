package com.learn;

import java.util.Scanner;

public class Example2Num {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(getResult(input, ""));
    }

    private static int getResult(String input, String prefix) {

        int sum = 0;

        StringBuilder pre = new StringBuilder();
        int ans = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            sum += c;
            pre.append(c);
            if (isFlowNum(sum) && !pre.toString().equals(prefix)) {
                if (prefix.length() > 0 && pre.toString().contains(prefix)) {
                    return -1;
                }

                int count = getResult(input, pre.toString());
                if (count == -1) {
                    return count;
                }
                ans++;
                pre = new StringBuilder();
                sum = 0;

            } else if (sum > 999) {
                return 0;
            }
        }
        return ans;
    }

    private static boolean isFlowNum(int sum) {
        int g = sum % 10;
        int s = sum / 10 % 10;
        int b = sum / 100 % 10;

        return b * b * b + s * s * s + g * g * g == sum;
    }
}
