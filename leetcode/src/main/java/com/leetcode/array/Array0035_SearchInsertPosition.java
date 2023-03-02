package com.leetcode.array;

/**
 * level: easy
 * <p>
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,5,6], target = 5
 * <p>
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,3,5,6], target = 2
 * <p>
 * Output: 1
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,3,5,6], target = 7
 * <p>
 * Output: 4
 */
public class Array0035_SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = {1, 6, 7};
        int target = 8;
        Array0035_SearchInsertPosition algorithm = new Array0035_SearchInsertPosition();
        System.out.println(algorithm.searchInsert(nums, target));
    }

    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
