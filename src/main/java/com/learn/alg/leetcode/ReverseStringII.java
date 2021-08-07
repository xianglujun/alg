package com.learn.alg.leetcode;

/**
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abcdefg", k = 2 输出："bacdfeg" 示例 2：
 * <p>
 * 输入：s = "abcd", k = 2 输出："bacd"
 * <p>
 */
public class ReverseStringII {

    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return s;
        }

        char[] characters = s.toCharArray();
        int len = characters.length;

        if (len < k) {
            reverse(characters, 0, len);
            return new String(characters);
        }

        for (int start = 0; start < len; start += 2 * k) {
            int i = start, j = Math.min(i + k - 1, len - 1);
            reverse(characters, i, j);
        }

        return new String(characters);
    }

    private void reverse(char[] characters, int start, int end) {
        int len = characters.length;
        int i = start;
        int j = end >= len ? len - 1 : end;
        for (; i < j; i++, j--) {
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
    }

    public static void main(String[] args) {
        String s = "abcd";
        ReverseStringII reverse = new ReverseStringII();
        String result = reverse.reverseStr(s, 4);
        System.out.println(result);
    }
}
