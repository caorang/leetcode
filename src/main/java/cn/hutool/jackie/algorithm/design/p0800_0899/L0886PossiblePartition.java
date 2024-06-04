package cn.hutool.jackie.algorithm.design.p0800_0899;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * <p>
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和 bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 * 示例 2：
 * <p>
 * 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 * 提示：
 * <p>
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 104
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * dislikes 中每一组都 不同
 *
 * @author rcao1
 */
public class L0886PossiblePartition {

    /**
     * 我们可以尝试用「染色法」来解决问题
     * 假设第一组中的人是红色，第二组中的人为蓝色。
     * 我们依次遍历每一个人，如果当前的人没有被分组，就将其分到第一组（即染为红色），那么这个人不喜欢的人必须分到第二组中（染为蓝色）。
     * 然后任何新被分到第二组中的人，其不喜欢的人必须被分到第一组，依此类推。
     * 如果在染色的过程中存在冲突，就表示这个任务是不可能完成的，否则说明染色的过程有效（即存在合法的分组方案）。
     *
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] color = new int[n + 1];
        Map<Integer, Set<Integer>> dislikeMap = new HashMap<>(8);
        for (int[] dislike : dislikes) {
            dislikeMap.computeIfAbsent(dislike[0], k -> new HashSet<>()).add(dislike[1]);
            dislikeMap.computeIfAbsent(dislike[1], k -> new HashSet<>()).add(dislike[0]);
        }
        for (int i = 1; i <= n; ++i) {
            if (color[i] == 0 && !dfs(i, 1, color, dislikeMap)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 深度遍历进行染色，0：未上色
     *
     * @param curNode
     * @param nowColor
     * @param color
     * @param dislikeMap
     * @return
     */
    public boolean dfs(int curNode, int nowColor, int[] color, Map<Integer, Set<Integer>> dislikeMap) {
        color[curNode] = nowColor;
        if (!dislikeMap.containsKey(curNode)) {
            return true;
        }
        for (int nextNode : dislikeMap.get(curNode)) {
            if (color[nextNode] != 0 && color[nextNode] == color[curNode]) {
                return false;
            }
            int newColor = nowColor == 1 ? 2 : 1;
            if (color[nextNode] == 0 && !dfs(nextNode, newColor, color, dislikeMap)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        L0886PossiblePartition partition = new L0886PossiblePartition();
        System.out.println(partition.possibleBipartition(4, new int[][] {{1, 2}, {1, 3}, {2, 4}}));
        System.out.println(partition.possibleBipartition(3, new int[][] {{1, 2}, {1, 3}, {2, 3}}));
        System.out.println(partition.possibleBipartition(5, new int[][] {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}));
    }
}
