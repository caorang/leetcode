package cn.hutool.jackie.algorithm.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * @See <a href="https://leetcode.cn/problems/permutations/">...</a>
 */
public class Permute {

	public static void main(String[] args) {
		System.out.println(new Permute().permute(new int[] {1, 2}));
		System.out.println(new Permute().permute(new int[] {1, 2, 3}));
		System.out.println(new Permute().permute(new int[] {1, 2, 3, 4}));
		System.out.println(new Permute().permute(new int[] {1, 1, 2}));
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return result;
		}
		backtrace(result, new ArrayList<>(), nums);
		return result;
	}

	private void backtrace(List<List<Integer>> result, List<Integer> list, int[] nums) {
		if (list.size() >= nums.length) {
			result.add(new ArrayList<>(list));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (list.contains(nums[i])) {
				continue;
			}
			list.add(nums[i]);
			backtrace(result, list, nums);
			list.remove(list.size() - 1);
		}
	}
}
