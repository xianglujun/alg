package com.learn;

import java.util.*;

public class LightSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        Input[] input = new Input[n];
        for (int i = 0; i < n; i++) {
            int idx = in.nextInt();
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();

            Input inp = new Input();
            inp.index = idx;
            inp.radio = new int[][]{{x1, y1}, {x2, y2}};
            input[i] = inp;
        }

        List<Input> list = new ArrayList<>();
        for (Input input1 : input) {
            list.add(input1);
        }

        System.out.println(getResult(list, n));
    }

    public static class Input {
        private int index;
        private int[][] radio;
    }

    private static String getResult(List<Input> input, int n) {
        // 按照高低排序
        Collections.sort(input, Comparator.comparingInt(pre -> pre.radio[0][1]));

        StringJoiner stringJoiner = new StringJoiner(" ");
        List<Integer> ans = new ArrayList<>();
        int idx = 1;
        Input in = input.get(0);
        int len = in.radio[1][1] - in.radio[0][1];

        int cur = 0;
        ans.add(in.index);
        while (idx < input.size()) {
            Input input1 = input.get(cur);
            Input input2 = input.get(idx);
            if (input2.radio[0][1] - input1.radio[0][1] <= (len >>> 1)) {
                ans.add(input2.index);
            } else {
                Collections.sort(ans, Comparator.comparingInt(pre -> pre));
                for (Integer an : ans) {
                    stringJoiner.add(an + "");
                }
                cur = idx;
                ans.clear();
                ans.add(input2.index);
            }
            // 不在同一行
            idx++;
        }

        if (ans.size() > 0) {
            Collections.sort(ans, Comparator.comparingInt(pre -> pre));
            for (Integer an : ans) {
                stringJoiner.add(an + "");
            }
        }

        return stringJoiner.toString();
    }
}
