package com.learn.alg.leetcode;

import com.alibaba.fastjson.JSON;
import java.util.Arrays;
import java.util.List;

/**
 * 两个数字相加，给出的数据格式如下: You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <pre>
 *   Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *   Output: 7 -> 0 -> 8
 *   Explanation: 342 + 465 = 807.
 * </pre>
 *
 * @author xianglujun
 * @datetime 2018/9/10 17:52
 */
public class AddTwoNumbers {

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (null == l1 && l2 == null) {
      return new ListNode(0);
    }

    ListNode head = null, next = null;
    head = next = new ListNode(0);
    int rest = 0;

    // 便利两个数，并做加法
    while (true) {
      if (null == l1 && l2 == null) {
        if (rest > 0) {
          next.next = new ListNode(rest);
        }
        break;
      }

      // 相加运算
      int firstValue = null != l1 ? l1.val : 0;
      int nextValue = null != l2 ? l2.val : 0;

      int sum = firstValue + nextValue + rest;
      // 计算当前节点的值，和下一个

      rest = sum / 10;
      int div = sum % 10;

      next = next.next = new ListNode(div);
      l1 = (l1 == null ? null : l1.next);
      l2 = (l2 == null ? null : l2.next);
    }

    return head.next;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(3);

    ListNode l2 = new ListNode(5);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(4);

    System.out.println(JSON.toJSONString(addTwoNumbers(l1, l2)));
  }

  /**
   * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int
   * x) { val = x; } }
   */

  static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}
