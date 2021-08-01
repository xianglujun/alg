package com.learn;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = null;
        while ((line = scanner.next()) != null) {
            // 将字符串排序
            String result = sort(line);
            System.out.println(result);
        }
    }

    public static String sort(String line) {
        if (line == null || line.length() == 0) {
            return line;
        }

        //将数组转换为char数组
        char[] chars = line.toCharArray();
        quickSort(chars, 0, chars.length -1);

        // 重新获取chars
        char[] srcChars = line.toCharArray();

        // 比较两个字符数组
        int min = -1;
        int exch = -1;
        char defChar = (char) -1;
        for (int i = 0; i < srcChars.length; i ++) {
            if (chars[i] == srcChars[i]) {
                continue;
            }

            // 不相等, 则说明有位置交换, 确定最小元素
            if (min == -1) {
                min = i;
                exch = i;
                defChar = chars[i];
            } else if (min != i && defChar == srcChars[i]) {
                min = i;
            }
        }

        if (min > -1) {
            exch(srcChars, min, exch);
        }

        return new String(srcChars);
    }

    public static void quickSort(char[] chars, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(chars, lo, hi);
        quickSort(chars, lo, j);
        quickSort(chars, j + 1, hi);
    }

    private static int partition(char[] chars, int lo, int hi) {
        if (lo >= hi) {
            return lo;
        }
        int i = lo; int j = hi + 1;
        char v = chars[lo];
        while (true) {
            while (less(chars[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(v, chars[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            exch(chars, i, j);
        }
        exch(chars, lo, j);
        return j;
    }

    private static boolean less(char i, char j) {
        return i < j;
    }

    private static void exch(char[] chars, int i, int j) {
        char temp = chars[j];
        chars[j] = chars[i];
        chars[i] = temp;
    }
}
