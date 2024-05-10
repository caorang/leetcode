package cn.hutool.jackie.algorithm.plus;

/**
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * <p>
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * <p>
 * 你的实现应该支持如下操作：
 * <p>
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * <p>
 * <p>
 * 示例：
 * <p>
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/design-circular-queue/description/?envType=study-plan-v2&envId=mihoyo-2023-fall-sprint">...</a>
 */
public class MyCircularQueue {

    private int capacity;

    private int size;

    private LinkNode front;

    private LinkNode rear;

    public MyCircularQueue(int k) {
        this.capacity = k;
        this.size = 0;
    }

    public boolean enQueue(int value) {
        if (this.size >= capacity) {
            return false;
        }
        LinkNode node = new LinkNode(value);
        if (this.front == null) {
            this.front = node;
            this.rear = node;
            front.setNext(rear);
        } else {
            this.rear.setNext(node);
            node.setNext(front);
            this.rear = node;
        }
        size++;
        return true;
    }

    public boolean deQueue() {
        if (front == null) {
            return false;
        }
        if (size > 1) {
            front = front.getNext();
            rear.setNext(front);
        } else {
            front = null;
            rear = null;
        }
        size--;
        return true;
    }

    public int Front() {
        if (this.front != null) {
            return this.front.getValue();
        }
        return -1;
    }

    public int Rear() {
        if (this.rear != null) {
            return this.rear.getValue();
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size >= capacity;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        System.out.println(circularQueue.enQueue(5));  // 返回 true
        System.out.println(circularQueue.deQueue());  // 返回 true
        System.out.println(circularQueue.enQueue(3));  // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 false，队列已满
        System.out.println(circularQueue.Rear());  // 返回 3
        System.out.println(circularQueue.isFull());  // 返回 true
        System.out.println(circularQueue.deQueue());  // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 true
        System.out.println(circularQueue.Rear());  // 返回 4
    }
}

class LinkNode {

    private int value;
    private LinkNode next;

    LinkNode(int value) {
        this.value = value;
    }

    LinkNode(int value, LinkNode next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public LinkNode getNext() {
        return next;
    }
}
