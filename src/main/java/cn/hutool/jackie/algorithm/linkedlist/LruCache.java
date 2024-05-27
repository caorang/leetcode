package cn.hutool.jackie.algorithm.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rcao1
 * @see <a href="https://leetcode.com/problems/lru-cache/">https://leetcode.com/problems/lru-cache/</a>
 */
public class LruCache<K, V> {

    private int capacity;
    private int size;
    private LinkedNode head;
    private LinkedNode tail;
    private Map<K, LinkedNode> cache;
    public LruCache(int capacity) {
        cache = new HashMap<K, LinkedNode>();
        this.capacity = capacity;
        this.size = 0;
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public static void main(String[] args) {
        LruCache<Integer, Integer> lruCache = new LruCache<>(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);

        System.out.println("first: " + lruCache);
        System.out.println("get(1): " + lruCache.get(1));
        System.out.println("after get(1): " + lruCache);

        lruCache.put(3, 3);
        System.out.println("after put(3): " + lruCache);

        System.out.println("get(2): " + lruCache.get(2));
        System.out.println("after get(2): " + lruCache);

        lruCache.put(4, 4);
        System.out.println("after put(4): " + lruCache);

        System.out.println("get(1): " + lruCache.get(1));
        System.out.println("after get(1): " + lruCache);

        System.out.println("get(3): " + lruCache.get(3));
        System.out.println("after get(3): " + lruCache);

        System.out.println("get(4): " + lruCache.get(4));
        System.out.println("after get(4): " + lruCache);
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            LinkedNode node = cache.get(key);
            moveToHead(node);
            return (V) node.value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            LinkedNode node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            LinkedNode node = new LinkedNode(key, value);
            cache.put(key, node);
            addToHead(node);
            ++size;
            if (size > capacity) {
                LinkedNode res = tail.prev;
                removeNode(res);
                cache.remove(res.key);
                --size;
            }
        }
    }

    private void addToHead(LinkedNode node) {
        LinkedNode temp = head.next;
        head.next = node;
        node.prev = head;
        node.next = temp;
        temp.prev = node;
    }

    private void removeNode(LinkedNode res) {
        LinkedNode prev = res.prev;
        LinkedNode next = res.next;
        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(LinkedNode node) {
        removeNode(node);
        addToHead(node);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedNode LinkedNode = head;
        while (LinkedNode != null) {
            sb.append(LinkedNode.value).append(",");
            LinkedNode = LinkedNode.next;
        }
        return sb.toString();
    }

    static class LinkedNode<K, V> {
        K key;
        V value;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode() {
        }

        public LinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
