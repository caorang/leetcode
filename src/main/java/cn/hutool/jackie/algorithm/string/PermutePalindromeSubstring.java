package cn.hutool.jackie.algorithm.string;

/**
 * @author rcao1
 */
public class PermutePalindromeSubstring {

    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        for (int left = 0; left < s.length(); left++) {
            for (int right = left; right < s.length(); right++) {
                String sub = s.substring(left, right + 1);
                if (left != right && isPalindrome(sub)) {
                    System.out.println(sub + ", left=" + left + ", right=" + right);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        return s.equals(sb.reverse().toString());
    }

    public static void main(String[] args) {
        PermutePalindromeSubstring palindrome = new PermutePalindromeSubstring();
        System.out.println(palindrome.canPermutePalindrome("code"));
        System.out.println(palindrome.canPermutePalindrome("aab"));
        System.out.println(palindrome.canPermutePalindrome("carerac"));
    }
}
