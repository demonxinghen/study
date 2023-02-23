package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * level: medium
 * <p>
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * <p>
 * Output: [1,2,3,6,9,8,7,4,5]
 * <p>
 * Example 2:
 * <p>
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * <p>
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Array0054_SpiralMatrix {

    public static void main(String[] args) {
        Array0054_SpiralMatrix algorithm = new Array0054_SpiralMatrix();
        int[][] matrix = {
                {}
        };
        System.out.println(algorithm.spiralOrder(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0){
            return list;
        }
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;

        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // 先右
            for (int i = colBegin; i <= colEnd; i++) {
                list.add(matrix[rowBegin][i]);
            }
            rowBegin++; // 作为下一步的起始位置
            // 再下
            for (int i = rowBegin; i <= rowEnd; i++) {
                list.add(matrix[i][colEnd]);
            }
            colEnd--;
            // 再左
            if (rowBegin <= rowEnd){
                for (int i = colEnd; i >= colBegin; i--) {
                    list.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;
            // 再上
            if (colBegin <= colEnd){
                for (int i = rowEnd; i >= rowBegin; i--) {
                    list.add(matrix[i][colBegin]);
                }
            }
            colBegin++;
        }
        return list;
    }

}
