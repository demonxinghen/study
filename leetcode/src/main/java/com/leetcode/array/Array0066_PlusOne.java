package com.leetcode.array;

import java.util.Arrays;

/**
 * level: easy
 * <p>
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
 * <p>
 * Increment the large integer by one and return the resulting array of digits.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: digits = [1,2,3]
 * <p>
 * Output: [1,2,4]
 * <p>
 * Explanation: The array represents the integer 123.
 * <p>
 * Incrementing by one gives 123 + 1 = 124.
 * <p>
 * Thus, the result should be [1,2,4].
 * <p>
 * Example 2:
 * <p>
 * Input: digits = [4,3,2,1]
 * <p>
 * Output: [4,3,2,2]
 * <p>
 * Explanation: The array represents the integer 4321.
 * <p>
 * Incrementing by one gives 4321 + 1 = 4322.
 * <p>
 * Thus, the result should be [4,3,2,2].
 * <p>
 * Example 3:
 * <p>
 * Input: digits = [9]
 * <p>
 * Output: [1,0]
 * <p>
 * Explanation: The array represents the integer 9.
 * <p>
 * Incrementing by one gives 9 + 1 = 10.
 * <p>
 * Thus, the result should be [1,0].
 */
public class Array0066_PlusOne {

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        a = new int[4];
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        Array0066_PlusOne algorithm = new Array0066_PlusOne();
        int[] digits = {9,9,9};
        int[] plusOne = algorithm.plusOne(digits);
        for (int i = 0; i < plusOne.length; i++) {
            System.out.print(plusOne[i] + " ");
        }
    }

    public int[] plusOne(int[] digits) {
        int index = digits.length - 1;
        while (index >= 0){
            if (digits[index] != 9){
                digits[index] = digits[index] + 1;
                break;
            }
            digits[index] = 0;
            index--;
        }
        if (index == -1){
            digits = new int[digits.length + 1]; // 优化, 这种情况下, 除了第一位是1, 其余全是0, 所以不需要拷贝数组.
            digits[0] = 1;
        }
        return digits;
    }
}
