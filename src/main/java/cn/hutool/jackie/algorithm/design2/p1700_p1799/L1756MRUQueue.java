package cn.hutool.jackie.algorithm.design2.p1700_p1799;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 设计一种类似队列的数据结构，该数据结构将最近使用的元素移到队列尾部。
 * <p>
 * 实现 MRUQueue 类：
 * <p>
 * MRUQueue(int n)  使用 n 个元素： [1,2,3,...,n] 构造 MRUQueue 。
 * fetch(int k) 将第 k 个元素（从 1 开始索引）移到队尾，并返回该元素。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["MRUQueue", "fetch", "fetch", "fetch", "fetch"]
 * [[8], [3], [5], [2], [8]]
 * 输出：
 * [null, 3, 6, 2, 2]
 * <p>
 * 解释：
 * MRUQueue mRUQueue = new MRUQueue(8); // 初始化队列为 [1,2,3,4,5,6,7,8]。
 * mRUQueue.fetch(3); // 将第 3 个元素 (3) 移到队尾，使队列变为 [1,2,4,5,6,7,8,3] 并返回该元素。
 * mRUQueue.fetch(5); // 将第 5 个元素 (6) 移到队尾，使队列变为 [1,2,4,5,7,8,3,6] 并返回该元素。
 * mRUQueue.fetch(2); // 将第 2 个元素 (2) 移到队尾，使队列变为 [1,4,5,7,8,3,6,2] 并返回该元素。
 * mRUQueue.fetch(8); // 第 8 个元素 (2) 已经在队列尾部了，所以直接返回该元素即可。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2000
 * 1 <= k <= n
 * 最多调用 2000 次 fetch
 * <p>
 * <p>
 * 进阶：找到每次 fetch 的复杂度为 O(n) 的算法比较简单。你可以找到每次 fetch 的复杂度更佳的算法吗？
 *
 * @author rcao1
 */
public class L1756MRUQueue {

    class LinkedNode {
        int pos;
        int value;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }
    }

    private LinkedNode head;
    private LinkedNode tail;

    private Map<Integer, LinkedNode> nodes;

    public L1756MRUQueue(int n) {
        this.head = new LinkedNode(0, Integer.MIN_VALUE);
        this.tail = new LinkedNode(-1, Integer.MAX_VALUE);
        this.nodes = new HashMap<>();
        LinkedNode node = head;
        for (int i = 0; i < n; i++) {
            LinkedNode newNode = new LinkedNode(i + 1, i + 1);
            this.nodes.put(i + 1, newNode);
            node.next = newNode;
            LinkedNode temp = node;
            node = node.next;
            node.prev = temp;
        }
        node.next = this.tail;
        this.tail.prev = node;
    }

    /**
     * 将第 k 个元素（从 1 开始索引）移到队尾，并返回该元素。
     *
     * @param k
     * @return
     */
    public int fetch(int k) {
        LinkedNode node = head;
        int i = 0;
        while (node.next != null && i < k) {
            node = node.next;
            i++;
        }
        removeToTail(node);
        return node.value;
    }

    private void removeToTail(LinkedNode node) {
        LinkedNode prev = node.prev;
        LinkedNode next = node.next;
        prev.next = next;
        next.prev = prev;
        next.pos = node.pos;

        LinkedNode insert = this.tail.prev;
        node.pos = insert.pos + 1;
        insert.next = node;
        node.next = this.tail;
        node.prev = insert;
        this.tail.prev = node;
    }

    public static void main(String[] args) {
        String method = "[\"MRUQueue\",\"fetch\",\"fetch\",\"fetch\",\"fetch\"]";
        String arguments = "[[8],[3],[5],[2],[8]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(L1756MRUQueue.class);
    }
}
