package com.leetcode.array;

/**
 * level: medium
 * <p>
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * <p>
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * Example 2:
 * <p>
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * <p>
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
public class Array0048_RotateImage {

    public static void main(String[] args) {
        Array0048_RotateImage algorithm = new Array0048_RotateImage();
        int[][] matrix = {
                        {5,1,9,11},
                        {2,4,8,10},
                        {13,3,6,7},
                        {15,14,12,16}
        };
        algorithm.rotate(matrix);
    }

    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length; i++) { // 通过坐标互换实现横变竖
            for (int j = i; j < length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < length; i++) { // 再通过左右互换
            for (int j = 0; j < length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][length - j - 1]; // 0,0 和0,3互换
                matrix[i][length - j - 1] = temp;
            }
        }
    }
}
