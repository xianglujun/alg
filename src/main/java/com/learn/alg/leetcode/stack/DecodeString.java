package com.learn.alg.leetcode.stack;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 *
 * <pre>
 *  实例1:
 *   输入：s = "3[a]2[bc]"
 *   输出："aaabcbc"
 *
 *  实例2:
 *      输入：s = "3[a2[c]]"
 *      输出："accaccacc"
 * </pre>
 */
public class DecodeString {

    /**
     * 通过栈的方式实现对表达式的解析
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }

        Stack<String> opStack = new Stack<>();
        for (int index = 0; index < s.length(); ) {
            // 获取字符
            char c = s.charAt(index);
            // 判断是否为数字
            if (Character.isDigit(c)) {
                // 获取数字
                StringBuilder digit = new StringBuilder();
                while (Character.isDigit(c)) {
                    digit.append(c);
                    index++;
                    c = s.charAt(index);
                }
                opStack.push(digit.toString());
                continue;
            } else if ('[' == c || Character.isLetter(c)) {
                opStack.push(String.valueOf(c));
            } else {
                // 这种情况则表示]出现, 测试则需要计算
                List<String> stackCache = new ArrayList<>();
                while (!opStack.isEmpty()) {
                    String op = opStack.pop();
                    if ("[".equals(op)) {
                        break;
                    }

                    stackCache.add(op);
                }

                // 获取重复的数字
                int digit = Integer.valueOf(opStack.pop());
                Collections.reverse(stackCache);

                StringBuilder rp = new StringBuilder();
                for (String sr : stackCache) {
                    rp.append(sr);
                }

                // 重复次数
                int i = 0;
                StringBuilder rs = new StringBuilder();
                while (i < digit) {
                    rs.append(rp);
                    i++;
                }

                opStack.push(rs.toString());
            }
            index++;
        }

        // 组成一个字符串
        StringBuilder sb = new StringBuilder();
        while (!opStack.isEmpty()) {
            sb.insert(0, opStack.pop());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String result = new DecodeString().decodeString("3[a]2[bc]");
        Assert.assertEquals("aaabcbc", result);
    }
}
