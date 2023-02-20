package com.leetcode.array;

import java.util.*;

/**
 * level: medium
 * <p>
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * <p>
 * Output:
 * <p>
 * [
 * <p>
 * [1,1,6],
 * <p>
 * [1,2,5],
 * <p>
 * [1,7],
 * <p>
 * [2,6]
 * <p>
 * ]
 * <p>
 * Example 2:
 * <p>
 * Input: candidates = [2,5,2,1,2], target = 5
 * <p>
 * Output:
 * <p>
 * [
 * <p>
 * [1,2,2],
 * <p>
 * [5]
 * <p>
 * ]
 */
public class Array0040_CombinationSumⅡ {

    public static void main(String[] args) {
        int[] candidates = new int[]{1, 2, 2, 2, 5};
        int target = 5;
        Array0040_CombinationSumⅡ algorithm = new Array0040_CombinationSumⅡ();
        System.out.println(algorithm.combinationSum2(candidates, target));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        Map<Integer, Integer> counter = new HashMap<>();
        for (int candidate : candidates) {
            if (counter.containsKey(candidate)) {
                counter.put(candidate, counter.get(candidate) + 1);
            } else {
                counter.put(candidate, 1);
            }
        }

        List<int[]> counterList = new ArrayList<>();
        counter.forEach((k, v) -> counterList.add(new int[]{k, v}));

        backtracking(comb, target, 0, counterList, results);
        return results;
    }

    /**
     * 这里和39题的区别是1:入参可以有重复整数,2:出参同一个索引的值不能重复使用<br/>
     * @param comb 临时结果
     * @param remain
     * @param curr
     * @param counter
     * @param results
     */
    public void backtracking(LinkedList<Integer> comb, int remain, int curr, List<int[]> counter, List<List<Integer>> results) {
        if (remain < 0) return;
        if (remain == 0) results.add(new ArrayList<>(comb));

        for (int nextCurr = curr; nextCurr < counter.size(); nextCurr++) {
            int[] entry = counter.get(nextCurr);

            Integer candidate = entry[0], freq = entry[1];
            if (freq <= 0){
                continue;
            }

            // 当前结果加入临时结果
            comb.addLast(candidate);
            counter.set(nextCurr, new int[]{candidate, freq - 1});

            backtracking(comb, remain - candidate, nextCurr, counter, results);

            // 当前元素移出临时结果
            counter.set(nextCurr, new int[]{candidate, freq});
            comb.removeLast();
        }
    }
}
