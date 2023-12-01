package cn.hutool.jackie.algorithm.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rcao1
 */
public class MinHeap<K extends Comparable, V> {

    private Map<K, TreeNode> cache;

    public MinHeap() {
        cache = new HashMap<>();
    }

    public static void main(String[] args) {
    }

    public V pop() {
        return null;
    }

    public V get(K key) {
        return null;
    }

    public void push(K key, V value) {

    }

    static class TreeNode<K, V> {
        K key;
        V value;
        TreeNode left;
        TreeNode right;

        TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
