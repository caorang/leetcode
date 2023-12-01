package cn.hutool.jackie.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @See <a href="https://leetcode.cn/problems/roman-to-integer/">...</a>
 */
public class RomanToInt {

    static Map<String, Integer> dictionary = new HashMap<>();

    // I 1 V 5 X 10 L 50 C 100 D 500 M 1000
    static {
        dictionary.put("I", 1);
        dictionary.put("V", 5);
        dictionary.put("X", 10);
        dictionary.put("L", 50);
        dictionary.put("C", 100);
        dictionary.put("D", 500);
        dictionary.put("M", 1000);
    }

    public static void main(String[] args) {
        System.out.println(new RomanToInt().romanToInt("III"));
        System.out.println(new RomanToInt().romanToInt("IV"));
        System.out.println(new RomanToInt().romanToInt("IX"));
        System.out.println(new RomanToInt().romanToInt("LVIII"));
    }

    public int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && getVal(s.charAt(i)) < getVal(s.charAt(i + 1))) {
                result -= getVal(s.charAt(i));
            } else {
                result += getVal(s.charAt(i));
            }
        }
        return result;
    }

    public int getVal(char c) {
        return dictionary.get(String.valueOf(c));
    }
}
