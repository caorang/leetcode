package cn.hutool.jackie.algorithm.design.p0300_0399;

/**
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 * <p>
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
 * 实现 NumMatrix 类：
 * <p>
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入:
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 * 输出:
 * [null, 8, 11, 12]
 * <p>
 * 解释:
 * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -105 <= matrix[i][j] <= 10^5
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * 最多调用 104 次 sumRegion 方法
 *
 * @author rcao1
 */
public class L0304NumMatrix {
    static class NumMatrix {

        private int[][] matrix;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    res += matrix[i][j];
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][] {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
        numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
        numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
        numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
    }
}
