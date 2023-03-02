package com.leetcode.array;

import java.util.*;

/**
 * level: medium
 * <p>
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * 0 <= a, b, c, d < n
 * <p>
 * a, b, c, and d are distinct.
 * <p>
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * <p>
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * <p>
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,2,2], target = 8
 * <p>
 * Output: [[2,2,2,2]]
 */
public class Array0018_4Sum {

    public static void main(String[] args) {
        int[] nums = {-5,5,4,-3,0,0,4,-2};
        int target = 4;
        Array0018_4Sum algorithm = new Array0018_4Sum();
        algorithm.fourSum(nums, target);
    }

    /**
     * @param nums
     * @return
     */
    public void fourSum(int[] nums, int target) {
        System.out.println(bruteForce(nums, target));
        System.out.println(twoPointers(nums, target));
        System.out.println(officialSolution(nums, target));
    }

    /**
     * 官方解法, 可以解决任意k个数字相加的问题
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> officialSolution(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    /**
     * 双指针, 要排序
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> twoPointers(int[] nums, int target) {
        Arrays.sort(nums);
        if (nums[0] > 0 && target < 0){
            return new ArrayList<>();
        }
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int lo = j + 1;
                int hi = nums.length - 1;
                while (lo < hi) {
                    // 全部转long, 解决因超过Integer最大范围导致的bug
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[lo] + (long)nums[hi];
                    if (sum == (long)target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]){
                            lo++;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]){
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (sum < target) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * 暴力破解
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> bruteForce(int[] nums, int target) {
        // 暴力破解也要排序, 不然可能出现-5,5,0,4和-5,5,4,0, 即使Set也不会认为这是重复的
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if ((long)nums[i] + (long)nums[j] + (long)nums[k] + (long)nums[l] == (long)target) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        }
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    /**
     *
     * @param nums
     * @param target
     * @param start
     * @param k
     * @return
     */
    public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();

        // If we have run out of numbers to add, return res.
        if (start == nums.length) {
            return res;
        }

        // There are k remaining values to add to the sum. The
        // average of these values is at least target / k.
        long average_value = target / k;

        // We cannot obtain a sum of target if the smallest value
        // in nums is greater than target / k or if the largest
        // value in nums is smaller than target / k.
        if  (nums[start] > average_value || average_value > nums[nums.length - 1]) {
            return res;
        }

        if (k == 2) {
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                // kSum(nums, target - nums[i], i + 1, k - 1) 递归化简成twoSum问题
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(subset);
                }
            }
        }

        return res;
    }
    public List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;

        while (lo < hi) {
            int currSum = nums[lo] + nums[hi];
            if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
                ++lo;
            } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
            }
        }

        return res;
    }
}