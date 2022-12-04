package com.example.datastructure;

/**
 * @author: xuh
 * @date: 2022/12/4 20:14
 * @description: 稀疏数组，用于压缩和解压
 */
public class SparseArray {

    public static void main(String[] args) {
        int[][] originalArray = createOriginalArray();
        int[][] sparseArray = sparseArray(originalArray);
        resumeArray(sparseArray);
    }

    /**
     * 将稀疏数组还原为普通数组
     *
     * @param sparseArray
     */
    private static void resumeArray(int[][] sparseArray) {
        int rowLength = sparseArray[0][0];
        int colLength = sparseArray[0][1];
        int resumeArray[][] = new int[rowLength][colLength];

        for (int i = 1; i < sparseArray.length; i++){
            resumeArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        System.out.println("还原后的二维数组为：");
        for (int[] row : resumeArray) {
            for (int col : row) {
                System.out.printf("%d ", col);
            }
            System.out.println();
        }
    }


    /**
     * 稀疏数组：列为3，行=数组中非0数量<br>
     * 第一行为row,col,sum<br>
     * 第二行开始为第几行，第几列，元素值
     *
     * @param originalArray
     * @return
     */
    private static int[][] sparseArray(int[][] originalArray) {
        int sum = 0;
        for (int[] row : originalArray) {
            for (int col : row) {
                if (col != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 10;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 0; i < originalArray.length; i++) {
            for (int j = 0; j < originalArray[i].length; j++) {
                if (originalArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = originalArray[i][j];
                }
            }
        }
        System.out.println("压缩后的稀疏数组为：");
        for (int[] row : sparseArray) {
            System.out.printf("%d %d %d\n", row[0], row[1], row[2]);
        }
        return sparseArray;
    }

    /**
     * 假设有一个10行11列的棋局，0表示无子，1表示黑子，2表示白子
     *
     * @return
     */
    private static int[][] createOriginalArray() {
        int array[][] = new int[10][11];
        array[1][2] = 1;
        array[2][3] = 2;
        array[9][10] = 2;
        array[0][0] = 1;
        array[5][7] = 1;
        System.out.println("原始二维数组为：");
        for (int[] row : array) {
            for (int col : row) {
                System.out.printf("%d ", col);
            }
            System.out.println();
        }
        return array;
    }
}
