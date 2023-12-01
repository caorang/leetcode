package cn.hutool.jackie.algorithm.array;

/**
 * @See <a href="https://leetcode.cn/problems/remove-element/">...</a>
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        System.out.println(removeElement(nums, val));
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.print("\n");

        nums = new int[] {0, 1, 2, 2, 3, 0, 4, 2};
        val = 2;
        System.out.println(removeElement(nums, val));
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static int removeElement(int[] nums, int val) {
        int prev = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num != val) {
                nums[prev] = num;
                prev++;
            }
        }
        return prev;
    }
}
