package cn.hutool.jackie.algorithm.design.p0900_0999;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 给你两个整数数组 persons 和 times 。在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。
 * <p>
 * 对于发生在时刻 t 的每个查询，需要找出在 t 时刻在选举中领先的候选人的编号。
 * <p>
 * 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 * <p>
 * 实现 TopVotedCandidate 类：
 * <p>
 * TopVotedCandidate(int[] persons, int[] times) 使用 persons 和 times 数组初始化对象。
 * int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
 * 示例：
 * <p>
 * 输入：
 * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
 * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
 * 输出：
 * [null, 0, 1, 1, 0, 0, 1]
 * <p>
 * 解释：
 * TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
 * topVotedCandidate.q(3); // 返回 0 ，在时刻 3 ，票数分布为 [0] ，编号为 0 的候选人领先。
 * topVotedCandidate.q(12); // 返回 1 ，在时刻 12 ，票数分布为 [0,1,1] ，编号为 1 的候选人领先。
 * topVotedCandidate.q(25); // 返回 1 ，在时刻 25 ，票数分布为 [0,1,1,0,0,1] ，编号为 1 的候选人领先。（在平局的情况下，1 是最近获得投票的候选人）。
 * topVotedCandidate.q(15); // 返回 0
 * topVotedCandidate.q(24); // 返回 0
 * topVotedCandidate.q(8); // 返回 1
 * 提示：
 * <p>
 * 1 <= persons.length <= 5000
 * times.length == persons.length
 * 0 <= persons[i] < persons.length
 * 0 <= times[i] <= 10^9
 * times 是一个严格递增的有序数组
 * times[0] <= t <= 10^9
 * 每个测试用例最多调用 10^4 次 q
 *
 * @author rcao1
 */
public class L0911TopVotedCandidate {

    static class TopVotedCandidate {

        private int[] win;
        private int[] times;

        public TopVotedCandidate(int[] persons, int[] times) {
            this.times = times;
            this.win = new int[persons.length];
            Map<Integer, Integer> votes = new HashMap<>();
            int max = 0;
            for (int i = 0; i < persons.length; i++) {
                if (votes.containsKey(persons[i])) {
                    votes.put(persons[i], votes.get(persons[i]) + 1);
                } else {
                    votes.put(persons[i], 1);
                }
                int vote = votes.get(persons[i]);
                if (vote >= max) {
                    max = vote;
                    this.win[i] = persons[i];
                } else {
                    this.win[i] = this.win[i - 1];
                }
            }
            System.out.println(Arrays.toString(win));
        }

        public int getTimeIndex(int target) {
            int left = 0;
            int right = times.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (times[mid] == target) {
                    return mid;
                } else if (times[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left - 1;
        }

        public int q(int t) {
            return this.win[getTimeIndex(t)];
        }
    }

    public static void main(String[] args) {
        /**
         * 输入：
         * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
         * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
         * 输出：
         * [null, 0, 1, 1, 0, 0, 1]
         */
        String method = "[\"TopVotedCandidate\",\"q\",\"q\",\"q\",\"q\",\"q\",\"q\",\"q\",\"q\",\"q\",\"q\"]";
        String arguments = "[[[0,0,1,1,2],[0,67,69,74,87]],[4],[62],[100],[88],[70],[73],[22],[75],[29],[10]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(TopVotedCandidate.class);
    }
}
