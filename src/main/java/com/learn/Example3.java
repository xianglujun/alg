package com.learn;

import java.util.Scanner;

public class Example3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 表示非元音的长度
        int num = sc.nextInt();

        // 需要区分的字符串
        String str = sc.nextLine();
//        String str = "asdbuiodevauufgh";
        // 设置元音字符
        String yys = "aeiouAEIOU";

        // 采用快慢指针的方式
        int slow = 0, fast = 0;
        int maxLen = 0;
        int nonN = 0;
        for (; fast < str.length(); ) {
            char f = str.charAt(fast);

            if (yys.indexOf(f) == -1) {
                nonN++;
            }

            fast++;

            // 是否和期望相等
            if (nonN == num) {
                int dur = fast - slow;
                maxLen = dur > maxLen ? dur : maxLen;
            }

            char s = str.charAt(slow);
            if (yys.indexOf(s) == -1) {
                slow++;
                nonN = --nonN < 0 ? 0 : nonN;
            } else if (nonN > num) {
                // 是元音, 则不需要减去非元音的统计数字
                slow++;
            }
        }

        System.out.println(maxLen);
    }
}
