package cn.hutool.jackie.algorithm.backtrace;

import java.util.Arrays;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9
 *
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/n-queens-ii/">...</a>
 */
public class NsQueens2 {

    char[][] board;
    int n;
    int res = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println("QueensNum: " + i + ", Total: " + new NsQueens2().totalNQueens(i));
        }
    }

    public int totalNQueens(int _n) {
        n = _n;
        res = 0;
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            // '.'表示没有放置国王
            Arrays.fill(board[i], '.');
        }
        // 按照行的顺序进行遍历
        dfs(0);
        return res;
    }

    /**
     * 深度优先 + 回溯
     *
     * @param r
     */
    private void dfs(int r) {
        // 遍历到n,说明[0,n-1]行已经全部遍历完毕,合法结果+1
        if (r == n) {
            res++;
        }
        // 遍历第r行的所有列
        for (int c = 0; c < n; c++) {
            // 无效的直接跳过
            if (!valid(r, c)) {
                continue;
            }
            // 可以放置的在该处放置国王
            board[r][c] = 'Q';
            // 继续判断下一行(因为一行只能放一个)
            dfs(r + 1);
            // 撤回(回到原来这行肯定要删除掉刚刚标记的'Q')
            board[r][c] = '.';
        }
    }

    /**
     * 判定在[r,c]坐标放置国王是否合法(排除法)
     *
     * @param r
     * @param c
     * @return
     */
    private boolean valid(int r, int c) {
        // 同一行必定不会重复放置(递归逻辑决定)
        // 因此只要判断列与两条斜边即可
        // 列[0,r-1]
        for (int i = 0; i < r; i++) {
            if (board[i][c] != '.') {
                return false;
            }
        }
        // 斜边:左上->右下
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] != '.') {
                return false;
            }
        }
        // 斜边:右上->左下
        for (int i = r - 1, j = c + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (board[i][j] != '.') {
                return false;
            }
        }
        // 排除后的情形就是合法的
        return true;
    }
}
