package cn.hutool.jackie.algorithm.design2.p0700_0799;

import java.util.LinkedList;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 * <p>
 * 实现 MyHashMap 类：
 * <p>
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 * 示例：
 * <p>
 * 输入：
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * 输出：
 * [null, null, null, 1, -1, null, 1, null, -1]
 * <p>
 * 解释：
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 * 提示：
 * <p>
 * 0 <= key, value <= 106
 * 最多调用 104 次 put、get 和 remove 方法
 *
 * @author rcao1
 */
public class L0706MyHashMap {

    static class Node {
        public int key;
        public int value;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static class MyHashMap {

        private LinkedList<Node>[] hash;
        private int hashSize;

        public MyHashMap() {
            this.hashSize = 32;
            this.hash = new LinkedList[hashSize];
        }

        public void put(int key, int value) {
            int hashCode = Integer.valueOf(key).hashCode() % hashSize;
            addToHash(hashCode, key, value);
        }

        private void addToHash(int hashCode, int key, int value) {
            LinkedList<Node> linkedList = hash[hashCode];
            if (linkedList == null) {
                linkedList = new LinkedList<>();
                linkedList.add(new Node(key, value));
                hash[hashCode] = linkedList;
            } else {
                boolean found = false;
                for (Node node : linkedList) {
                    if (node.key == key) {
                        node.value = value;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    linkedList.add(new Node(key, value));
                }
            }
        }

        public int get(int key) {
            int hashCode = Integer.valueOf(key).hashCode() % hashSize;
            return getFromHash(hashCode, key);
        }

        public int getFromHash(int hashCode, int key) {
            LinkedList<Node> linkedList = hash[hashCode];
            if (linkedList != null) {
                for (Node node : linkedList) {
                    if (node.key == key) {
                        return node.value;
                    }
                }
            }
            return -1;
        }

        public void remove(int key) {
            int hashCode = Integer.valueOf(key).hashCode() % hashSize;
            removeFromHash(hashCode, key);
        }

        private void removeFromHash(int hashCode, int key) {
            LinkedList<Node> linkedList = hash[hashCode];
            if (linkedList != null) {
                int removeIndex = -1;
                for (int i = 0; i < linkedList.size(); i++) {
                    if (linkedList.get(i).key == key) {
                        removeIndex = i;
                        break;
                    }
                }
                if (removeIndex >= 0) {
                    linkedList.remove(removeIndex);
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 输入：
         * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
         * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
         * 输出：
         * [null, null, null, 1, -1, null, 1, null, -1]
         */
        String method = "[\"MyHashMap\", \"put\", \"put\", \"get\", \"get\", \"put\", \"get\", \"remove\", \"get\"]";
        String arguments = "[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(MyHashMap.class);
    }
}
