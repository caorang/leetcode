package cn.hutool.jackie.algorithm.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @See <a href="https://leetcode.cn/problems/combination-sum/">...</a>
 */
public class CombinationSum {

	public static void main(String[] args) {
		System.out.println(new CombinationSum().combinationSum(new int[] {2, 3, 6, 7}, 7));
		System.out.println(new CombinationSum().combinationSum(new int[] {2, 3, 5}, 8));
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
			path.add(num);
			backtrace(result, path, candidates, i, target - num);
			path.remove(path.size() - 1);
		}
		return result;
	}
}
