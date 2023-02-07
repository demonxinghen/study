package com.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * level: medium
 * <p>
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * <p>
 * Each column must contain the digits 1-9 without repetition.
 * <p>
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * <p>
 * Note:
 * <p>
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * <p>
 * Only the filled cells need to be validated according to the mentioned rules.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board =<br/>
 * [["5","3",".",".","7",".",".",".","."]<br/>
 * ,["6",".",".","1","9","5",".",".","."]<br/>
 * ,[".","9","8",".",".",".",".","6","."]<br/>
 * ,["8",".",".",".","6",".",".",".","3"]<br/>
 * ,["4",".",".","8",".","3",".",".","1"]<br/>
 * ,["7",".",".",".","2",".",".",".","6"]<br/>
 * ,[".","6",".",".",".",".","2","8","."]<br/>
 * ,[".",".",".","4","1","9",".",".","5"]<br/>
 * ,[".",".",".",".","8",".",".","7","9"]]
 * <p>
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: board =<br/>
 * [["8","3",".",".","7",".",".",".","."]<br/>
 * ,["6",".",".","1","9","5",".",".","."]<br/>
 * ,[".","9","8",".",".",".",".","6","."]<br/>
 * ,["8",".",".",".","6",".",".",".","3"]<br/>
 * ,["4",".",".","8",".","3",".",".","1"]<br/>
 * ,["7",".",".",".","2",".",".",".","6"]<br/>
 * ,[".","6",".",".",".",".","2","8","."]<br/>
 * ,[".",".",".","4","1","9",".",".","5"]<br/>
 * ,[".",".",".",".","8",".",".","7","9"]]
 * <p>
 * Output: false
 * <p>
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 */
public class Array0036_ValidSudoku {

    public static void main(String[] args) {
        char[][] board = new char[][]{
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
        Array0036_ValidSudoku algorithm = new Array0036_ValidSudoku();
        System.out.println(algorithm.isValidSudoku2(board));
    }

    /**
     * 该方法无法划分出3*3, 思路失败
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            char[] heng = new char[9];
            for (int j = 0; j < heng.length; j++) {
                heng[j] = board[i][j];
            }
            char[] shu = new char[9];
            for (int k = 0; k < shu.length; k++) {
                shu[k] = board[k][i];
            }
            System.out.println();
        }
        for (int i = 0; i < board.length; i++) {
            char[] area = new char[9];
            for (int j = 0; j < board.length; j++) {
                for (int l = 0; l < area.length; l++) {
                    area[l] = board[i / 3 * 3][j / 3 * 3];
                }
            }
            System.out.println();
        }
        return false;
    }

    public boolean isValidSudoku(char[] board) {
        int lo = 0;
        while (lo < board.length) {
            int hi = lo + 1;
            while (hi < board.length && board[hi] != board[lo]) {
                hi++;
            }
            if (hi == board.length) {
                lo++;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 借鉴他人方法
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char cur = board[i][j];
                if (cur != '.')
                    if (!seen.add(cur + "row" + i) || !seen.add(cur + "col" + j) || !seen.add(cur + "grid" + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }
}
