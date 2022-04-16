package com.learn.alg.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <pre>
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * </pre>
 */
public class LengthOfLongestSubstring {
    /**
     * 实现思路：
     * <pre>
     *  使用双指针的方式, 当遇到相同的字符的时候, 就将左指针向后移动一位, 然后有指针向后移动一位即可
     *  </pre>
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int start = -1, ans = 0;
        Set<Character> set = new HashSet<>();

        int n = s.length();
        for (int i = 0; i < n; i++) {

            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }

            // 判断开始的元素是否与长度相等
            while (start + 1 < n && !set.contains(s.charAt(start + 1))) {
                // 如果说不存在, 加入到set中
                set.add(s.charAt(start + 1));
                start++;
            }

            if (start == n) {
                break;
            }

            // 在遍历完成之后, 需要判断当前已有的最大子串,是否和当前的子串
            ans = Math.max(ans, start - i + 1);
        }

        return ans;
    }
}
