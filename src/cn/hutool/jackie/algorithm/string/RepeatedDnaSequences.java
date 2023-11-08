package cn.hutool.jackie.algorithm.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 * <p>
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * <p>
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 * <p>
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 */
public class RepeatedDnaSequences {

	public static void main(String[] args) {
		RepeatedDnaSequences repeatedDnaSequences = new RepeatedDnaSequences();
		System.out.println(repeatedDnaSequences.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
		System.out.println(repeatedDnaSequences.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
	}

	public List<String> findRepeatedDnaSequences(String s) {
		Set<String> result = new HashSet<>();
		Set<String> set = new HashSet<>();
		for (int i = 0; i <= s.length() - 10; i++) {
			String sub = s.substring(i, i + 10);
			if (set.contains(sub)) {
				result.add(sub);
			}
			set.add(sub);
		}
		return new ArrayList<>(result);
	}
}
