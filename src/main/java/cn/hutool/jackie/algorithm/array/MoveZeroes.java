package cn.hutool.jackie.algorithm.array;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 * <p>
 * 输入: nums = [0]
 * 输出: [0]
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/move-zeroes/description/?envType=study-plan-v2&envId=top-100-liked">...</a>
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 0, 3, 12};
        new MoveZeroes().moveZeroes(nums);
        System.out.println(nums);
    }

    public void moveZeroes(int[] nums) {

    }
}
