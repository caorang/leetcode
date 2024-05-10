package cn.hutool.jackie.algorithm.array;

/**
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/first-missing-positive/">...</a>
 */
public class FirstMissingPositive {

    public static void main(String[] args) {
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[] {1, 2, 0}));
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[] {3, 4, -1, 1}));
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[] {7, 8, 9, 11, 12}));
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
