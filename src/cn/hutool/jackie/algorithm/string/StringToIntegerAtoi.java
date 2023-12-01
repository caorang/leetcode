package cn.hutool.jackie.algorithm.string;

/**
 * @See <a href="https://leetcode.com/problems/string-to-integer-atoi/">...</a>
 */
public class StringToIntegerAtoi {
    public static void main(String[] args) {
        System.out.println(new StringToIntegerAtoi().myAtoi("42"));
        System.out.println(new StringToIntegerAtoi().myAtoi("   -42"));
        System.out.println(new StringToIntegerAtoi().myAtoi("4193 with words"));
        System.out.println(new StringToIntegerAtoi().myAtoi("words and 987"));
        System.out.println(new StringToIntegerAtoi().myAtoi("-91283472332"));
        System.out.println(new StringToIntegerAtoi().myAtoi("-+12"));
    }

    private int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
            s = s.substring(1);
        } else if (s.charAt(0) == '+') {
            s = s.substring(1);
        }
        if (s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            return 0;
        }
        long result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                break;
            }
            result = result * 10 + (s.charAt(i) - '0');
            if (result > Integer.MAX_VALUE) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        return negative ? (int) -result : (int) result;
    }
}
