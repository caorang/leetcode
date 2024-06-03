package cn.hutool.jackie.algorithm.design.p0400_0499;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 * <p>
 * 实现 AllOne 类：
 * <p>
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * 注意：每个函数都应当满足 O(1) 平均时间复杂度。
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * 输出
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 * <p>
 * 解释
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "leet"
 * 提示：
 * <p>
 * 1 <= key.length <= 10
 * key 由小写英文字母组成
 * 测试用例保证：在每次调用 dec 时，数据结构中总存在 key
 * 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 10^4 次
 *
 * @author rcao1
 */
public class L0432AllOne {

    static class AllOne {

        private Map<String, Integer> stringCounter;
        private TreeMap<Integer, Set<String>> counters;

        public AllOne() {
            this.stringCounter = new HashMap<>();
            this.counters = new TreeMap<>();
        }

        public void inc(String key) {
            int times = 0;
            if (stringCounter.containsKey(key)) {
                times = stringCounter.get(key);
                stringCounter.put(key, stringCounter.get(key) + 1);
            } else {
                stringCounter.put(key, 1);
            }
            if (counters.containsKey(times)) {
                Set<String> strings = counters.get(times);
                strings.remove(key);
                if (strings.size() == 0) {
                    counters.remove(times);
                }
            }
            counters.computeIfAbsent(times + 1, k -> new HashSet<>()).add(key);
            print();
        }

        public void dec(String key) {
            int times = 0;
            if (stringCounter.containsKey(key)) {
                times = stringCounter.get(key);
                stringCounter.put(key, stringCounter.get(key) - 1);
                if (times == 1) {
                    stringCounter.remove(key);
                }
            }
            if (counters.containsKey(times)) {
                Set<String> strings = counters.get(times);
                strings.remove(key);
                if (strings.size() == 0) {
                    counters.remove(times);
                }
            }
            if (times > 1) {
                counters.computeIfAbsent(times - 1, k -> new HashSet<>()).add(key);
            }
            print();
        }

        public String getMaxKey() {
            if (counters.size() == 0) {
                return "";
            }
            return counters.descendingMap().firstEntry().getValue().stream().findFirst().get();
        }

        public String getMinKey() {
            if (counters.size() == 0) {
                return "";
            }
            return counters.firstEntry().getValue().stream().findFirst().get();
        }

        public void print() {
            System.out.println("string: " + this.stringCounter);
            System.out.println("counter: " + this.counters);
            System.out.println("--------------------------------");
        }
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
         * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
         * 输出
         * [null, null, null, "hello", "hello", null, "hello", "leet"]
         */
        /**
         * input:["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
         * 			[[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]
         * 	Output:[null,null,null,null,null,null,null,null,"a","a"]
         */
        String method = "[\"AllOne\",\"inc\",\"inc\",\"inc\",\"inc\",\"inc\",\"inc\",\"dec\", \"dec\",\"getMinKey\",\"dec\",\"getMaxKey\",\"getMinKey\"]";
        String arguments = "[[],[\"a\"],[\"b\"],[\"b\"],[\"c\"],[\"c\"],[\"c\"],[\"b\"],[\"b\"],[],[\"a\"],[],[]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(AllOne.class);
    }
}
