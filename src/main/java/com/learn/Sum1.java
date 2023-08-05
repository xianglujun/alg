package com.learn;

import java.util.LinkedList;
import java.util.Scanner;

public class Sum1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(getResult(s));
    }

    private static String getResult(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        int maxLen = 0;
        String res = "-1";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                // 可能是开始信号，可能是结束信息
                // 栈为空，则是命令的开始
                if (stack.isEmpty()) {
                    stack.add(c);
                    continue;
                }

                // 如果栈不为空, 判断最后一个元素的样式
                Character last = stack.getLast();
                if (last == '1') {
                    stack.add(c);
                    continue;
                } else if (stack.size() > maxLen && stack.size() > 1) { // 栈顶是0
                    maxLen = stack.size();
                    StringBuilder sb = new StringBuilder();
                    for (Character character : stack) {
                        sb.append(character);
                    }
                    res = sb.toString();
                    stack.clear();
                } else {
                    stack.clear();
                }
                stack.add(c);
            } else {

                if (!stack.isEmpty()) {
                    if (stack.getLast() == '0') {
                        stack.add(c);
                    } else {
                        // 上一个是1, 则出现了连续的1，不是完整信号
                        stack.clear();
                    }
                }

            }
        }
        return res;
    }
}
