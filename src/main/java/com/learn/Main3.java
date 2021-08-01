package com.learn;

import java.util.*;

public class Main3 {

    private static Map<Character, List<String>> charMap = new HashMap<>();

    static {
        charMap.put('2', Arrays.asList("a", "b", "c"));
        charMap.put('3', Arrays.asList("d", "e", "f"));
        charMap.put('4', Arrays.asList("g", "h", "i"));
        charMap.put('5', Arrays.asList("j", "k", "l"));
        charMap.put('6', Arrays.asList("m", "n", "o"));
        charMap.put('7', Arrays.asList("p", "q", "r", "s"));
        charMap.put('8', Arrays.asList("t", "u", "v"));
        charMap.put('9', Arrays.asList("w", "x", "y", "z"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        char[] chars = line.toCharArray();
        int mode = 1;
        char cur;
        List<Character> list = new ArrayList<>();
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '#') {
                mode = 2;
                continue;
            }
            if (mode == 1) {
                if (chars[i] == '/') {
                    chars[i] = '-';
                }
            } else if (mode ==2) {

            }
        }

        for (int i = 0; i <chars.length; i++) {
            if (chars[i] != '-') {
                System.out.print(chars[i]);
            }
        }
    }
}
