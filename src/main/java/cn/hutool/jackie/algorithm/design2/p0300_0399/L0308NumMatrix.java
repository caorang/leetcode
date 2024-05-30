package cn.hutool.jackie.algorithm.design2.p0300_0399;

import java.util.Arrays;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 给你一个二维矩阵 matrix ，处理以下类型的多个查询:
 * <p>
 * 更新 matrix 中单元格的值。
 * 计算由 左上角 (row1, col1) 和 右下角 (row2, col2) 定义的 matrix 内矩阵元素的 和。
 * 实现 NumMatrix 类：
 * <p>
 * NumMatrix(int[][] matrix) 用整数矩阵 matrix 初始化对象。
 * void update(int row, int col, int val) 更新 matrix[row][col] 的值到 val 。
 * int sumRegion(int row1, int col1, int row2, int col2) 返回矩阵 matrix 中指定矩形区域元素的 和 ，该区域由 左上角 (row1, col1) 和 右下角 (row2, col2) 界定。
 * 示例 1：
 * <p>
 * <p>
 * 输入
 * ["NumMatrix", "sumRegion", "update", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
 * 输出
 * [null, 8, null, 10]
 * 解释
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // 返回 8 (即, 左侧红色矩形的和)
 * numMatrix.update(3, 2, 2); // 矩阵从左图变为右图
 * numMatrix.sumRegion(2, 1, 4, 3); // 返回 10 (即，右侧红色矩形的和)
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -10^5 <= matrix[i][j] <= 10^5
 * 0 <= row < m
 * 0 <= col < n
 * -10^5 <= val <= 10^5
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * 最多调用10^4次sumRegion和update方法
 *
 * @author rcao1
 */
public class L0308NumMatrix {

    static class NumMatrix {

        private int[][] matrix;
        private int[][] rowSum;

        /**
         * 用整数矩阵 matrix 初始化对象
         *
         * @param matrix
         */
        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            this.rowSum = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                this.rowSum[i][0] = this.matrix[i][0];
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 1; j < matrix[i].length; j++) {
                    this.rowSum[i][j] = this.matrix[i][j] + this.rowSum[i][j - 1];
                }
            }
        }

        /**
         * 更新 matrix[row][col] 的值到 val
         *
         * @param row
         * @param col
         * @param val
         */
        public void update(int row, int col, int val) {
            System.out.println("before:" + Arrays.toString(rowSum[row]));
            this.matrix[row][col] = val;
            for (int j = col; j < matrix[row].length; j++) {
                this.rowSum[row][j] = this.matrix[row][j] + (j >= 1 ? this.rowSum[row][j - 1] : 0);
            }
            System.out.println("after:" + Arrays.toString(rowSum[row]));
        }

        /**
         * 返回矩阵matrix中指定矩形区域元素的和，该区域由左上角 (row1, col1) 和右下角 (row2, col2)界定
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int total = 0;
            for (int i = row1; i <= row2; i++) {
                total += this.rowSum[i][col2] - this.rowSum[i][col1] + this.matrix[i][col1];
            }
            return total;
        }
    }

    public static void main(String[] args) {
        /**
         * 输入
         * ["NumMatrix", "sumRegion", "update", "sumRegion"]
         * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
         * 输出
         * [null, 8, null, 10]
         */
        String method = "[\"NumMatrix\", \"sumRegion\", \"update\", \"sumRegion\"]";
        String arguments = "[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(NumMatrix.class);
    }
}
