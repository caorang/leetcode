package cn.hutool.jackie.algorithm.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @See <a href="https://leetcode.cn/problems/combination-sum-ii/">...</a>
 */
public class CombinationSum2 {

	public static void main(String[] args) {
		System.out.println(new CombinationSum2().combinationSum(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));
		System.out.println(new CombinationSum2().combinationSum(new int[] {2, 5, 2, 1, 2}, 5));
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		if (candidates == null || candidates.length == 0) {
			return new ArrayList<>();
		}
		Arrays.sort(candidates);
		return backtrace(new ArrayList<>(), new ArrayList<>(), candidates, 0, target);
	}

	public List<List<Integer>> backtrace(List<List<Integer>> result, List<Integer> path, int[] candidates, int start, int target) {
		if (target == 0) {
			result.add(new ArrayList<>(path));
			return result;
		}
		for (int i = start; i <= candidates.length - 1; i++) {
			int num = candidates[i];
			if (target - num < 0) {
				break;
			}
			if (i > start && candidates[i] == candidates[i - 1]) {
				continue;
			}
			path.add(num);
			backtrace(result, path, candidates, i + 1, target - num);
			path.remove(path.size() - 1);
		}
		return result;
	}
}
