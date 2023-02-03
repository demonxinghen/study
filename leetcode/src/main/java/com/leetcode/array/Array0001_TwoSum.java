package com.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * level: easy
 * <p>
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * <p>
 * Output: [0,1]
 * <p>
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class Array0001_TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        Array0001_TwoSum algorithm = new Array0001_TwoSum();
        int[] result = algorithm.twoSum(nums, target);
        System.out.println(result[0] + "-----------" + result[1]);
    }

    public int[] twoSum(int[] nums, int target) {
        return bruteForce(nums, target);
        // return byHashMap(nums, target);
    }

    /**
     * 通过hash map
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] byHashMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    /**
     * 暴力破解
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
