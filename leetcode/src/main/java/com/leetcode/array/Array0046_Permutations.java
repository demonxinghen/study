package com.leetcode.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * level: medium
 * <p>
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * <p>
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * <p>
 * Output: [[0,1],[1,0]]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1]
 * <p>
 * Output: [[1]]
 */
public class Array0046_Permutations {

    boolean[] used;
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> comb = new LinkedList<>();

    public static void main(String[] args) {
        Array0046_Permutations algorithm = new Array0046_Permutations();
        int[] nums = {1,2,3};
        algorithm.permute(nums);
    }

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtracking(nums);
        return res;
    }

    private void backtracking(int[] nums) {
        if (comb.size() == nums.length){
            res.add(new ArrayList<>(comb));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]){
                continue;
            }
            used[i] = true;
            comb.addLast(nums[i]);
            backtracking(nums);
            comb.removeLast();
            used[i] = false;
        }
    }
}
