package cn.hutool.jackie.algorithm.design.p0300_0399;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 给你一个数组 nums ，请你完成两类查询。
 * <p>
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 * 示例 1：
 * <p>
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 * <p>
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 10^4
 *
 * @author rcao1
 */
public class L0307NumArray {

    public static class NumArray {
        private int[] nums;
        private int[] tree;

        public NumArray(int[] nums) {
            int n = nums.length;
            this.nums = new int[n]; // 使 update 中算出的 delta = nums[i]
            tree = new int[n + 1];
            for (int i = 0; i < n; i++) {
                update(i, nums[i]);
            }
        }

        public void update(int index, int val) {
            int delta = val - nums[index];
            nums[index] = val;
            for (int i = index + 1; i < tree.length; i += i & -i) {
                tree[i] += delta;
            }
        }

        private int prefixSum(int i) {
            int s = 0;
            for (; i > 0; i &= i - 1) { // i -= i & -i 的另一种写法
                s += tree[i];
            }
            return s;
        }

        public int sumRange(int left, int right) {
            return prefixSum(right + 1) - prefixSum(left);
        }
    }

    public static void main(String[] args) {
        /**
         * 输入：
         * ["NumArray", "sumRange", "update", "sumRange"]
         * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
         * 输出：
         * [null, 9, null, 8]
         */
        String method = "[\"NumArray\", \"sumRange\", \"update\", \"sumRange\"]";
        String arguments = "[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(NumArray.class);
    }
}
