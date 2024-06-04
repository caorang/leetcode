package cn.hutool.jackie.algorithm.design.p1200_1299;

/**
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 * <p>
 * 请返回 封闭岛屿 的数目。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid =
 * [[1,1,1,1,1,1,1,0],
 * [1,0,0,0,0,1,1,0],
 * [1,0,1,0,1,1,1,0],
 * [1,0,0,0,0,1,0,1],
 * [1,1,1,1,1,1,1,0]]
 * 输出：2
 * 解释：
 * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：grid =
 * [[0,0,1,0,0],
 * [0,1,0,1,0],
 * [0,1,1,1,0]]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：grid =
 * [[1,1,1,1,1,1,1],
 * [1,0,0,0,0,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,1,0,1,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,0,0,0,0,1],
 * [1,1,1,1,1,1,1]]
 * 输出：2
 * 提示：
 * <p>
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 *
 * @author rcao1
 */
public class L1254ClosedIsland {

    int[][] grid;
    int n, m, ans;

    public int closedIsland(int[][] grid) {
        this.grid = grid;
        n = this.grid.length;
        m = this.grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (this.grid[i][j] == 0 && dfs(i, j)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    boolean dfs(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return false; // 终止条件1
        }
        if (grid[i][j] == 1) {
            return true; // 终止条件2
        }
        grid[i][j] = 1;
        return dfs(i + 1, j) & dfs(i - 1, j) & dfs(i, j + 1) & dfs(i, j - 1);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        System.out.println(new L1254ClosedIsland().closedIsland(grid));
    }
}
