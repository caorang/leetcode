package cn.hutool.jackie.algorithm.design;

/**
 * @author rcao1
 */
public class Node<T extends Comparable<T>, V> {
    T key;
    V value;
    Color color;
    Node<T, V> left, right, parent;

    Node(T key, V value) {
        this.key = key;
        this.value = value;
        color = Color.RED;
        left = right = parent = null;
    }

    public T getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public Node<T, V> getLeft() {
        return left;
    }

    public Node<T, V> getRight() {
        return right;
    }

    public Node<T, V> getParent() {
        return parent;
    }
}
