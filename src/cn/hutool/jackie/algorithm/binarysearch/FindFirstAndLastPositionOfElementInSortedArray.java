package cn.hutool.jackie.algorithm.binarysearch;

import java.util.Arrays;

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * @See <a href="https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array">...</a>
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8)
        ));
        System.out.println(Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6)
        ));
        System.out.println(Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[] {0}, 0)
        ));
        System.out.println(Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[] {1, 4}, 4)
        ));
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[] {0, 0};
            }
            return new int[] {-1, -1};
        }
        boolean flag = false;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                left = mid;
                flag = true;
                break;
            } else {
                right = mid - 1;
            }
        }
        if (flag) {
            int index1 = left;
            while (index1 >= 0 && nums[index1] == target) {
                index1--;
            }
            int index2 = left;
            while (index2 < nums.length && nums[index2] == target) {
                index2++;
            }
            return new int[] {index1 + 1, index2 - 1};
        }
        return new int[] {-1, -1};
    }
}
