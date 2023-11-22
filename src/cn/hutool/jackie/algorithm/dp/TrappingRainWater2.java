package cn.hutool.jackie.algorithm.dp;

import java.util.PriorityQueue;

/**
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/trapping-rain-water-ii/">...</a>
 */
public class TrappingRainWater2 {

    public static void main(String[] args) {
        int[][] heightMap = {{3, 3, 3, 3, 3}, {3, 2, 2, 2, 3}, {3, 2, 1, 2, 3}, {3, 2, 2, 2, 3}, {3, 3, 3, 3, 3}};
        System.out.println(new TrappingRainWater2().trapRainWater(heightMap));

        heightMap = new int[][] {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};
        System.out.println(new TrappingRainWater2().trapRainWater(heightMap));
    }

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int trapRainWater(int[][] heightMap) {
        // 使用堆解决，保存<x,y,h>，表示位置和高度
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        // 将边界加进去
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    visited[i][j] = true;
                    heap.offer(new int[] {i, j, heightMap[i][j]});
                }
            }
        }

        int ans = 0;

        // 每次从堆中弹出最小的元素，并检查它上下左右的位置是否可以注水
        while (!heap.isEmpty()) {
            int[] element = heap.poll();
            int x = element[0];
            int y = element[1];
            int h = element[2];

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    if (heightMap[nx][ny] < h) {
                        ans += h - heightMap[nx][ny];
                    }
                    visited[nx][ny] = true;
                    heap.offer(new int[] {nx, ny, Math.max(h, heightMap[nx][ny])});
                }
            }
        }

        return ans;
    }
}
