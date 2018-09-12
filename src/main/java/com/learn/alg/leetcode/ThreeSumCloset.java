package com.learn.alg.leetcode;

import java.util.Arrays;

/**
 * 给出一个整数列表和一个目标数值，找出列表中任意三个的和最靠近目标数值的和。
 * <p>
 * 我的思路是，将列表进行排序，然后判断target的值所在的目标位置，然后在最小的集合中进行寻找便可
 * </p>
 *
 * @author xianglujun
 * @datetime 2018/9/12 17:48
 */
public class ThreeSumCloset {

  public static int threeSumClosest(int[] nums, int target) {
    int sub = Integer.MAX_VALUE;
    int sum = 0, abs = 0, s = 0;
    for (int i = 0, len = nums.length; i < len - 2; i++) {
      for (int j = i + 1; j < len - 1; j++) {
        for (int k = j + 1; k < len; k++) {

          s = nums[i] + nums[j] + nums[k];
          abs = Math.abs(s - target);

          if (abs < sub) {
            sub = abs;
            sum = s;
          }
        }
      }
    }
    return sum;
  }

  /**
   * 改进版的实现
   *
   * @param nums 数字列表
   * @param target 目标数字
   * @return 三个数字距离target最近的和
   */
  public static int approach(int[] nums, int target) {
    int res = nums[0] + nums[1] + nums[2];

    if (nums.length == 3) {
      return res;
    }

    Arrays.sort(nums);
    int sum;
    for (int i = 0, len = nums.length; i < len - 2; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      // 判断当前的数字与最后两位数的和，为什么要校验?
      // 因为当前的数组为有序数组，则最后一个数字肯定为最大的
      // 伴随着i的递增，则和也会越大，最后会无限趋近于target的数字
      sum = nums[i] + nums[len - 1] + nums[len - 2];
      if (sum < target) {
        res = sum;
        continue;
      }

      // 为什么要*3？
      // 1. 因为当前计算的为3个数字的和
      // 2. 因为当前为有序数组，则i索引之后的数字肯定会大于或等于当前数字
      // 3. 因为当前的数组为有序数组，因此当第一个满足*3的数大于target时，实际上就应该是最小满足*3>target的数字了
      if (nums[i] * 3 > target) {
        sum = nums[i] + nums[i + 1] + nums[i + 2];
        int result = Math.abs(sum - target) < Math.abs(res - target) ? sum : res;
        return result;
      }

      // 在不满足以上条件的时候，这个时候就需要我们去便利之后的元素，
      int left = i + 1;
      int right = len - 1;
      while (left < right) {
        // 获取当前i的位置和其后的所有的数字的和
        sum = nums[i] + nums[left] + nums[right];
        if (sum == target) {
          return sum;
        }

        int result = Math.abs(sum - target);
        int resResult = Math.abs(res - target);
        if (sum < target) {
          // 判断当前的sum与res之间的关系
          if (result < resResult) {
            res = sum;
          }
          left++;
        } else {
          if (result < resResult) {
            res = sum;
          }
          right--;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {

    int[] nums = new int[]{0, 2, 1, -3};
    System.out.println(approach(nums, 1));
  }
}
