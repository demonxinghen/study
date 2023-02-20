package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * level: medium
 * <p>
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * <p>
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7
 * <p>
 * Output: [[2,2,3],[7]]
 * <p>
 * Explanation:
 * <p>
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * <p>
 * 7 is a candidate, and 7 = 7.
 * <p>
 * These are the only two combinations.
 * <p>
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8
 * <p>
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * Example 3:
 * <p>
 * Input: candidates = [2], target = 1
 * <p>
 * Output: []
 */
public class Array0039_CombinationSum {

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        Array0039_CombinationSum algorithm = new Array0039_CombinationSum();
        System.out.println(algorithm.combinationSum(candidates, target));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        backtracking(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }

    /**
     * 回溯的使用
     *
     * @param list
     * @param tempList
     * @param nums
     * @param remain
     * @param start
     * @link src/main/resources/回溯.md
     */
    public void backtracking(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtracking(list, tempList, nums, remain - nums[i], i);// 从i开始, 因为本题中元素可以重复使用
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
