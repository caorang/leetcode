package cn.hutool.jackie.algorithm.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @See <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/">...</a>
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(removeDuplicates(nums));
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.print("\n");
    }

    public static int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int prev = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!set.contains(num)) {
                set.add(num);
                nums[prev] = num;
                prev++;
            }
        }
        return prev;
    }
}
