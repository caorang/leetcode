package cn.hutool.jackie.algorithm.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author rcao1
 * @see <a href="https://leetcode.com/problems/lru-cache/">https://leetcode.com/problems/lru-cache/</a>
 */
public class LruCache2<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    /**
     * 传递进来最多能缓存多少数据
     *
     * @param capacity 缓存大小
     */
    public LruCache2(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * 如果map中的数据量大于设定的最大容量，返回true，再新加入对象时删除最老的数据
     *
     * @param eldest 最老的数据项
     * @return true则移除最老的数据
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当 map中的数据量大于指定的缓存个数的时候，自动移除最老的数据
        return size() > capacity;
    }

    public static void main(String[] args) {
        LruCache2<Integer, Integer> lruCache = new LruCache2<>(2);
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
