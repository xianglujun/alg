package com.learn.alg.leetcode.string;

/**
 * 415题:
 * <pre>
 *     给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * </pre>
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {

        int len1 = num1.length();
        int len2 = num2.length();

        StringBuilder sb = new StringBuilder();

        int carry = 0;
        for (int i = len1 - 1, j = len2 - 1; i >= 0 || j >= 0 || carry > 0; i--, j--) {
            int f = i < 0 ? 0 : num1.charAt(i) - '0';
            int s = j < 0 ? 0 : num2.charAt(j) - '0';
            int r = f + s + carry;
            sb.append((r % 10));
            carry = r / 10;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "11";
        String num2 = "123";
        System.out.println(new AddStrings().addStrings(num1, num2));
    }
}
