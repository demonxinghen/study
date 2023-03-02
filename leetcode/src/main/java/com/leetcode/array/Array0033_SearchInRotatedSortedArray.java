package com.leetcode.array;

import java.util.Arrays;

/**
 * level: medium
 * <p>
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * <p>
 * Output: 4
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * <p>
 * Output: -1
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1], target = 0
 * <p>
 * Output: -1
 */
public class Array0033_SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        // 此时要交换应该是5和1,3和2, 交换了之后是5 3 1 2 4,还差个4
        Array0033_SearchInRotatedSortedArray algorithm = new Array0033_SearchInRotatedSortedArray();
        System.out.println(algorithm.search(nums, target));
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(algorithm.findIndex2(nums2, target));

        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

    /**
     * 复杂度O(n), 不符合题目, 但是完成了数组搬运</br>
     * 本题是要用O(log n)找出那个索引
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == target) {
                break;
            }
            i++;
        }
        if (i < nums.length) {
            int[] newArray = Arrays.copyOf(nums, i);
            for (int j = i; j < nums.length; j++) {
                nums[j - i] = nums[j];
            }
            for (int j = 0; j < newArray.length; j++) {
                nums[nums.length - i + j] = newArray[j];
            }
            return i;
        }
        return -1;
    }

    /**
     * 使用二分法
     *
     * @param nums
     * @param target
     * @return
     */
    public int findIndex(int[] nums, int target) {
        return findIndexByRange(nums, 0, nums.length - 1, target);
    }

    /**
     * 这个方法是0(log n),但是只针对完全升序的数组有效,而题目中不是完全升序,比如4,5,6,7,0,1,2
     *
     * @param nums
     * @param lo
     * @param hi
     * @param target
     * @return
     */
    private int findIndexByRange(int[] nums, int lo, int hi, int target) {
        while (lo < hi) {
            int mid = (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                return findIndexByRange(nums, mid + 1, hi, target);
            }
            return findIndexByRange(nums, lo, mid - 1, target);
        }
        return -1;
    }
    public int findIndex2(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            // 如果最小值小于中间值
            if (nums[low] <= nums[mid]) {
                // 最小值小于目标值 且 中间值大于目标值, 说明目标值在最小值和中间值中间
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }
        return -1;
    }
}
