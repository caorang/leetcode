package cn.hutool.jackie.algorithm.design2.p0100_0199;

import java.util.HashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * @author rcao1
 */
@SuppressWarnings("ALL")
public class L0146LRUCache<K, V> {

    public static class LinkedNode<K, V> {
        K key;
        V value;
        LinkedNode previous;
        LinkedNode next;
    }

    private LinkedNode head;

    private LinkedNode tail;

    private Map<K, LinkedNode> map;

    private int capacity = 0;

    public L0146LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new LinkedNode();
        this.tail = new LinkedNode();
        this.map = new HashMap<>();
        this.head.next = tail;
        this.tail.previous = head;
    }

    public void put(K key, V value) {
        int size = map.size();
        if (map.containsKey(key)) {
            moveToHead(map.get(key));
        } else {
            if (size >= capacity) {
                this.map.remove(tail.previous.key);
                removeNode(tail.previous);
            }
            LinkedNode newNode = new LinkedNode();
            newNode.key = key;
            newNode.value = value;
            this.map.put(key, newNode);
            addToHead(newNode);
        }
    }

    private void addToHead(LinkedNode<K, V> node) {
        LinkedNode temp = this.head.next;
        node.previous = head;
        node.next = temp;
        temp.previous = node;
        this.head.next = node;
    }

    private void moveToHead(LinkedNode<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(LinkedNode<K, V> node) {
        LinkedNode temp1 = node.previous;
        LinkedNode temp2 = node.next;
        temp1.next = temp2;
        temp2.previous = temp1;
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            LinkedNode<K, V> node = map.get(key);
            moveToHead(node);
            return node.value;
        }
        return null;
    }

    public static void main(String[] args) {
        L0146LRUCache lRUCache = new L0146LRUCache<Integer, String>(2);
        // 缓存是 {1=1}
        lRUCache.put(1, 1);
        // 缓存是 {1=1, 2=2}
        lRUCache.put(2, 2);
        // 返回 1
        Object value = lRUCache.get(1);
        System.out.println(value);
        // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.put(3, 3);
        // 返回 -1 (未找到)
        value = lRUCache.get(2);
        System.out.println(value);
        // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.put(4, 4);
        // 返回 -1 (未找到)
        value = lRUCache.get(1);
        System.out.println(value);
        // 返回 3
        value = lRUCache.get(3);
        System.out.println(value);
        // 返回 4
        value = lRUCache.get(4);
        System.out.println(value);
    }
}
