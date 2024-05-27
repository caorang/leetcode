package cn.hutool.jackie.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author rcao1
 */
public class QueueImplementStack {

    private Deque<Integer> queue1 = new ArrayDeque<>();
    private Deque<Integer> queue2 = new ArrayDeque<>();

    int size = 0;

    public QueueImplementStack() {

    }

    public void push(int x) {
        queue1.offer(x);
        this.size++;
    }

    public int pop() {
        moveQ1WithoutTailToQ2();
        this.size--;
        int temp = queue1.poll();
        moveQ2ToQ1();
        return temp;
    }

    private void moveQ1WithoutTailToQ2() {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
    }

    private void moveQ2ToQ1() {
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
    }

    public int top() {
        moveQ1WithoutTailToQ2();
        int temp = queue1.peek();
        queue2.offer(queue1.poll());
        moveQ2ToQ1();
        return temp;
    }

    public boolean empty() {
        return this.size == 0;
    }

    public static void main(String[] args) {
        QueueImplementStack obj = new QueueImplementStack();
        obj.push(1);
        obj.push(2);
        System.out.println(obj.top());
        System.out.println(obj.pop());
        System.out.println(obj.empty());
    }
}
