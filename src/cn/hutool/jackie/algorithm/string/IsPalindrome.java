package cn.hutool.jackie.algorithm.string;

/**
 * @See <a href="https://leetcode.cn/problems/palindrome-number/">...</a>
 */
public class IsPalindrome {

	public static void main(String[] args) {
		System.out.println(new IsPalindrome().isPalindrome(121));
		System.out.println(new IsPalindrome().isPalindrome(-121));
		System.out.println(new IsPalindrome().isPalindrome(10));
		System.out.println(new IsPalindrome().isPalindrome(1));
		System.out.println(new IsPalindrome().isPalindrome(1001));
		System.out.println(new IsPalindrome().isPalindrome(1212));
	}

	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		String str = String.valueOf(x);
		int length = str.length();
		if (length == 1) {
			return true;
		}
		for (int i = 0; i < length / 2; i++) {
			if (str.charAt(i) != str.charAt(length - i - 1)) {
				return false;
			}
		}
		return true;
	}
}
