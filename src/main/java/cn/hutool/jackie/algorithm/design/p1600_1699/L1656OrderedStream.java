package cn.hutool.jackie.algorithm.design.p1600_1699;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 有 n 个 (id, value) 对，其中 id 是 1 到 n 之间的一个整数，value 是一个字符串。不存在 id 相同的两个 (id, value) 对。
 * <p>
 * 设计一个流，以 任意 顺序获取 n 个 (id, value) 对，并在多次调用时 按 id 递增的顺序 返回一些值。
 * <p>
 * 实现 OrderedStream 类：
 * <p>
 * OrderedStream(int n) 构造一个能接收 n 个值的流，并将当前指针 ptr 设为 1 。
 * String[] insert(int id, String value) 向流中存储新的 (id, value) 对。存储后：
 * 如果流存储有 id = ptr 的 (id, value) 对，则找出从 id = ptr 开始的 最长 id 连续递增序列 ，并 按顺序 返回与这些 id 关联的值的列表。然后，将 ptr 更新为最后那个 id + 1 。
 * 否则，返回一个空列表。
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入
 * ["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
 * [[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
 * 输出
 * [null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
 * <p>
 * 解释
 * OrderedStream os= new OrderedStream(5);
 * os.insert(3, "ccccc"); // 插入 (3, "ccccc")，返回 []
 * os.insert(1, "aaaaa"); // 插入 (1, "aaaaa")，返回 ["aaaaa"]
 * os.insert(2, "bbbbb"); // 插入 (2, "bbbbb")，返回 ["bbbbb", "ccccc"]
 * os.insert(5, "eeeee"); // 插入 (5, "eeeee")，返回 []
 * os.insert(4, "ddddd"); // 插入 (4, "ddddd")，返回 ["ddddd", "eeeee"]
 * 提示：
 * <p>
 * 1 <= n <= 1000
 * 1 <= id <= n
 * value.length == 5
 * value 仅由小写字母组成
 * 每次调用 insert 都会使用一个唯一的 id
 * 恰好调用 n 次 insert
 *
 * @author rcao1
 */
public class L1656OrderedStream {

    static class OrderedStream {
        private int ptr;
        private int capacity;
        private TreeMap<Integer, String> map;

        public OrderedStream(int n) {
            this.capacity = n;
            this.ptr = 1;
            this.map = new TreeMap<>();
        }

        public List<String> insert(int idKey, String value) {
            System.out.println("ptr=" + ptr + ", map=" + map + ", insert:" + idKey + "=" + value);
            List<String> res = new ArrayList<>();
            if (this.map.size() >= capacity) {
                return res;
            }
            this.map.put(idKey, value);
            if (!this.map.containsKey(ptr)) {
                return res;
            }
            Map<Integer, String> subMap = this.map.subMap(ptr, capacity + 1);
            int start = ptr;
            for (Map.Entry<Integer, String> entry : subMap.entrySet()) {
                int key = entry.getKey();
                if (key == start) {
                    res.add(entry.getValue());
                    start++;
                } else {
                    break;
                }
            }
            ptr = start;
            return res;
        }
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
         * [[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
         * 输出
         * [null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
         */
        String method = "[\"OrderedStream\", \"insert\", \"insert\", \"insert\", \"insert\", \"insert\"]";
        String arguments = "[[5], [3, \"ccccc\"], [1, \"aaaaa\"], [2, \"bbbbb\"], [5, \"eeeee\"], [4, \"ddddd\"]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(OrderedStream.class);
    }
}
