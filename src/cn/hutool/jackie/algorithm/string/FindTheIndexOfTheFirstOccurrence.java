package cn.hutool.jackie.algorithm.string;

/**
 * @See <a href="https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/">...</a>
 */
public class FindTheIndexOfTheFirstOccurrence {
	public static void main(String[] args) {
		System.out.println(new FindTheIndexOfTheFirstOccurrence().strStr("hello", "ll"));
		System.out.println(new FindTheIndexOfTheFirstOccurrence().strStr("hello", "lo"));
		System.out.println(new FindTheIndexOfTheFirstOccurrence().strStr("aaaaa", "bba"));
	}

	private int strStr(String haystack, String needle) {
		for (int i = 0; i < haystack.length(); i++) {
			if (i + needle.length() > haystack.length()) {
				return -1;
			}
			String sub = haystack.substring(i, i + needle.length());
			if (sub.equals(needle)) {
				return i;
			}
		}
		return -1;
	}
}
