package cn.hutool.jackie.algorithm.design.p0700_0799;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 你可以选择使用单链表或者双链表，设计并实现自己的链表。
 * <p>
 * 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * <p>
 * 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
 * <p>
 * 实现 MyLinkedList 类：
 * <p>
 * MyLinkedList() 初始化 MyLinkedList 对象。
 * int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
 * void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
 * void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
 * void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
 * void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
 * 示例：
 * <p>
 * 输入
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * 输出
 * [null, null, null, null, 2, null, 3]
 * <p>
 * 解释
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
 * myLinkedList.get(1);              // 返回 2
 * myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
 * myLinkedList.get(1);              // 返回 3
 * 提示：
 * <p>
 * 0 <= index, val <= 1000
 * 请不要使用内置的 LinkedList 库。
 * 调用 get、addAtHead、addAtTail、addAtIndex 和 deleteAtIndex 的次数不超过 2000 。
 *
 * @author rcao1
 */
public class L0707MyLinkedList {

    static class Node {
        private int value;
        private Node next;
        private Node prev;

        public Node(int value) {
            this.value = value;
        }
    }

    static class MyLinkedList {
        private Node head;
        private Node tail;
        private int size;

        public MyLinkedList() {
            this.head = new Node(Integer.MIN_VALUE);
            this.tail = new Node(Integer.MAX_VALUE);
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.size = 0;
        }

        public void print(String method, String value) {
            System.out.print(method + "(" + value + "): ");
            Node n = this.head;
            while (n != null) {
                System.out.print(n.value + "->");
                n = n.next;
            }
            System.out.println();
        }

        public int get(int index) {
            print("get", index + "");
            if (index < 0 || index + 1 > this.size) {
                System.out.println("index out of range:" + index + ", size = " + this.size);
                return -1;
            }
            Node node = this.head;
            for (int i = 0; i < index + 1; i++) {
                node = node.next;
            }
            return node.value;
        }

        public void addAtHead(int val) {
            Node newNode = new Node(val);
            Node temp = this.head.next;
            this.head.next = newNode;
            newNode.prev = this.head;
            newNode.next = temp;
            temp.prev = newNode;
            this.size++;
            print("addAtHead", val + "");
        }

        public void addAtTail(int val) {
            Node newNode = new Node(val);
            Node temp = this.tail.prev;
            this.tail.prev = newNode;
            newNode.next = this.tail;
            newNode.prev = temp;
            temp.next = newNode;
            this.size++;
            print("addAtTail", val + "");
        }

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > this.size) {
                System.out.println("index out of range:" + index + ", size = " + this.size);
                return;
            }
            Node node = this.head;
            for (int i = 0; i < index + 1; i++) {
                node = node.next;
            }
            Node newNode = new Node(val);
            Node temp = node.prev;
            node.prev = newNode;
            newNode.next = node;
            newNode.prev = temp;
            temp.next = newNode;
            this.size++;
            print("addAtIndex", index + "#" + val);
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index + 1 > this.size) {
                System.out.println("index out of range:" + index + ", size = " + this.size);
                return;
            }
            Node node = this.head;
            for (int i = 0; i < index + 1; i++) {
                node = node.next;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            this.size--;
            print("deleteAtIndex", index + "");
        }
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
         * [[], [1], [3], [1, 2], [1], [1], [1]]
         * 输出
         * [null, null, null, null, 2, null, 3]
         */
        String method = "[\"MyLinkedList\",\"addAtHead\",\"addAtHead\",\"addAtHead\",\"addAtIndex\",\"deleteAtIndex\",\"addAtHead\",\"addAtTail\",\"get\",\"addAtHead\",\"addAtIndex\",\"addAtHead\"]";
        String arguments = "[[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(MyLinkedList.class);
    }
}
