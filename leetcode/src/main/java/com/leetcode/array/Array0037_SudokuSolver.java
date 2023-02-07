package com.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * level: hard
 * <p>
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * <p>
 * Each of the digits 1-9 must occur exactly once in each column.
 * <p>
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * <p>
 * The '.' character indicates empty cells.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [<br/>
 * ["5","3",".",".","7",".",".",".","."],<br/>
 * ["6",".",".","1","9","5",".",".","."],<br/>
 * [".","9","8",".",".",".",".","6","."],<br/>
 * ["8",".",".",".","6",".",".",".","3"],<br/>
 * ["4",".",".","8",".","3",".",".","1"],<br/>
 * ["7",".",".",".","2",".",".",".","6"],<br/>
 * [".","6",".",".",".",".","2","8","."],<br/>
 * [".",".",".","4","1","9",".",".","5"],<br/>
 * [".",".",".",".","8",".",".","7","9"]<br/>
 * ]
 * <p>
 * Output: [<br/>
 * ["5","3","4","6","7","8","9","1","2"],<br/>
 * ["6","7","2","1","9","5","3","4","8"],<br/>
 * ["1","9","8","3","4","2","5","6","7"],<br/>
 * ["8","5","9","7","6","1","4","2","3"],<br/>
 * ["4","2","6","8","5","3","7","9","1"],<br/>
 * ["7","1","3","9","2","4","8","5","6"],<br/>
 * ["9","6","1","5","3","7","2","8","4"],<br/>
 * ["2","8","7","4","1","9","6","3","5"],<br/>
 * ["3","4","5","2","8","6","1","7","9"]<br/>
 * ]
 * <p>
 * Explanation: The input board is shown above and the only valid solution is shown below:
 */
public class Array0037_SudokuSolver {

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
        Array0037_SudokuSolver algorithm = new Array0037_SudokuSolver();
        algorithm.solveSudoku(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (j == board.length - 1) {
                    System.out.println(board[i][j]);
                } else {
                    System.out.print(board[i][j] + ",");
                }
            }
        }
    }

    /**
     * 借鉴他人方法
     *
     * @param board
     * @return
     */
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }

    public boolean solve(char[][] board, int row, int col) {
        if (row == 9) return true;
        if (col == 9) return solve(board, row + 1, 0);
        if (board[row][col] != '.') return solve(board, row, col + 1);
        for (int i = '1'; i <= '9'; i++) {
            char c = (char) i;
            if (isSafe(board, c, row, col)) {
                board[row][col] = c;
                if (solve(board, row, col + 1)) return true;
                board[row][col] = '.';
            }
        }
        return false;
    }

    public boolean isSafe(char[][] board, char toPlace, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == toPlace) return false;
            if (board[x][i] == toPlace) return false;
        }
        int v = (x / 3) * 3;
        int h = (y / 3) * 3;
        for (int i = v; i < v + 3; i++) {
            for (int j = h; j < h + 3; j++) {
                if (board[i][j] == toPlace) return false;
            }
        }
        return true;
    }
}
