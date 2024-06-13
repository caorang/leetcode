package cn.hutool.jackie.algorithm.design.p0600_0699;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.jackie.algorithm.CaseRunner;

/**
 * 请你设计 Excel 中的基本功能，并实现求和公式。
 * <p>
 * 实现 Excel 类：
 * <p>
 * Excel(int height, char width)：用高度 height 和宽度 width 初始化对象。该表格是一个大小为 height x width 的整数矩阵 mat，其中行下标范围是 [1, height] ，列下标范围是 ['A', width] 。初始情况下，所有的值都应该为 零 。
 * void set(int row, char column, int val)：将 mat[row][column] 的值更改为 val 。
 * int get(int row, char column)：返回 mat[row][column] 的值。
 * int sum(int row, char column, List numbers)：将 mat[row][column] 的值设为由 numbers 表示的单元格的和，并返回 mat[row][column] 的值。此求和公式应该 长期作用于 该单元格，直到该单元格被另一个值或另一个求和公式覆盖。其中，numbers[i] 的格式可以为：
 * "ColRow"：表示某个单元格。
 * 例如，"F7" 表示单元格 mat[7]['F'] 。
 * "ColRow1:ColRow2"：表示一组单元格。该范围将始终为一个矩形，其中 "ColRow1" 表示左上角单元格的位置，"ColRow2" 表示右下角单元格的位置。
 * 例如，"B3:F7" 表示 3 <= i <= 7 和 'B' <= j <= 'F' 的单元格 mat[i][j] 。
 * 注意：可以假设不会出现循环求和引用。
 * <p>
 * 例如，mat[1]['A'] == sum(1, "B")，且 mat[1]['B'] == sum(1, "A") 。
 * 示例 1：
 * <p>
 * 输入：
 * ["Excel", "set", "sum", "set", "get"]
 * [[3, "C"], [1, "A", 2], [3, "C", ["A1", "A1:B2"]], [2, "B", 2], [3, "C"]]
 * 输出：
 * [null, null, 4, null, 6]
 * <p>
 * 解释：
 * 执行以下操作：
 * Excel excel = new Excel(3, "C");
 * // 构造一个 3 * 3 的二维数组，所有值初始化为零。
 * //   A B C
 * // 1 0 0 0
 * // 2 0 0 0
 * // 3 0 0 0
 * excel.set(1, "A", 2);
 * // 将 mat[1]["A"] 设置为 2 。
 * //   A B C
 * // 1 2 0 0
 * // 2 0 0 0
 * // 3 0 0 0
 * excel.sum(3, "C", ["A1", "A1:B2"]); // 返回 4
 * // 将 mat[3]["C"] 设置为 mat[1]["A"] 的值与矩形范围的单元格和的和，该范围的左上角单元格位置为 mat[1]["A"] ，右下角单元格位置为 mat[2]["B"] 。
 * //   A B C
 * // 1 2 0 0
 * // 2 0 0 0
 * // 3 0 0 4
 * excel.set(2, "B", 2);
 * // 将 mat[2]["B"] 设置为 2 。注意 mat[3]["C"] 也应该更改。
 * //   A B C
 * // 1 2 0 0
 * // 2 0 2 0
 * // 3 0 0 6
 * excel.get(3, "C"); // 返回 6
 * 提示：
 * <p>
 * 1 <= height <= 26
 * 'A' <= width <= 'Z'
 * 1 <= row <= height
 * 'A' <= column <= width
 * -100 <= val <= 100
 * 1 <= numbers.length <= 5
 * numbers[i] 的格式为 "ColRow" 或 "ColRow1:ColRow2" 。
 * 最多会对 set 、get 和 sum 进行 100 次调用。
 *
 * @author rcao1
 */
public class L0632Excel {

    static class Excel {
        private int[][] mat;
        private Map<String, List<String>> formulas;

        private int parseChar(char c) {
            return c - 'A' + 1;
        }

        public Excel(int height, char width) {
            int iWidth = parseChar(width);
            this.mat = new int[height + 1][iWidth + 1];
            this.formulas = new HashMap<>();
        }

        public void set(int row, char column, int val) {
            this.mat[row][parseChar(column)] = val;
            String key = row + "_" + parseChar(column);
            if (this.formulas.containsKey(key)) {
                this.formulas.remove(key);
            }
        }

        public int get(int row, char column) {
            return calculate(row, parseChar(column));
        }

        public int sum(int row, char column, String[] numbers) {
            int iColumn = parseChar(column);
            String key = row + "_" + iColumn;
            this.formulas.put(key, Arrays.asList(numbers));
            return calculate(row, iColumn);
        }

        public int calculate(int row, int column) {
            int sum = 0;
            String key = row + "_" + column;
            if (this.formulas.containsKey(key)) {
                List<String> formulaList = this.formulas.get(key);
                for (String formula : formulaList) {
                    if (formula.contains(":")) {
                        String[] pos = formula.split(":");
                        int[] positions1 = parsePosition(pos[0]);
                        int[] positions2 = parsePosition(pos[1]);
                        for (int i = positions1[0]; i <= positions2[0]; i++) {
                            for (int j = positions1[1]; j <= positions2[1]; j++) {
                                sum += calculate(i, j);
                            }
                        }
                    } else {
                        int[] positions = parsePosition(formula);
                        sum += calculate(positions[0], positions[1]);
                    }
                }
            } else {
                sum += mat[row][column];
            }
            return sum;
        }

        public int[] parsePosition(String position) {
            String column = position.substring(0, 1);
            int row = Integer.valueOf(position.substring(1, position.length()));
            return new int[] {row, parseChar(column.charAt(0))};
        }
    }

    public static void main(String[] args) {
        /**
         * 输入：
         * ["Excel", "set", "sum", "set", "get"]
         * [[3, "'C'"], [1, "A", 2], [3, "C", ["A1", "A1:B2"]], [2, "B", 2], [3, "C"]]
         * 输出：
         * [null, null, 4, null, 6]
         */
        String method = "[\"Excel\",\"sum\",\"set\",\"sum\",\"get\"]";
        String arguments = "[[3,\"C\"],[1,\"A\",[\"A2\"]],[2,\"A\",1],[3,\"A\",[\"A1\"]],[1,\"A\"]]";
        CaseRunner runner = new CaseRunner(method, arguments);
        runner.run(Excel.class);
    }
}
