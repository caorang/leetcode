package cn.hutool.jackie.algorithm.design.p0100_0199;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeSet;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 请设计一个自助结账系统，该系统需要通过一个队列来模拟顾客通过购物车的结算过程，需要实现的功能有：
 * <p>
 * get_max()：获取结算商品中的最高价格，如果队列为空，则返回 -1
 * add(value)：将价格为 value 的商品加入待结算商品队列的尾部
 * remove()：移除第一个待结算的商品价格，如果队列为空，则返回 -1
 * 注意，为保证该系统运转高效性，以上函数的均摊时间复杂度均为 O(1)
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * ["Checkout","add","add","get_max","remove","get_max"]
 * [[],[4],[7],[],[],[]]
 * <p>
 * 输出: [null,null,null,7,4,7]
 * 示例 2：
 * <p>
 * 输入:
 * ["Checkout","remove","get_max"]
 * [[],[],[]]
 * <p>
 * 输出: [null,-1,-1]
 * 提示：
 * <p>
 * 1 <= get_max, add, remove 的总操作数 <= 10000
 * 1 <= value <= 10^5
 * Related Topics
 *
 * @author rcao1
 */
public class L0184Checkout {

    public static class Checkout {

        private TreeSet<Integer> set;
        private Deque<Integer> queue;

        public Checkout() {
            this.queue = new ArrayDeque<>();
            this.set = new TreeSet<>((o1, o2) -> o2 - o1);
        }

        public int get_max() {
            if (queue.isEmpty()) {
                return -1;
            }
            return set.first();
        }

        public void add(int value) {
            this.queue.offer(value);
            this.set.add(value);
        }

        public int remove() {
            if (queue.isEmpty()) {
                return -1;
            }
            int temp = queue.poll();
            set.remove(temp);
            return temp;
        }
    }

    public static void main(String[] args) {
        /**
         * 输入:
         * ["Checkout","add","add","get_max","remove","get_max"]
         * [[],[4],[7],[],[],[]]
         * <p>
         * 输出: [null,null,null,7,4,7]
         */
        String method = "[\"Checkout\",\"add\",\"add\",\"get_max\",\"remove\",\"get_max\"]";
        String arguments = "[[],[4],[7],[],[],[]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(Checkout.class);
    }
}
