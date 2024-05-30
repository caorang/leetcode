package cn.hutool.jackie.algorithm.design.p0700_0799;

import java.util.PriorityQueue;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * 示例：
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 * 提示：
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 *
 * @author rcao1
 */
public class L0703KthLargest {

    static class KthLargest {

        int k;
        private PriorityQueue<Integer> queue = new PriorityQueue<>();

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int i = 0; i < nums.length; i++) {
                add(nums[i]);
            }
        }

        public int add(int val) {
            queue.offer(val);
            if (queue.size() > k) {
                queue.poll();
            }
            System.out.println("add: " + val + ", queue: " + queue);
            return queue.peek();
        }
    }

    public static void main(String[] args) {
        /**
         * 输入：
         * ["KthLargest", "add", "add", "add", "add", "add"]
         * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
         * 输出：
         * [null, 4, 5, 5, 8, 8]
         */
        String method = "[\"KthLargest\", \"add\", \"add\", \"add\", \"add\", \"add\"]";
        String arguments = "[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(KthLargest.class);
    }
}
