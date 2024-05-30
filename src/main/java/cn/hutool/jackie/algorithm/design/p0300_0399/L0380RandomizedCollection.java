package cn.hutool.jackie.algorithm.design.p0300_0399;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * RandomizedCollection 是一种包含数字集合(可能是重复的)的数据结构。它应该支持插入和删除特定元素，以及删除随机元素。
 * <p>
 * 实现 RandomizedCollection 类:
 * <p>
 * RandomizedCollection()初始化空的 RandomizedCollection 对象。
 * bool insert(int val) 将一个 val 项插入到集合中，即使该项已经存在。如果该项不存在，则返回 true ，否则返回 false 。
 * bool remove(int val) 如果存在，从集合中移除一个 val 项。如果该项存在，则返回 true ，否则返回 false 。注意，如果 val 在集合中出现多次，我们只删除其中一个。
 * int getRandom() 从当前的多个元素集合中返回一个随机元素。每个元素被返回的概率与集合中包含的相同值的数量 线性相关 。
 * 您必须实现类的函数，使每个函数的 平均 时间复杂度为 O(1) 。
 * <p>
 * 注意：生成测试用例时，只有在 RandomizedCollection 中 至少有一项 时，才会调用 getRandom 。
 * <p>
 * 示例 1:
 * <p>
 * 输入
 * ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
 * [[], [1], [1], [2], [], [1], []]
 * 输出
 * [null, true, false, true, 2, true, 1]
 * <p>
 * 解释
 * RandomizedCollection collection = new RandomizedCollection();// 初始化一个空的集合。
 * collection.insert(1);   // 返回 true，因为集合不包含 1。
 * // 将 1 插入到集合中。
 * collection.insert(1);   // 返回 false，因为集合包含 1。
 * // 将另一个 1 插入到集合中。集合现在包含 [1,1]。
 * collection.insert(2);   // 返回 true，因为集合不包含 2。
 * // 将 2 插入到集合中。集合现在包含 [1,1,2]。
 * collection.getRandom(); // getRandom 应当:
 * // 有 2/3 的概率返回 1,
 * // 1/3 的概率返回 2。
 * collection.remove(1);   // 返回 true，因为集合包含 1。
 * // 从集合中移除 1。集合现在包含 [1,2]。
 * collection.getRandom(); // getRandom 应该返回 1 或 2，两者的可能性相同。
 * 提示:
 * <p>
 * -2^31 <= val <= 2^31 - 1
 * insert, remove 和 getRandom 最多 总共 被调用 2 * 10^5 次
 * 当调用 getRandom 时，数据结构中 至少有一个 元素
 *
 * @author rcao1
 */
public class L0380RandomizedCollection {

    static class RandomizedCollection {

        private Map<Integer, Set<Integer>> map1;
        private Map<Integer, Integer> map2;
        private int id = 0;
        private List<Integer> ids;

        public RandomizedCollection() {
            this.map1 = new HashMap<>();
            this.map2 = new HashMap<>();
            this.ids = new ArrayList<>();
        }

        public boolean insert(int val) {
            boolean flag = true;
            if (map1.containsKey(val) && !map1.get(val).isEmpty()) {
                flag = false;
            }
            map1.computeIfAbsent(val, k -> new HashSet<>()).add(id);
            map2.put(id, val);
            ids.add(id);
            id++;
            return flag;
        }

        public boolean remove(int val) {
            if (!map1.containsKey(val)) {
                return false;
            }
            Set<Integer> setIds = map1.get(val);
            if (setIds == null || setIds.isEmpty()) {
                return false;
            }
            int toRemove = setIds.iterator().next();
            setIds.remove(toRemove);
            map2.remove(toRemove);
            ids.remove(Integer.valueOf(toRemove));
            return true;
        }

        public int getRandom() {
            if (this.ids.size() == 1) {
                return map2.get(ids.get(0));
            }
            int random = new Random().nextInt(ids.size());
            int randomId = ids.get(random);
            return map2.get(randomId);
        }
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
         * [[], [1], [1], [2], [], [1], []]
         * 输出
         * [null, true, false, true, 2, true, 1]
         */
        String method = "[\"RandomizedCollection\",\"insert\",\"remove\",\"insert\"]";
        String arguments = "[[],[1],[1],[1]]";
        CaseRunner runner = new CaseRunner(method, arguments);

        for (int i = 0; i < 1000; i++) {
            runner.run(RandomizedCollection.class);
        }
    }
}
