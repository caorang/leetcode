package cn.hutool.jackie.algorithm.graph;

import java.util.Arrays;

/**
 * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 * <p>
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * 解释:
 * 城市航班图如下
 * <p>
 * <p>
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 * 示例 2：
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 * 解释:
 * 城市航班图如下
 * <p>
 * <p>
 * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * 航班没有重复，且不存在自环
 * 0 <= src, dst, k < n
 * src != dst
 *
 * @author rcao1
 * @see <a href="https://leetcode.com/problems/cheapest-flights-within-k-stops/">...</a>
 */
public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        System.out.println(new CheapestFlightsWithinKStops()
                .findCheapestPrice(3, new int[][] {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
        System.out.println(new CheapestFlightsWithinKStops()
                .findCheapestPrice(3, new int[][] {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] f = new int[k + 2][n];
        for (int i = 0; i < k + 2; ++i) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        f[0][src] = 0;
        for (int i = 1; i <= k + 1; i++) {
            for (int j = 0; j < flights.length; j++) {
                int[] flight = flights[j];
                if (f[i - 1][flight[0]] != Integer.MAX_VALUE) {
                    f[i][flight[1]] = Math.min(f[i][flight[1]], f[i - 1][flight[0]] + flight[2]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int t = 1; t <= k + 1; ++t) {
            ans = Math.min(ans, f[t][dst]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
