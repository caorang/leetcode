package cn.hutool.jackie.algorithm.design.p0700_0799;

import java.util.LinkedList;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * <p>
 * 实现 MyHashSet 类：
 * <p>
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * 示例：
 * <p>
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 * <p>
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 * 提示：
 * <p>
 * 0 <= key <= 106
 * 最多调用 104 次 add、remove 和 contains
 *
 * @author rcao1
 */
public class L0705MyHashSet {

    static class MyHashSet {
        private LinkedList<Integer>[] hash;
        private int hashSize = 10000;

        public MyHashSet() {
            this.hash = new LinkedList[hashSize];
        }

        public void add(int key) {
            int hashCode = Integer.valueOf(key).hashCode() % hashSize;
            addToHash(hashCode, key);
        }

        private void addToHash(int hashCode, int key) {
            LinkedList<Integer> linkedList = hash[hashCode];
            if (linkedList == null) {
                linkedList = new LinkedList<>();
                linkedList.add(key);
                hash[hashCode] = linkedList;
            } else {
                boolean found = false;
                for (Integer integer : linkedList) {
                    if (integer.equals(key)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    linkedList.add(key);
                }
            }
        }

        public void remove(int key) {
            int hashCode = Integer.valueOf(key).hashCode() % hashSize;
            removeFromHash(hashCode, key);
        }

        private void removeFromHash(int hashCode, int key) {
            LinkedList<Integer> linkedList = hash[hashCode];
            if (linkedList != null) {
                linkedList.remove(Integer.valueOf(key));
            }
        }

        public boolean contains(int key) {
            int hashCode = Integer.valueOf(key).hashCode() % hashSize;
            LinkedList<Integer> linkedList = hash[hashCode];
            if (linkedList != null) {
                for (Integer integer : linkedList) {
                    if (integer.equals(key)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        /**
         * 输入：
         * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
         * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
         * 输出：
         * [null, null, null, true, false, null, true, null, false]
         */
        String method = "[\"MyHashSet\", \"add\", \"add\", \"contains\", \"contains\", \"add\", \"contains\", \"remove\", \"contains\"]";
        String arguments = "[[], [1], [2], [1], [3], [2], [2], [2], [2]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(MyHashSet.class);
    }
}
