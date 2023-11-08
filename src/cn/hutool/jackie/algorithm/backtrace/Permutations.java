package cn.hutool.jackie.algorithm.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * @See <a href="https://leetcode.cn/problems/permutations/">...</a>
 */
public class Permutations {

	public static void main(String[] args) {
		System.out.println(new Permutations().permute(new int[] {1, 2}));
		System.out.println(new Permutations().permute(new int[] {1, 2, 3}));
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		backtrace(result, new ArrayList<>(), nums);
		return result;
	}

	private void backtrace(List<List<Integer>> result, List<Integer> path, int[] nums) {
		if (path.size() >= nums.length) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (path.contains(nums[i])) {
				continue;
			}
			path.add(nums[i]);
			backtrace(result, path, nums);
			path.remove(path.size() - 1);
		}
	}
}
