package com.learn.eor;

/**
 * 获取数字二禁止中1的数量
 */
public class GetBinaryOneCount {

    public int getBinaryOneCount(int num) {
        int rightOne = 0;
        int count = 0;
        System.out.println(Integer.toBinaryString(num));
        while ((rightOne = num & (~num + 1)) > 0) {
            count++;
            num ^= rightOne;
        }

        return count;
    }

    public static void main(String[] args) {

        System.out.println(new GetBinaryOneCount().getBinaryOneCount(-1));
    }
}
