package cn.hutool.jackie.algorithm.design.p2200_2299;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 一个 ATM 机器，存有 5 种面值的钞票：20 ，50 ，100 ，200 和 500 美元。初始时，ATM 机是空的。用户可以用它存或者取任意数目的钱。
 * <p>
 * 取款时，机器会优先取 较大 数额的钱。
 * <p>
 * 比方说，你想取 $300 ，并且机器里有 2 张 $50 的钞票，1 张 $100 的钞票和1 张 $200 的钞票，那么机器会取出 $100 和 $200 的钞票。
 * 但是，如果你想取 $600 ，机器里有 3 张 $200 的钞票和1 张 $500 的钞票，那么取款请求会被拒绝，因为机器会先取出 $500 的钞票，然后无法取出剩余的 $100 。注意，因为有 $500 钞票的存在，机器 不能 取 $200 的钞票。
 * 请你实现 ATM 类：
 * <p>
 * ATM() 初始化 ATM 对象。
 * void deposit(int[] banknotesCount) 分别存入 $20 ，$50，$100，$200 和 $500 钞票的数目。
 * int[] withdraw(int amount) 返回一个长度为 5 的数组，分别表示 $20 ，$50，$100 ，$200 和 $500 钞票的数目，并且更新 ATM 机里取款后钞票的剩余数量。如果无法取出指定数额的钱，请返回 [-1] （这种情况下 不 取出任何钞票）。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["ATM", "deposit", "withdraw", "deposit", "withdraw", "withdraw"]
 * [[], [[0,0,1,2,1]], [600], [[0,1,0,1,1]], [600], [550]]
 * 输出：
 * [null, null, [0,0,1,0,1], null, [-1], [0,1,0,0,1]]
 * <p>
 * 解释：
 * ATM atm = new ATM();
 * atm.deposit([0,0,1,2,1]); // 存入 1 张 $100 ，2 张 $200 和 1 张 $500 的钞票。
 * atm.withdraw(600);        // 返回 [0,0,1,0,1] 。机器返回 1 张 $100 和 1 张 $500 的钞票。机器里剩余钞票的数量为 [0,0,0,2,0] 。
 * atm.deposit([0,1,0,1,1]); // 存入 1 张 $50 ，1 张 $200 和 1 张 $500 的钞票。
 * // 机器中剩余钞票数量为 [0,1,0,3,1] 。
 * atm.withdraw(600);        // 返回 [-1] 。机器会尝试取出 $500 的钞票，然后无法得到剩余的 $100 ，所以取款请求会被拒绝。
 * // 由于请求被拒绝，机器中钞票的数量不会发生改变。
 * atm.withdraw(550);        // 返回 [0,1,0,0,1] ，机器会返回 1 张 $50 的钞票和 1 张 $500 的钞票。
 * <p>
 * <p>
 * 提示：
 * <p>
 * banknotesCount.length == 5
 * 0 <= banknotesCount[i] <= 10^9
 * 1 <= amount <= 10^9
 * 总共 最多有 5000 次 withdraw 和 deposit 的调用。
 * 函数 withdraw 和 deposit 至少各有 一次 调用。
 *
 * @author rcao1
 */
public class L2241ATM {

    private long[][] deposits = new long[5][2];

    /**
     * 20/50/100/200/500
     */
    public L2241ATM() {
        initDeposits();
    }

    private void initDeposits() {
        for (int i = 0; i < this.deposits.length; i++) {
            this.deposits[i] = new long[2];
            this.deposits[i][0] = 0;
            if (i == 0) {
                this.deposits[i][1] = 20;
            } else if (i == 1) {
                this.deposits[i][1] = 50;
            } else if (i == 2) {
                this.deposits[i][1] = 100;
            } else if (i == 3) {
                this.deposits[i][1] = 200;
            } else if (i == 4) {
                this.deposits[i][1] = 500;
            }
        }
    }

    /**
     * 分别存入 $20 ，$50，$100，$200 和 $500 钞票的数目。
     *
     * @param banknotesCount
     */
    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; i++) {
            deposits[i][0] = deposits[i][0] + banknotesCount[i];
        }
    }

    /**
     * 返回一个长度为 5 的数组，分别表示 $20 ，$50，$100 ，$200 和 $500 钞票的数目，并且更新 ATM 机里取款后钞票的剩余数量。
     * 如果无法取出指定数额的钱，请返回 [-1] （这种情况下 不 取出任何钞票）。
     *
     * @param amount
     * @return
     */
    public int[] withdraw(int amount) {
        int[] counts = new int[5];
        for (int i = deposits.length - 1; i >= 0 && amount > 0; i--) {
            long deposit = deposits[i][0];
            long depositValue = deposits[i][1];
            if (deposit > 0 && amount >= depositValue) {
                long count = amount / depositValue;
                if (deposit >= count) {
                    counts[i] = (int) count;
                    amount -= count * depositValue;
                } else {
                    counts[i] = (int) deposit;
                    amount -= deposit * depositValue;
                }
            }
        }
        if (amount > 0) {
            return new int[] {-1};
        }
        for (int i = 0; i < counts.length; i++) {
            deposits[i][0] = deposits[i][0] - counts[i];
        }
        return counts;
    }

    public static void main(String[] args) {
        /**
         *  输入：
         *  ["ATM", "deposit", "withdraw", "deposit", "withdraw", "withdraw"]
         *  [[], [[0,0,1,2,1]], [600], [[0,1,0,1,1]], [600], [550]]
         *  输出：
         *  [null, null, [0,0,1,0,1], null, [-1], [0,1,0,0,1]]
         */
        String method = "[\"ATM\",\"deposit\",\"deposit\",\"withdraw\",\"withdraw\",\"withdraw\",\"withdraw\"]";
        String arguments = "[[],[[250796,638723,691758,845522,938973]],[[215317,848628,182949,784609,30472]],[701035245],[109992310],[755819795],[722349970]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(L2241ATM.class);
    }
}
