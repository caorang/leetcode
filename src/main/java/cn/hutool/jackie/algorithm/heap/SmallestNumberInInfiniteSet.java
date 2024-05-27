package cn.hutool.jackie.algorithm.heap;

import java.util.TreeSet;

/**
 * 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。
 * <p>
 * 实现 SmallestInfiniteSet 类：
 * <p>
 * SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含 所有 正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。
 * void addBack(int num) 如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集最后。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
 * [[], [2], [], [], [], [1], [], [], []]
 * 输出
 * [null, null, 1, 2, 3, null, 1, 4, 5]
 * <p>
 * 解释
 * SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
 * smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
 * smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
 * // 且 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= num <= 1000
 * 最多调用 popSmallest 和 addBack 方法 共计 1000 次
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/smallest-infinite-set/">...</a>
 */
public class SmallestNumberInInfiniteSet {

    public static void main(String[] args) {
        SmallestInfiniteSet set = new SmallestInfiniteSet(5000);
        set.addBack(2);
        System.out.println(set.popSmallest());
        System.out.println(set.popSmallest());
        System.out.println(set.popSmallest());
        set.addBack(1);
        System.out.println(set.popSmallest());
        System.out.println(set.popSmallest());
        System.out.println(set.popSmallest());
    }

    static class SmallestInfiniteSet {

        private int size;
        private TreeSet<Integer> pool;

        public SmallestInfiniteSet(int size) {
            this.size = size;
            this.pool = new TreeSet<>();
            for (int i = 1; i <= size; i++) {
                pool.add(i);
            }
        }

        public int popSmallest() {
            return this.pool.pollFirst();
        }

        public void addBack(int num) {
            if (!this.pool.contains(num)) {
                this.pool.add(num);
            }
        }
    }
}
