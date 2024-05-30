package cn.hutool.jackie.algorithm.design.p0300_0399;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 实现RandomizedSet 类：
 * <p>
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * 解释
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
 * randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
 * randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
 * randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * 提示：
 * <p>
 * -2^31 <= val <= 2^31 - 1
 * 最多调用 insert、remove 和 getRandom 函数 2 * 10^5 次
 * 在调用 getRandom 方法时，数据结构中 至少存在一个 元素。
 *
 * @author rcao1
 */
public class L0380RandomizedSet {

    static class RandomizedSet {

        private Map<Integer, Integer> map1;
        private Map<Integer, Integer> map2;
        private int id = 0;
        private List<Integer> ids;

        public RandomizedSet() {
            this.map1 = new HashMap<>();
            this.map2 = new HashMap<>();
            this.ids = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map1.containsKey(val)) {
                return false;
            }
            map1.put(val, id);
            map2.put(id, val);
            ids.add(id);
            id++;
            return true;
        }

        public boolean remove(int val) {
            if (!map1.containsKey(val)) {
                return false;
            }
            int id = map1.get(val);
            map1.remove(val);
            map2.remove(id);
            ids.remove(Integer.valueOf(id));
            return true;
        }

        public int getRandom() {
            if (ids.size() == 1) {
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
         * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
         * [[], [1], [2], [2], [], [1], [2], []]
         * 输出
         * [null, true, false, true, 2, true, false, 2]
         */
        String method = "[\"RandomizedSet\", \"insert\", \"remove\", \"insert\", \"getRandom\", \"remove\", \"insert\", \"getRandom\"]";
        String arguments = "[[], [1], [2], [2], [], [1], [2], []]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(RandomizedSet.class);
    }
}
