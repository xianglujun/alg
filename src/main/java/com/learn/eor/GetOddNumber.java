package com.learn.eor;

/**
 * 获取一个数组中出现基数次的数字: 只有一个数字出现基数
 */
public class GetOddNumber {

    public int getEddNumber(int[] arr) {
        int num = 0;
        for (int i : arr) {
            num ^= arr[i];
        }
        return num;
    }

    public static void main(String[] args) {
        GetOddNumber getOddNumber = new GetOddNumber();
        int eddNumber = getOddNumber.getEddNumber(new int[]{1, 3, 3, 3, 1, 1, 1, 1, 1});
        System.out.println("出现基数次的数字为: " + eddNumber);
    }
}
