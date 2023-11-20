package cn.hutool.jackie.algorithm.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rcao1
 * @see <a href="https://leetcode.com/problems/lru-cache/">https://leetcode.com/problems/lru-cache/</a>
 */
public class LruCache<K, V> {

    static class Node<K, V> {
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Node head;
    private Map<K, Node> map = new HashMap<>();

    public LruCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            add(node);
            return (V) node.value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            add(node);
        } else {
            if (map.size() >= capacity) {
                Node node = tail();
                remove(node);
                map.remove(node.key);
            }
            Node node = new Node(key, value);
            add(node);
            map.put(key, node);
        }
    }

    private Node tail() {
        return head.prev;
    }

    private void add(Node node) {
        if (head == null) {
            head = node;
            node.prev = head;
            node.next = null;
        } else {
            node.next = head;
            node.prev = head.prev;
            head.prev = node;
            head = node;
        }
    }

    private void remove(Node node) {
        if (head == null) {
            return;
        }
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = head;
        while (node != null) {
            sb.append(node.value).append(",");
            node = node.next;
        }
        return sb.toString();
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
}
