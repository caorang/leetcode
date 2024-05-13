package cn.hutool.jackie.algorithm.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 读者来到图书馆排队借还书，图书管理员使用两个书车来完成整理借还书的任务。书车中的书从下往上叠加存放，图书管理员每次只能拿取书车顶部的书。排队的读者会有两种操作：
 * <p>
 * push(bookID)：把借阅的书籍还到图书馆。
 * pop()：从图书馆中借出书籍。
 * 为了保持图书的顺序，图书管理员每次取出供读者借阅的书籍是 最早 归还到图书馆的书籍。你需要返回 每次读者借出书的值 。
 * <p>
 * 如果没有归还的书可以取出，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["BookQueue", "push", "push", "pop"]
 * [[], [1], [2], []]
 * 输出：[null,null,null,1]
 * 解释：
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.pop(); // return 1, queue is [2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= bookID <= 10000
 * 最多会对 push、pop 进行 10000 次调用
 *
 * @author rcao1
 */
public class CQueue {

    private Deque<Integer> queue = new ArrayDeque<>();

    public CQueue() {

    }

    public void appendTail(int value) {
        queue.add(value);
    }

    public int deleteHead() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.pop();
    }

    public static void main(String[] args) {
        /**
         *  输入：
         *  ["BookQueue", "push", "push", "pop"]
         *  [[], [1], [2], []]
         *  输出：[null,null,null,1]
         */
        String method = "[\"CQueue\",\"appendTail\",\"deleteHead\",\"deleteHead\",\"deleteHead\"]\n";
        String arguments = "[[],[3],[],[],[]]\n";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(CQueue.class);
    }
}
