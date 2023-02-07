package com.leetcode.array;

import java.util.Arrays;

/**
 * level: hard
 * <p>
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * <p>
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,0]
 * <p>
 * Output: 3
 * <p>
 * Explanation: The numbers in the range [1,2] are all in the array.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,4,-1,1]
 * <p>
 * Output: 2
 * <p>
 * Explanation: 1 is in the array but 2 is missing.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [7,8,9,11,12]
 * <p>
 * Output: 1
 * <p>
 * Explanation: The smallest positive integer 1 is missing.
 */
public class Array0041_FirstMissingPositive {

    public static void main(String[] args) {
        int[] nums = {0};
        Array0041_FirstMissingPositive algorithm = new Array0041_FirstMissingPositive();
        System.out.println(algorithm.firstMissingPositive(nums));
    }

    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == result) {
                result++;
            }
        }
        return result;
    }
}
