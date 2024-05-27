package cn.hutool.jackie.algorithm.array;

import java.util.Arrays;

/**
 * @author rcao1
 * @see <a href="https://leetcode.com/problems/sort-colors/">...</a>
 */
public class SortColors {

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        new SortColors().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int mid, int high) {
        int temp = nums[mid];
        nums[mid] = nums[high];
        nums[high] = temp;
    }
}
