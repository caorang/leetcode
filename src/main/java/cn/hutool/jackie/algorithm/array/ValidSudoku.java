package cn.hutool.jackie.algorithm.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author rcao1
 * @see <a href="https://leetcode.cn/problems/valid-sudoku/">...</a>
 */
public class ValidSudoku {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board));

        board = new char[][] {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Integer>> rowMap = new HashMap<>(9);
        Map<Integer, Set<Integer>> colMap = new HashMap<>(9);
        Map<String, Set<Integer>> blockMap = new HashMap<>(9);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '0';
                    if (rowMap.containsKey(i)) {
                        Set<Integer> set = rowMap.get(i);
                        if (set.contains(num)) {
                            return false;
                        }
                        set.add(num);
                    } else {
                        Set<Integer> set = new HashSet<>();
                        set.add(num);
                        rowMap.put(i, set);
                    }
                    if (colMap.containsKey(j)) {
                        Set<Integer> set = colMap.get(j);
                        if (set.contains(num)) {
                            return false;
                        }
                        set.add(num);
                    } else {
                        Set<Integer> set = new HashSet<>();
                        set.add(num);
                        colMap.put(j, set);
                    }
                    String key = i / 3 + "_" + j / 3;
                    if (blockMap.containsKey(key)) {
                        Set<Integer> set = blockMap.get(key);
                        if (set.contains(num)) {
                            return false;
                        }
                        set.add(num);
                    } else {
                        Set<Integer> set = new HashSet<>();
                        set.add(num);
                        blockMap.put(key, set);
                    }
                }
            }
        }
        return true;
    }
}
