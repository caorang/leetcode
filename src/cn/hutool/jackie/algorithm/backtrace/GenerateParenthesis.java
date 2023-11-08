package cn.hutool.jackie.algorithm.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * 提示：
 * <p>
 * 1 <= n <= 8
 * Related Topics
 * 字符串
 * 动态规划
 * 回溯
 *
 * @See <a href="https://leetcode.cn/problems/generate-parentheses/">...</a>
 */
public class GenerateParenthesis {

	public static void main(String[] args) {
		System.out.println(new GenerateParenthesis().generateParenthesis(1));
		System.out.println(new GenerateParenthesis().generateParenthesis(2));
		System.out.println(new GenerateParenthesis().generateParenthesis(3));
		System.out.println(new GenerateParenthesis().generateParenthesis(4));
		System.out.println(new GenerateParenthesis().generateParenthesis(5));
		System.out.println(new GenerateParenthesis().generateParenthesis(6));
	}

	public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<>();
		backtrack(ans, new StringBuilder(), 0, 0, n);
		return ans;
	}

	public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
		if (cur.length() >= max * 2) {
			ans.add(cur.toString());
			return;
		}
		if (open < max) {
			cur.append('(');
			backtrack(ans, cur, open + 1, close, max);
			cur.deleteCharAt(cur.length() - 1);
		}
		if (close < open) {
			cur.append(')');
			backtrack(ans, cur, open, close + 1, max);
			cur.deleteCharAt(cur.length() - 1);
		}
	}
}
