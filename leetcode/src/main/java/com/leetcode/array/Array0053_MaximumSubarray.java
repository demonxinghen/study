package com.leetcode.array;

/**
 * level: medium
 * <p>
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * <p>
 * Output: 6
 * <p>
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1]
 * <p>
 * Output: 1
 * <p>
 * Explanation: The subarray [1] has the largest sum 1.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * <p>
 * Output: 23
 * <p>
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 */
public class Array0053_MaximumSubarray {

    public static void main(String[] args) {
        Array0053_MaximumSubarray algorithm = new Array0053_MaximumSubarray();
        int[] nums = {-1, -6, -4, -9};
        System.out.println(algorithm.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(sum, max);
            if (sum < 0){
                sum = 0;
            }
        }
        return max;
    }
}
