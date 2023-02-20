package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * level: hard
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4
 * <p>
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * <p>
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1
 * <p>
 * Output: [["Q"]]
 */
public class Array0061_NQueens {

    public static void main(String[] args) {
        Array0061_NQueens algorithm = new Array0061_NQueens();
        List<List<String>> lists = algorithm.solveNQueens(8);
        for (List<String> list : lists) {
            System.out.println(list);
        }
        System.out.printf("共有解法%d种", lists.size());
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board= new char[n][n];
        for (int i = 0; i < n; i++) { // i是行,j是列
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtracking(0, n, result, board);
        return result;
    }

    private void backtracking(int i, int n,List<List<String>> result,char[][] board) {
        if (i == n){
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                temp.add(new String(board[j]));
            }
            result.add(temp);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (isSafe(i, j, board, n)){
                board[i][j] = 'Q';
                backtracking(i + 1, n, result, board);
                board[i][j] = '.';
            }
        }
    }

    /**
     * 判断第j列, i以上的行没有Q<br/>
     * / 判断这个方向的斜线没有<br/>
     * \ 判断这个方向的斜线没有<br/>
     * 因为从上往下放, 所以都不会超过i行
     * @param i
     * @param j
     * @param board
     * @param n
     * @return
     */
    private boolean isSafe(int i, int j, char[][] board, int n) {
        // 同一列
        int k = 0;
        for (; k < i; k++) {
            if (board[k][j] == 'Q'){
                return false;
            }
        }

        // \线
        k = i;
        int l = j;
        while (k >= 0 && l >= 0){
            if (board[k--][l--] == 'Q'){
                return false;
            }
        }

        // /线
        k = i;
        l = j;
        while (k >=0 && l < n){
            if (board[k--][l++] == 'Q'){
                return false;
            }
        }
        return true;
    }
}
