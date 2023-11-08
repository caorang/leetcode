package cn.hutool.jackie.algorithm.dp;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 *
 * @See <a href="https://leetcode.cn/problems/longest-valid-parentheses/">...</a>
 */
public class LongestValidParentheses {

	public static void main(String[] args) {
		LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
		System.out.println(longestValidParentheses.longestValidParentheses("(()"));
	}

	public int longestValidParentheses(String s) {
		return 0;
	}
}
