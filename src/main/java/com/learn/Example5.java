package com.learn;

public class Example5 {

    public static void main(String[] args) {
        System.out.println(new Example5().reverse("MyNameIsTom"));
    }

    public String reverse(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        String result = "";
        String s = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                if (!"".equals(s)) {
                    s = reverseSub(s);
                    result += s;
                    s = "";
                }
            }
            s += c;
        }
        if (!"".equals(s)) {
            s = reverseSub(s);
            result += s;
        }
        return result;
    }

    private String reverseSub(String s) {
        int l = 0, h = s.length() - 1;
        char[] chars = s.toCharArray();
        while (l < h) {
            char t = chars[l];
            chars[l] = chars[h];
            chars[h] = t;
            l++;
            h--;
        }
        return new String(chars);
    }
}
