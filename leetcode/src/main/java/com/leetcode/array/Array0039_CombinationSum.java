package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.println(list);
        return list;
    }

    /**
     * 回溯的使用
     * @link src/main/resources/回溯.md
     * @param list
     * @param tempList
     * @param nums
     * @param remain
     * @param start
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
                backtracking(list, tempList, nums, remain - nums[i], i); // 从i开始, 因为本题中元素可以重复使用
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
