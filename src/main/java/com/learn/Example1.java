package com.learn;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Example1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        Set<Integer> nums = new HashSet<>();
        int i = 2;
        while (i < num) {
            if (num % i == 0) {
                nums.add(i);
            }
            i++;
        }

        if (nums.size() != 2) {
            System.out.println("-1 -1");
        } else {
            Integer[] arr = nums.toArray(new Integer[2]);
            Integer n1 = arr[1];
            Integer n0 = arr[0];

            if (n0 > n1) {
                Integer t = n1;
                n1 = n0;
                n0 = t;
            }

            System.out.println(n0 + " " + n1);
        }
    }
}
