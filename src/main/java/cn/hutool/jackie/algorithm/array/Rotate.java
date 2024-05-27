package cn.hutool.jackie.algorithm.array;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 * 进阶：
 * <p>
 * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 *
 * @author rcao1
 */
public class Rotate {

    public Rotate() {

    }

    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            nums = moveLastToHead(nums);
        }
        System.out.println(Arrays.toString(nums));
    }

    private int[] moveLastToHead(int[] nums) {
        int[] temp = new int[nums.length];
        for (int i = 1; i < temp.length; i++) {
            temp[i] = nums[i - 1];
        }
        temp[0] = nums[nums.length - 1];
        return temp;
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        rotate.rotate(new int[] {1, 2, 3, 4, 5, 6, 7}, 3);
    }
}
