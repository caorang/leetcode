package cn.hutool.jackie.algorithm.array;

/**
 * @author rcao1
 * @see <a href="https://leetcode.com/problems/minimum-path-sum/">...</a>
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minimumPathSum.minPathSum(grid));
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int top = dp[i - 1][j] + grid[i][j];
                int left = dp[i][j - 1] + grid[i][j];
                dp[i][j] = Math.min(top, left);
            }
        }
        return dp[m - 1][n - 1];
    }
}
