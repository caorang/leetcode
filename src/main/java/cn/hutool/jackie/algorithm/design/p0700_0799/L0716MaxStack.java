package cn.hutool.jackie.algorithm.design.p0700_0799;

import java.util.*;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 设计一个最大栈数据结构，既支持栈操作，又支持查找栈中最大元素。
 * <p>
 * 实现 MaxStack 类：
 * <p>
 * MaxStack() 初始化栈对象
 * void push(int x) 将元素 x 压入栈中。
 * int pop() 移除栈顶元素并返回这个元素。
 * int top() 返回栈顶元素，无需移除。
 * int peekMax() 检索并返回栈中最大元素，无需移除。
 * int popMax() 检索并返回栈中最大元素，并将其移除。如果有多个最大元素，只要移除 最靠近栈顶 的那个。
 * 示例：
 * <p>
 * 输入
 * ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
 * [[], [5], [1], [5], [], [], [], [], [], []]
 * 输出
 * [null, null, null, null, 5, 5, 1, 5, 1, 5]
 * <p>
 * 解释
 * MaxStack stk = new MaxStack();
 * stk.push(5);   // [5] - 5 既是栈顶元素，也是最大元素
 * stk.push(1);   // [5, 1] - 栈顶元素是 1，最大元素是 5
 * stk.push(5);   // [5, 1, 5] - 5 既是栈顶元素，也是最大元素
 * stk.top();     // 返回 5，[5, 1, 5] - 栈没有改变
 * stk.popMax();  // 返回 5，[5, 1] - 栈发生改变，栈顶元素不再是最大元素
 * stk.top();     // 返回 1，[5, 1] - 栈没有改变
 * stk.peekMax(); // 返回 5，[5, 1] - 栈没有改变
 * stk.pop();     // 返回 1，[5] - 此操作后，5 既是栈顶元素，也是最大元素
 * stk.top();     // 返回 5，[5] - 栈没有改变
 * 提示：
 * <p>
 * -107 <= x <= 107
 * 最多调用 104 次 push、pop、top、peekMax 和 popMax
 * 调用 pop、top、peekMax 或 popMax 时，栈中 至少存在一个元素
 * 进阶：
 * <p>
 * 试着设计解决方案：调用 top 方法的时间复杂度为 O(1) ，调用其他方法的时间复杂度为 O(logn) 。
 *
 * @author rcao1
 */
public class L0716MaxStack {

    static class MaxStack {

        private TreeSet<int[]> stack;
        private TreeSet<int[]> values;
        private int cnt;

        public MaxStack() {
            Comparator<int[]> comp = (a, b) -> {
                return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
            };
            stack = new TreeSet<>(comp);
            values = new TreeSet<>(comp);
            cnt = 0;
        }

        public void push(int x) {
            stack.add(new int[] {cnt, x});
            values.add(new int[] {x, cnt});
            cnt++;
        }

        public int pop() {
            int[] pair = stack.pollLast();
            values.remove(new int[] {pair[1], pair[0]});
            return pair[1];
        }

        public int top() {
            return stack.last()[1];
        }

        public int peekMax() {
            return values.last()[0];
        }

        public int popMax() {
            int[] pair = values.pollLast();
            stack.remove(new int[] {pair[1], pair[0]});
            return pair[0];
        }
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
         * [[], [5], [1], [5], [], [], [], [], [], []]
         * 输出
         * [null, null, null, null, 5, 5, 1, 5, 1, 5]
         */
        String method = "[\"MaxStack\",\"push\",\"push\",\"popMax\",\"peekMax\"]";
        String arguments = "[[],[5],[1],[],[]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(MaxStack.class);
    }
}
