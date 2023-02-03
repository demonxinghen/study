package com.leetcode.array;

import java.util.Arrays;

/**
 * level: medium
 * <p>
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * <p>
 * Return the sum of the three integers.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 * <p>
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 */
public class Array0016_3SumCloset {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0};
        int target = -100;
        Array0016_3SumCloset algorithm = new Array0016_3SumCloset();
        int result = algorithm.threeSumClosest(nums, target);
        System.out.println(result);
    }

    /**
     * @param nums
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        System.out.println(bruteForce(nums, target));
        System.out.println(twoPointers(nums, target));
        return 0;
    }

    /**
     * 双指针, 要排序
     *
     * @param nums
     * @param target
     * @return
     */
    public int twoPointers(int[] nums, int target) {
        Arrays.sort(nums);

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1;
            int hi = nums.length - 1;

            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
                if (Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }
            }
        }
        return result;
    }

    /**
     * 暴力破解, 不用排序
     *
     * @param nums
     * @param target
     * @return
     */
    public int bruteForce(int[] nums, int target) {
        int result = Integer.MAX_VALUE;
        int sum;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(target - sum) < Math.abs(target - result)) {
                        result = sum;
                    }
                    if (sum == target) {
                        return result;
                    }
                    ;
                }
            }
        }
        return result;
    }
}