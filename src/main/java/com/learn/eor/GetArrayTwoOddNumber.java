package com.learn.eor;

/**
 * 获取一个数组中两个数出现了奇数次，其他数都出现了偶数次
 */
public class GetArrayTwoOddNumber {

    public void getArrayTwoOddNumber(int[] arr) {
        // eor的实现, 使为了获取两个奇数的数字异或值， 即: a^b
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }

        // 获取eor的二进制中，最右边的二进制1位
        // 11011101
        // 00100010  取反
        // 00100011  + 1
        // 00000001  // &操作
        int rightOne = eor & (~eor + 1);

        int first = 0;
        for (int i : arr) {
            // 将在最右侧包含1位的数字分布到一组, 这样就可以求出其中的一个数
            // 例如：
            if ((i & rightOne) != 0) {
                first ^= i;
            }
        }

        System.out.println("这两个数分别为: " + first + ", " + (first ^ eor));
    }

    public static void main(String[] args) {
        GetArrayTwoOddNumber getArrayTwoOddNumber = new GetArrayTwoOddNumber();
        getArrayTwoOddNumber.getArrayTwoOddNumber(new int[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 4, 4, 4, 4, 5, 5, 5, 5});
    }
}
