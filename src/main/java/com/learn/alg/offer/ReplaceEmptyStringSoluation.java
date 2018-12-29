package com.learn.alg.offer;

import org.junit.Test;

/**
 * 替换字符串中空格为'%20'的字符
 * 1. 在原有的字符串上进行替换
 * 2. 使用新的字符串执行操作, 将旧的字符串的值进行拷贝.
 * @author xianglujun
 */
public class ReplaceEmptyStringSoluation {

    public static String replace(StringBuffer str) {
        int emptyCount = 0;
        for (int i = 0, len = str.length(); i < len; i++) {
            if (str.charAt(i) == ' ') {
                emptyCount++;
            }
        }

        /**
         * 表明没有空格
         */
        if (0 ==  emptyCount) {
            return str.toString();
        }

        // 计算新的字符串的长度, 只是在之前的基础上多了两个长度
        int oldLength = str.length();
        int newLength = oldLength + emptyCount * 2;

        // 设置新的长度
        str.setLength(newLength);

        // 设置两个标签, 完成数据的位置配置
        int oldPosition = oldLength - 1;
        int newPosition = newLength - 1;

        while (oldPosition >= 0) {
            if (str.charAt(oldPosition) == ' ') {
                str.setCharAt(newPosition--, '0');
                str.setCharAt(newPosition--, '2');
                str.setCharAt(newPosition--, '%');
            } else {
                str.setCharAt(newPosition--, str.charAt(oldPosition));
            }

            oldPosition--;
        }

        return str.toString();
    }

    @Test
    public void test() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("dfd                                      sdfdfd");
        System.out.println(replace(stringBuffer));

    }
}
