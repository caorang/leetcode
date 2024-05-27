package cn.hutool.jackie.algorithm.array;

import java.util.Arrays;

/**
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/maximum-sum-with-exactly-k-elements/">...</a>
 */
public class MaximumSumWithExactlyKElements {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 3;
        MaximumSumWithExactlyKElements maximumSumWithExactlyKElements = new MaximumSumWithExactlyKElements();
        System.out.println(maximumSumWithExactlyKElements.maximizeSum(nums, k));
    }

    public int maximizeSum(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[nums.length - 1] + i;
        }
        return sum;
    }
}
