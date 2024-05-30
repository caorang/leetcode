package cn.hutool.jackie.algorithm.system;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 * <p>
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * @author rcao1
 */
public class LruCache {

    private int size;
    private int capacity;
    private Map<Integer, LinkNode> map;
    private LinkNode head;
    private LinkNode tail;

    public LruCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        head = new LinkNode(-1, -1);
        tail = new LinkNode(-1, -1);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            LinkNode node = map.get(key);
            node.setValue(value);
            moveToHead(node);
        } else {
            if (size >= capacity) {
                LinkNode last = tail.getPrev();
                map.remove(last.getKey());
                removeNode(last);
                size--;
            }
            LinkNode node = new LinkNode(key, value);
            map.put(key, node);
            addToHead(node);
            size++;
        }
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        LinkNode node = map.get(key);
        moveToHead(node);
        return map.get(key).getValue();
    }

    private void moveToHead(LinkNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(LinkNode node) {
        LinkNode temp = head.getNext();
        node.setPrev(head);
        head.setNext(node);
        node.setNext(temp);
        temp.setPrev(node);
    }

    private void removeNode(LinkNode node) {
        LinkNode temp = node.getPrev();
        temp.setNext(node.getNext());
        node.getNext().setPrev(temp);
    }

    @Override
    public String toString() {
        LinkNode node = head.getNext();
        StringBuilder sb = new StringBuilder();
        sb.append("[size=" + this.size + ", capacity=" + this.capacity + "]: ");
        while (node != null) {
            if (node.getValue() != -1) {
                sb.append(node.getKey() + "#" + node.getValue());
            }
            if (node.getValue() != -1 && node.getNext() != tail) {
                sb.append(" -> ");
            }
            node = node.getNext();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LruCache cache = new LruCache(2);
        cache.put(2, 1);
        System.out.println(cache);
        cache.put(1, 1);
        System.out.println(cache);
        cache.put(2, 3);
        System.out.println(cache);
        cache.put(4, 1);
        System.out.println(cache);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}

class LinkNode {
    private int key;
    private int value;
    private LinkNode prev;
    private LinkNode next;

    public LinkNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return this.key;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LinkNode getPrev() {
        return this.prev;
    }

    public void setPrev(LinkNode prev) {
        this.prev = prev;
    }

    public LinkNode getNext() {
        return this.next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }
}
