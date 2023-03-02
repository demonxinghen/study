package com.leetcode.array;

import java.util.*;

/**
 * level: medium
 * <p>
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * <p>
 * Output:
 * <p>
 * [[1,1,2],
 * <p>
 * [1,2,1],
 * <p>
 * [2,1,1]]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,3]
 * <p>
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Array0047_PermutationsⅡ {

    public static void main(String[] args) {
        Array0047_PermutationsⅡ algorithm = new Array0047_PermutationsⅡ();
        int[] nums = {1, 2, 1};
        algorithm.permuteUnique(nums);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (!counter.containsKey(num)) {
                counter.put(num, 0);
            }
            counter.put(num, counter.get(num) + 1);
        }

        LinkedList<Integer> comb = new LinkedList<>();
        backtracking(comb, nums.length, counter, results);
        return results;
    }

    private void backtracking(LinkedList<Integer> comb, int length, Map<Integer, Integer> counter, List<List<Integer>> results) {
        if (comb.size() == length){
            results.add(new ArrayList<>(comb));
            return;
        }
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();

            if (count == 0){
                continue;
            }

            comb.addLast(num);
            counter.put(num, count - 1);

            backtracking(comb, length, counter, results);

            comb.removeLast();
            counter.put(num, count);
        }
    }
}
