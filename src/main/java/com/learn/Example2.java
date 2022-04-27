package com.learn;

import java.util.Scanner;

public class Example2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 人员数量
        int num = sc.nextInt();
        int[] heights = new int[num];

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] nums = line.split(" ");
            int i = 0;
            for (String n : nums) {
                if (!"".equals(n.trim())) {
                    try {
                        Integer f = Integer.valueOf(n);
                        heights[i++] = f;
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }

        // 计算每个位上的身高
        int[] result = new int[num];
        for (int j = 0; j < heights.length; j++) {
            for (int k = j + 1; k < heights.length; k++) {
                if (heights[j] < heights[k]) {
                    result[j] = k;
                    break;
                }
            }
        }

        // 输出数组
        for (int j = 0; j < result.length; j++) {
            System.out.print(result[j] + " ");
        }
    }
}
