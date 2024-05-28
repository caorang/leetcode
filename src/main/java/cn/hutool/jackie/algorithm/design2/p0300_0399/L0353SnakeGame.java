package cn.hutool.jackie.algorithm.design2.p0300_0399;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 请你设计一个 贪吃蛇游戏，该游戏将会在一个 屏幕尺寸 = 宽度 x 高度 的屏幕上运行。如果你不熟悉这个游戏，可以 点击这里 在线试玩。
 * <p>
 * 起初时，蛇在左上角的 (0, 0) 位置，身体长度为 1 个单位。
 * <p>
 * 你将会被给出一个数组形式的食物位置序列 food ，其中 food[i] = (ri, ci) 。当蛇吃到食物时，身子的长度会增加 1 个单位，得分也会 +1 。
 * <p>
 * 食物不会同时出现，会按列表的顺序逐一显示在屏幕上。比方讲，第一个食物被蛇吃掉后，第二个食物才会出现。
 * <p>
 * 当一个食物在屏幕上出现时，保证 不会 出现在被蛇身体占据的格子里。
 * <p>
 * 如果蛇越界（与边界相撞）或者头与 移动后 的身体相撞（即，身长为 4 的蛇无法与自己相撞），游戏结束。
 * <p>
 * 实现 SnakeGame 类：
 * <p>
 * SnakeGame(int width, int height, int[][] food) 初始化对象，屏幕大小为 height x width ，食物位置序列为 food
 * int move(String direction) 返回蛇在方向 direction 上移动后的得分。如果游戏结束，返回 -1 。
 * 示例 1：
 * <p>
 * <p>
 * 输入：
 * ["SnakeGame", "move", "move", "move", "move", "move", "move"]
 * [[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
 * 输出：
 * [null, 0, 0, 1, 1, 2, -1]
 * 解释：
 * SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
 * snakeGame.move("R"); // 返回 0
 * snakeGame.move("D"); // 返回 0
 * snakeGame.move("R"); // 返回 1 ，蛇吃掉了第一个食物，同时第二个食物出现在 (0, 1)
 * snakeGame.move("U"); // 返回 1
 * snakeGame.move("L"); // 返回 2 ，蛇吃掉了第二个食物，没有出现更多食物
 * snakeGame.move("U"); // 返回 -1 ，蛇与边界相撞，游戏结束
 * 提示：
 * <p>
 * 1 <= width, height <= 10^4
 * 1 <= food.length <= 50
 * food[i].length == 2
 * 0 <= ri < height
 * 0 <= ci < width
 * direction.length == 1
 * direction is 'U', 'D', 'L', or 'R'.
 * 最多调用 10^4 次 move 方法
 *
 * @author rcao1
 */
public class L0353SnakeGame {

    static class Block {
        public int x;
        public int y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class SnakeGame {
        private int width;
        private int height;
        private int[][] food;
        private int foodSequence = -1;
        private Deque<Block> snake = new ArrayDeque<>();

        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            initSnake();
            showFood();
        }

        private void initSnake() {
            this.snake.offer(new Block(0, 0));
        }

        private Block getSnakeHead() {
            if (snake.size() >= 0) {
                return this.snake.getLast();
            }
            return null;
        }

        private Block getSnakeTail() {
            return this.snake.peek();
        }

        private void showFood() {
            if (foodSequence < food.length - 1) {
                foodSequence++;
            }
        }

        private Block getFood() {
            return new Block(this.food[foodSequence][0], this.food[foodSequence][1]);
        }

        /**
         * 0: 空闲；1：蛇；2：食物
         *
         * @param x
         * @param y
         * @return
         */
        private int getBlockStatus(int x, int y) {
            if (this.snake.size() > 0) {
                for (Block block : this.snake) {
                    if (block.x == x && block.y == y) {
                        return 1;
                    }
                }
            }
            Block food = getFood();
            if (food.x == x && food.y == y) {
                return 2;
            }
            return 0;
        }

        public int move(String direction) {
            Block head = getSnakeHead();
            int x = head.x;
            int y = head.y;
            int nextX = x;
            int nextY = y;
            if (direction.equals("U")) {
                nextX = x - 1;
                if (nextX < 0) {
                    return -1;
                }
            } else if (direction.equals("D")) {
                nextX = x + 1;
                if (nextX >= this.height) {
                    return -1;
                }
            } else if (direction.equals("L")) {
                nextY = y - 1;
                if (nextY < 0) {
                    return -1;
                }
            } else if (direction.equals("R")) {
                nextY = y + 1;
                if (nextY >= this.width) {
                    return -1;
                }
            }
            Block tail = this.snake.poll();
            int status = getBlockStatus(nextX, nextY);
            if (status == 1) {
                return -1;
            } else if (status == 2) {
                this.snake.offer(new Block(nextX, nextY));
                this.snake.addFirst(tail);
                showFood();
            } else { // 0
                this.snake.offer(new Block(nextX, nextY));
            }
            //print();
            return this.snake.size() - 1;
        }
    }

    public static void main(String[] args) {
        /**
         * 输入：
         * input:["SnakeGame","move","move","move","move","move","move","move","move","move","move","move","move"]
         * 			[[3,3,[[2,0],[0,0],[0,2],[2,2]]],["D"],["D"],["R"],["U"],["U"],["L"],["D"],["R"],["R"],["U"],["L"],["D"]]
         * Expected:[null,0,1,1,1,1,2,2,2,2,3,3,3]
         */
        String method = "[\"SnakeGame\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\"]";
        String arguments = "[[3,3,[[2,0],[0,0],[0,2],[2,2]]],[\"D\"],[\"D\"],[\"R\"],[\"U\"],[\"U\"],[\"L\"],[\"D\"],[\"R\"],[\"R\"],[\"U\"],[\"L\"],[\"D\"]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(SnakeGame.class);

        /**
         * Your input:["SnakeGame","move","move","move","move","move","move","move","move","move","move","move","move"]
         * 			[[10000,10000,[[0,1],[0,2],[0,3],[0,4],[1,4],[2,4],[2,3],[2,2],[2,1],[2,0],[1,0]]],["R"],["R"],["R"],["R"],["D"],["D"],["L"],["L"],["L"],["L"],["U"],["U"]]
         */
        method = "[\"SnakeGame\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\",\"move\"]";
        arguments =
                "[[10000,10000,[[0,1],[0,2],[0,3],[0,4],[1,4],[2,4],[2,3],[2,2],[2,1],[2,0],[1,0]]],[\"R\"],[\"R\"],[\"R\"],[\"R\"],[\"D\"],[\"D\"],[\"L\"],[\"L\"],[\"L\"],[\"L\"],[\"U\"],[\"U\"]]";
        runner = new CaseRunner(method, arguments);
        runner.run(SnakeGame.class);
    }
}
