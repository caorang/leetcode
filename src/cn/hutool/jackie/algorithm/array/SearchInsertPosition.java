package cn.hutool.jackie.algorithm.array;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 *
 * @See <a href="https://leetcode-cn.com/problems/search-insert-position/">...</a>
 */
public class SearchInsertPosition {
	public static void main(String[] args) {
		int[] nums = {1, 3, 5, 6};
		int target = 5;
		System.out.println(new SearchInsertPosition().searchInsert(nums, target));

		nums = new int[] {1, 3, 5, 6};
		target = 2;
		System.out.println(new SearchInsertPosition().searchInsert(nums, target));

		nums = new int[] {1, 3, 5, 6};
		target = 7;
		System.out.println(new SearchInsertPosition().searchInsert(nums, target));
	}

	public int searchInsert(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (target == num) {
				return i;
			} else if (num > target) {
				return i;
			}
		}
		return nums.length;
	}
}
