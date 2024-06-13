package cn.hutool.jackie.algorithm.design.p0300_0399;

import java.util.Map;
import java.util.TreeMap;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 * <p>
 * 实现 SummaryRanges 类：
 * <p>
 * SummaryRanges() 使用一个空数据流初始化对象。
 * void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 * 示例：
 * <p>
 * 输入：
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * 输出：
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 * <p>
 * 解释：
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // 返回 [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
 * 提示：
 * <p>
 * 0 <= val <= 10^4
 * 最多调用 addNum 和 getIntervals 方法 3 * 10^4 次
 * 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
 *
 * @author rcao1
 */
public class L0352SummaryRanges {


    static class SummaryRanges {

        private TreeMap<Integer, Integer> ranges;

        public SummaryRanges() {
            this.ranges = new TreeMap<>();
        }

        public void addNum(int value) {
            mergeRanges(value);
            System.out.println(this.ranges);
        }

        private void mergeRanges(int value) {
            Map.Entry<Integer, Integer> floorEntry = this.ranges.floorEntry(value - 1);
            if (this.ranges.containsKey(value)) {
                return;
            }

            boolean floorMerged = false;
            if (floorEntry != null && floorEntry.getValue() == value - 1) {
                this.ranges.put(floorEntry.getKey(), value);
                this.ranges.remove(value);
                floorMerged = true;
            } else if (floorEntry != null && floorEntry.getValue() >= value) {
                floorMerged = true;
            }

            boolean ceilMerged = false;
            Map.Entry<Integer, Integer> ceilingEntry = this.ranges.ceilingEntry(value + 1);
            if (ceilingEntry != null && ceilingEntry.getKey() == value + 1) {
                this.ranges.remove(ceilingEntry.getKey());
                this.ranges.put(value, ceilingEntry.getValue());
                if (floorMerged) {
                    this.ranges.remove(value);
                    this.ranges.put(floorEntry.getKey(), ceilingEntry.getValue());
                }
                ceilMerged = true;
            } else if (ceilingEntry != null && ceilingEntry.getKey() <= value) {
                ceilMerged = true;
            }

            if (!floorMerged && !ceilMerged) {
                this.ranges.put(value, value);
            }
        }

        public int[][] getIntervals() {
            int[][] res = new int[this.ranges.size()][2];
            int i = 0;
            for (Map.Entry<Integer, Integer> entry : this.ranges.entrySet()) {
                res[i][0] = entry.getKey();
                res[i][1] = entry.getValue();
                i++;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        /**
         * 输入：
         * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
         * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
         * 输出：
         * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
         */
        String method =
                "[\"SummaryRanges\",\"addNum\",\"getIntervals\",\"addNum\",\"getIntervals\",\"addNum\",\"getIntervals\",\"addNum\",\"getIntervals\",\"addNum\",\"getIntervals\",\"addNum\",\"getIntervals\",\"addNum\",\"getIntervals\",\"addNum\",\"getIntervals\",\"addNum\",\"getIntervals\",\"addNum\",\"getIntervals\"]";
        String arguments = "[[],[6],[],[6],[],[0],[],[4],[],[8],[],[7],[],[6],[],[4],[],[7],[],[5],[]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(SummaryRanges.class);
    }
}
