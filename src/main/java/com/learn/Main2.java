package com.learn;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = null;

        String count = scanner.nextLine();
        int lines = Integer.parseInt(count);
        int max = 0;
        int c = 0;
        while ((line = scanner.nextLine()) != null) {
            byte[] bytes = getBytes(line);
            int val = getMaxValue(bytes);
            System.out.println(val);
            max += val;
            c++;
            if (c>=lines) {
                break;
            }
        }
        System.out.println(max);
    }

    private static int getMaxValue(byte[] bytes) {

        // 判断连续1的数量
        int maxIndex = -1;
        int max = 0;
        int currentIndex = -1;
        int currentMax = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 1) {
                currentMax++;
                if (currentIndex == -1) {
                    currentIndex = i;
                }
                continue;
            } else if ( currentMax >= max){
                maxIndex = currentIndex;
                max = currentMax;
                currentMax = 0;
                currentIndex = -1;
            }
        }

        if (currentIndex != -1 && currentMax >= max){
            maxIndex = currentIndex;
            max = currentMax;
        }

        byte[] newBytes = new byte[bytes.length];
        // 确定了当前最大连续1位置, 判断是否为尾部
        if (maxIndex + max == bytes.length - 1) {
            // 向向右移动

            for (int i = maxIndex; i < bytes.length; i++) {
                newBytes[i - maxIndex] = bytes[i];
            }

            for (int i = 0; i < maxIndex; i++) {
                newBytes[i + max] = bytes[i];
            }
        } else if (maxIndex == 0 && bytes[bytes.length - 1] == 1) {
            // 确定1的数量
            int i = bytes.length - 1;
            int len = 0;
            for (; i >= 0; i--) {
                if (bytes[i] != 1) {
                    i++;
                    break;
                }
                len++;
            }
            for (int j = i; j < bytes.length; j++) {
                newBytes[bytes.length - 1 - j] = bytes[j];
            }

            for (int j = 0; j < i; j++) {
                newBytes[j + len] = bytes[j];
            }
            // 右移动
        } else {
            // 左移动
            for (int j = maxIndex; j < bytes.length; j++) {
                newBytes[j-maxIndex] = bytes[j];
            }

            for (int j = 0; j < maxIndex; j++) {
                newBytes[j + max] = bytes[j];
            }
        }
        return getValue(newBytes);
    }

    private static int getValue(byte[] bytes) {
        int val = 0;
        for(int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 1) {
                int sqrt = bytes.length -i -1;
                val += Math.pow(2, sqrt);
            }
        }
        return val;
    }

    private static byte[] getBytes(String line) {
        String[] strings = line.split(",");
        byte[] bytes = new byte[strings.length];
        for (int i = 0; i < strings.length; i++) {
            bytes[i] = Byte.parseByte(strings[i]);
        }
        return bytes;
    }
}
