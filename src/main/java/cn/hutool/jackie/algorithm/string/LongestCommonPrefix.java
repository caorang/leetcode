package cn.hutool.jackie.algorithm.string;

/**
 * @See <a href="https://leetcode.cn/problems/longest-common-prefix/">...</a>
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[] {"flower", "flow", "flight"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[] {"dog", "racecar", "car"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[] {"cir", "car"}));
    }

    public String longestCommonPrefix(String[] strs) {
        int min = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < min) {
                min = strs[i].length();
            }
        }
        for (int i = 0; i < min; i++) {
            Character c = strs[0].charAt(i);
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                String str = strs[j];
                if (c != str.charAt(i)) {
                    return sb.toString();
                }
            }
            if (flag) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
