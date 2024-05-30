package cn.hutool.jackie.algorithm.design.p1200_1299;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 新一轮的「力扣杯」编程大赛即将启动，为了动态显示参赛者的得分数据，需要设计一个排行榜 Leaderboard。
 * <p>
 * 请你帮忙来设计这个 Leaderboard 类，使得它有如下 3 个函数：
 * <p>
 * addScore(playerId, score)：
 * <ul>
 * 	<li>假如参赛者已经在排行榜上，就给他的当前得分增加 <code>score</code> 点分值并更新排行。</li>
 * 	<li>假如该参赛者不在排行榜上，就把他添加到榜单上，并且将分数设置为 <code>score</code>。</li>
 * </ul>
 * </li>
 * <li><code>top(K)</code>：返回前 <code>K</code> 名参赛者的 <strong>得分总和</strong>。</li>
 * <li><code>reset(playerId)</code>：将指定参赛者的成绩清零（换句话说，将其从排行榜中删除）。题目保证在调用此函数前，该参赛者已有成绩，并且在榜单上。</li>
 * 请注意，在初始状态下，排行榜是空的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * 输出：
 * [null,null,null,null,null,null,73,null,null,null,141]
 * <p>
 * 解释：
 * Leaderboard leaderboard = new Leaderboard ();
 * leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 * leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 * leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 * leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 * leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.top(1);           // returns 73;
 * leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 * leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 * leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= playerId, K <= 10000
 * 题目保证 K 小于或等于当前参赛者的数量
 * 1 <= score <= 100
 * 最多进行 1000 次函数调用
 *
 * @author rcao1
 */
public class L1244Leaderboard {

    private Map<Integer, Integer> playerScores;

    private TreeMap<Integer, List<Integer>> scorePlayers;

    public L1244Leaderboard() {
        this.playerScores = new HashMap<>();
        this.scorePlayers = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
    }

    public static void main(String[] args) {
        L1244Leaderboard leaderboard = new L1244Leaderboard();
        // leaderboard = [[1,73]];
        leaderboard.addScore(1, 73);
        // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(2, 56);
        // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(3, 39);
        // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(4, 51);
        // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        leaderboard.addScore(5, 4);
        // returns 73;
        System.out.println(leaderboard.top(1));
        // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(1);
        // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.reset(2);
        // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        leaderboard.addScore(2, 51);
        // returns 141 = 51 + 51 + 39;
        System.out.println(leaderboard.top(3));
    }

    private void addScore(int playerId, int score) {
        if (this.playerScores.containsKey(playerId)) {
            int oldScore = this.playerScores.get(playerId);
            List<Integer> playerIds = this.scorePlayers.get(oldScore);
            if (playerIds != null) {
                playerIds.remove(playerId);
            }
            int newScore = oldScore + score;
            this.playerScores.put(playerId, newScore);
            List<Integer> newPlayerIds = this.scorePlayers.get(newScore);
            if (newPlayerIds != null) {
                newPlayerIds.add(playerId);
            } else {
                List<Integer> players = new LinkedList<>();
                players.add(playerId);
                this.scorePlayers.put(newScore, players);
            }
        } else {
            this.playerScores.put(playerId, score);
            List<Integer> newPlayerIds = this.scorePlayers.get(score);
            if (newPlayerIds != null) {
                newPlayerIds.add(playerId);
            } else {
                List<Integer> players = new LinkedList<>();
                players.add(playerId);
                this.scorePlayers.put(score, players);
            }
        }
    }

    private void reset(Integer playerId) {
        int score = this.playerScores.get(playerId);
        List<Integer> playerIds = this.scorePlayers.get(score);
        if (playerIds != null && playerIds.contains(playerId)) {
            playerIds.remove(playerId);
        }
        this.playerScores.remove(playerId);
    }

    private int top(int k) {
        int total = 0;
        int count = 0;
        for (Map.Entry<Integer, List<Integer>> entry : this.scorePlayers.entrySet()) {
            List<Integer> playerIds = entry.getValue();
            for (Integer id : playerIds) {
                total += this.playerScores.get(id);
                count++;
                if (count >= k) {
                    break;
                }
            }
            if (count >= k) {
                break;
            }
        }
        return total;
    }
}
