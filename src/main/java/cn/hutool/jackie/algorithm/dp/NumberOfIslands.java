package cn.hutool.jackie.algorithm.dp;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/number-of-islands/description/">...</a>
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        case3();
    }

    private static void case1() {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(new NumberOfIslands().numIslands(grid));
    }

    private static void case2() {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(new NumberOfIslands().numIslands(grid));
    }

    private static void case3() {
        char[][] grid = {
                {'0', '1'},
                {'1', '0'}
        };
        System.out.println(new NumberOfIslands().numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0] == '1' ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i - 1][0] == '0' && grid[i][0] == '1' ? dp[i - 1][0] + 1 : dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j - 1] == '0' && grid[0][j] == '1' ? dp[0][j - 1] + 1 : dp[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == '0') {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    if (grid[i - 1][j] == '0' && grid[i][j - 1] == '0') {
                        dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
