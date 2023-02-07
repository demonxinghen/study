package com.leetcode.array;

/**
 * level: medium
 * <p>
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * <p>
 * Output: [3,4]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * <p>
 * Output: [-1,-1]
 */
public class Array0034_FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        Array0034_FindFirstAndLastPositionOfElementInSortedArray algorithm = new Array0034_FindFirstAndLastPositionOfElementInSortedArray();
        int[] range = algorithm.searchRange(nums, target);
        for (int num : range) {
            System.out.print(num + ",");
        }
    }

    public int[] searchRange(int[] nums, int target) {
        return searchRangeBinary(nums, 0, nums.length - 1, target);
    }

    /**
     * 二分法
     * @param nums
     * @param i
     * @param j
     * @param target
     * @return
     */
    private int[] searchRangeBinary(int[] nums, int i, int j, int target) {
        if (i <= j && j < nums.length){
            int mid = i + (j - i) / 2;
            if (nums[mid] < target){
                return searchRangeBinary(nums, mid + 1, nums.length - 1, target);
            } else if (nums[mid] > target) {
                return searchRangeBinary(nums, i, mid - 1, target);
            }
            int start = mid;
            // 这里的代码是否会导致时间复杂度变为O(n)
            while (start < j && start >= 1 && nums[start - 1] == target){
                start--;
            }
            int end = mid;
            while (end < j && nums[end + 1] == target){
                end++;
            }
            return new int[]{start, end};
        }
        return new int[]{-1, -1};
    }
}
