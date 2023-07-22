package com.learn.eor;

/**
 * 获取一个数字二进制中最右侧的1
 */
public class GetNumberMostRightOne {

    public int getNumberMostRightOne(int num) {
        return num & (~num + 1);
    }
}
