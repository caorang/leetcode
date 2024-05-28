package cn.hutool.jackie.algorithm.design2.p0600_0699;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 设计实现双端队列。
 * <p>
 * 实现 MyCircularDeque 类:
 * <p>
 * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
 * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
 * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
 * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
 * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
 * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
 * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false 。
 * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
 * 示例 1：
 * <p>
 * 输入
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * 输出
 * [null, true, true, true, false, 2, true, true, true, 4]
 * <p>
 * 解释
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull 调用次数不大于 2000 次
 *
 * @author rcao1
 */
public class L0641MyCircularDeque {

    static class Node {
        public int value;
        public Node previous;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    static class MyCircularDeque {
        private int capacity;
        private int size;
        private Node head;
        private Node tail;

        public MyCircularDeque(int k) {
            this.capacity = k;
            this.size = 0;
            initNode();
        }

        private void initNode() {
            this.head = new Node(Integer.MIN_VALUE);
            this.tail = new Node(Integer.MIN_VALUE);
            this.head.next = this.tail;
            this.tail.previous = this.head;
        }

        public boolean insertFront(int value) {
            if (size >= capacity) {
                return false;
            }
            Node newNode = new Node(value);
            Node temp = this.head.next;
            temp.previous = newNode;
            this.head.next = newNode;
            newNode.previous = this.head;
            newNode.next = temp;
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (size >= capacity) {
                return false;
            }
            Node newNode = new Node(value);
            Node temp = this.tail.previous;
            temp.next = newNode;
            this.tail.previous = newNode;
            newNode.next = this.tail;
            newNode.previous = temp;
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (size <= 0) {
                return false;
            }
            this.head.next = this.head.next.next;
            this.head.next.previous = this.head;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (size <= 0) {
                return false;
            }
            this.tail.previous = this.tail.previous.previous;
            this.tail.previous.next = this.tail;
            size--;
            return true;
        }

        public int getFront() {
            if (size <= 0) {
                return -1;
            }
            return this.head.next.value;
        }

        public int getRear() {
            if (size <= 0) {
                return -1;
            }
            return this.tail.previous.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
         * [[3], [1], [2], [3], [4], [], [], [], [4], []]
         * 输出
         * [null, true, true, true, false, 2, true, true, true, 4]
         */
        String method = "[\"MyCircularDeque\", \"insertLast\", \"insertLast\", \"insertFront\", \"insertFront\", \"getRear\", \"isFull\", \"deleteLast\", \"insertFront\", \"getFront\"]";
        String arguments = "[[3], [1], [2], [3], [4], [], [], [], [4], []]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(MyCircularDeque.class);
    }
}
