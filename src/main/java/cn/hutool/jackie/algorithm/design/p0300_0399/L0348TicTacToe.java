package cn.hutool.jackie.algorithm.design.p0300_0399;

/**
 * 请在 n × n 的棋盘上，实现一个判定井字棋（Tic-Tac-Toe）胜负的神器，判断每一次玩家落子后，是否有胜出的玩家。
 * <p>
 * 在这个井字棋游戏中，会有 2 名玩家，他们将轮流在棋盘上放置自己的棋子。
 * <p>
 * 在实现这个判定器的过程中，你可以假设以下这些规则一定成立：
 * <p>
 * 1. 每一步棋都是在棋盘内的，并且只能被放置在一个空的格子里；
 * <p>
 * 2. 一旦游戏中有一名玩家胜出的话，游戏将不能再继续；
 * <p>
 * 3. 一个玩家如果在同一行、同一列或者同一斜对角线上都放置了自己的棋子，那么他便获得胜利。
 * <p>
 * 示例:
 * <p>
 * 给定棋盘边长 n = 3, 玩家 1 的棋子符号是 "X"，玩家 2 的棋子符号是 "O"。
 * <p>
 * TicTacToe toe = new TicTacToe(3);
 * <p>
 * toe.move(0, 0, 1); -> 函数返回 0 (此时，暂时没有玩家赢得这场对决)
 * |X| | |
 * | | | |    // 玩家 1 在 (0, 0) 落子。
 * | | | |
 * <p>
 * toe.move(0, 2, 2); -> 函数返回 0 (暂时没有玩家赢得本场比赛)
 * |X| |O|
 * | | | |    // 玩家 2 在 (0, 2) 落子。
 * | | | |
 * <p>
 * toe.move(2, 2, 1); -> 函数返回 0 (暂时没有玩家赢得比赛)
 * |X| |O|
 * | | | |    // 玩家 1 在 (2, 2) 落子。
 * | | |X|
 * <p>
 * toe.move(1, 1, 2); -> 函数返回 0 (暂没有玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 2 在 (1, 1) 落子。
 * | | |X|
 * <p>
 * toe.move(2, 0, 1); -> 函数返回 0 (暂无玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 1 在 (2, 0) 落子。
 * |X| |X|
 * <p>
 * toe.move(1, 0, 2); -> 函数返回 0 (没有玩家赢得比赛)
 * |X| |O|
 * |O|O| |    // 玩家 2 在 (1, 0) 落子.
 * |X| |X|
 * <p>
 * toe.move(2, 1, 1); -> 函数返回 1 (此时，玩家 1 赢得了该场比赛)
 * |X| |O|
 * |O|O| |    // 玩家 1 在 (2, 1) 落子。
 * |X|X|X|
 * 进阶:
 * 您有没有可能将每一步的 move() 操作优化到比 O(n2) 更快吗?
 *
 * @author rcao1
 */
public class L0348TicTacToe {
    static class TicTacToe {
        private char[][] map;
        private int size;
        private int currentPlayer;

        public TicTacToe(int n) {
            this.size = n;
            this.map = new char[this.size][this.size];
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    this.map[i][j] = '#';
                }
            }
        }

        public int move(int row, int col, int player) {
            this.currentPlayer = player;
            if (this.map[row][col] == '#') {
                if (player == 1) {
                    this.map[row][col] = 'X';
                } else {
                    this.map[row][col] = 'O';
                }
            } else {
                return 0;
            }
            print();
            int win = checkWin(row, col);
            if (win != 0) {
                return win;
            }
            resetPlayer();
            return 0;
        }

        private void resetPlayer() {
            if (this.currentPlayer == 1) {
                this.currentPlayer = 2;
            } else {
                this.currentPlayer = 1;
            }
        }

        private int checkWin(int row, int col) {
            char c = this.currentPlayer == 1 ? 'X' : 'O';
            boolean win = true;
            for (int i = 0; i < this.map.length; i++) {
                if (this.map[i][col] != c) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return this.currentPlayer;
            }

            win = true;
            for (int j = 0; j < this.map[row].length; j++) {
                if (this.map[row][j] != c) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return this.currentPlayer;
            }

            win = true;
            for (int i = 0; i < this.size; i++) {
                if (this.map[i][i] != c) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return this.currentPlayer;
            }

            win = true;
            for (int i = 0; i < this.size; i++) {
                if (this.map[i][this.size - i - 1] != c) {
                    win = false;
                    break;
                }
            }
            return win ? this.currentPlayer : 0;
        }

        public void print() {
            System.out.println("--------------------------------");
            for (int i = 0; i < this.map.length; i++) {
                for (int j = 0; j < this.map[i].length; j++) {
                    System.out.print(this.map[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        TicTacToe toe = new TicTacToe(3);
        System.out.println(toe.move(0, 0, 1));
        System.out.println(toe.move(0, 2, 2));
        System.out.println(toe.move(1, 1, 1));
        System.out.println(toe.move(2, 2, 2));
        System.out.println(toe.move(1, 2, 1));
        System.out.println(toe.move(1, 0, 2));
        System.out.println(toe.move(0, 1, 1));
        System.out.println(toe.move(2, 1, 2));
        System.out.println(toe.move(2, 0, 1));
    }
}
