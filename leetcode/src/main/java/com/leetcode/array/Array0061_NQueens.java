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
        N = 8;
        Array0061_NQueens algorithm = new Array0061_NQueens();
        List<List<String>> lists = algorithm.solveNQueens(N);
        // for (List<String> list : lists) {
        //     System.out.println(list);
        // }
        // System.out.printf("共有解法%d种", lists.size());
        System.out.println();

        res = new ArrayList<>();
        char[][] emptyBoard = new char[N][N];
        long bitStart = System.currentTimeMillis();
        algorithm.backtrackingWithBitManipulation(emptyBoard, 0, 0, 0, 0);
        long bitEnd = System.currentTimeMillis();
        System.out.println("bit耗时" + (bitEnd - bitStart) + "毫秒");
        // for (List<String> list : res) {
        //     System.out.println(list);
        // }
        System.out.printf("共有解法%d种", res.size());
    }

    private static int N;
    private static List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board= new char[n][n];
        for (int i = 0; i < n; i++) { // i是行,j是列
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        long start = System.currentTimeMillis();
        backtracking(0, n, result, board);
        long end = System.currentTimeMillis();
        System.out.println("普通回溯耗时" + (end - start) + "毫秒");
        return result;
    }

    /**
     * 解题链接: https://leetcode.com/problems/n-queens/solutions/2107776/explained-with-diagrams-backtracking-and-bit-manipulation/
     * @param board 临时数组
     * @param row
     * @param cols
     * @param diags
     * @param antiDiags
     */
    private void backtrackingWithBitManipulation(char[][] board, int row, int cols, int diags, int antiDiags) {
        if (row == N){ //从第一行开始往下放, row是索引, row == N - 1的时候就是正在放最后一行了, 如果row == N, 说明最后一行放好了, 存入结果集
            res.add(toBoard(board));
            return;
        }
        for (int col = 0; col < N; col++) { // col 表示从第一列开始放
            int currDiag = row - col + N; // 计算对角线, 比如row为1, col为1,
            int curAntiDiag = row + col;

            // &操作只有1&1 = 1, 其他都为0, 所以如果不等于0, 则说明已经存在一个皇后
            if ((cols & (1 << col)) != 0 || (diags & (1 << currDiag)) != 0 || (antiDiags & (1 << curAntiDiag)) != 0){
                continue;
            }

            // 不存在, 则开始尝试
            board[row][col] = 'Q';
            cols |= (1 << col);
            diags |= (1 << currDiag);
            antiDiags |= (1 << curAntiDiag);

            // 回溯
            backtrackingWithBitManipulation(board, row + 1, cols, diags, antiDiags);

            // 还原, 继续下一个位置尝试
            board[row][col] = '.';
            cols ^= (1 << col);
            diags ^= (1 << currDiag);
            antiDiags ^= (1 << curAntiDiag);
        }
    }

    private List<String> toBoard(char[][] board) {
        List<String> newBoard = new ArrayList<>();
        for (char[] row : board) {
            newBoard.add(new String(row));
        }
        return newBoard;
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
