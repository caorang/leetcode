package cn.hutool.jackie.algorithm.design2.p2300_2399;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 设计一个数字容器系统，可以实现以下功能：
 * <p>
 * 在系统中给定下标处 插入 或者 替换 一个数字。
 * 返回 系统中给定数字的最小下标。
 * 请你实现一个 NumberContainers 类：
 * <p>
 * NumberContainers() 初始化数字容器系统。
 * void change(int index, int number) 在下标 index 处填入 number 。如果该下标 index 处已经有数字了，那么用 number 替换该数字。
 * int find(int number) 返回给定数字 number 在系统中的最小下标。如果系统中没有 number ，那么返回 -1 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
 * [[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
 * 输出：
 * [null, -1, null, null, null, null, 1, null, 2]
 * <p>
 * 解释：
 * NumberContainers nc = new NumberContainers();
 * nc.find(10); // 没有数字 10 ，所以返回 -1 。
 * nc.change(2, 10); // 容器中下标为 2 处填入数字 10 。
 * nc.change(1, 10); // 容器中下标为 1 处填入数字 10 。
 * nc.change(3, 10); // 容器中下标为 3 处填入数字 10 。
 * nc.change(5, 10); // 容器中下标为 5 处填入数字 10 。
 * nc.find(10); // 数字 10 所在的下标为 1 ，2 ，3 和 5 。因为最小下标为 1 ，所以返回 1 。
 * nc.change(1, 20); // 容器中下标为 1 处填入数字 20 。注意，下标 1 处之前为 10 ，现在被替换为 20 。
 * nc.find(10); // 数字 10 所在下标为 2 ，3 和 5 。最小下标为 2 ，所以返回 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= index, number <= 10^9
 * 调用 change 和 find 的 总次数 不超过 10^5 次。
 *
 * @author rcao1
 */
public class L2349NumberContainers {

    private Map<Integer, Integer> numbers = new HashMap<>();
    private Map<Integer, TreeSet<Integer>> valueIndexes = new HashMap<>();

    public L2349NumberContainers() {

    }

    public void change(int index, int number) {
        if (numbers.containsKey(index)) {
            int previous = numbers.get(index);
            valueIndexes.computeIfAbsent(previous, k -> new TreeSet<>()).remove(index);
        }
        numbers.put(index, number);
        valueIndexes.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
    }

    public int find(int number) {
        if (valueIndexes.containsKey(number)) {
            if (!valueIndexes.get(number).isEmpty()) {
                return valueIndexes.get(number).first();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /**
         *  输入：
         *  ["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
         *  [[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
         *  输出：
         *  [null, -1, null, null, null, null, 1, null, 2]
         */
        String method = "[\"NumberContainers\",\"change\",\"find\",\"change\",\"find\",\"find\",\"find\"]";
        String arguments = "[[],[1,10],[10],[1,20],[10],[20],[30]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(L2349NumberContainers.class);
    }
}
