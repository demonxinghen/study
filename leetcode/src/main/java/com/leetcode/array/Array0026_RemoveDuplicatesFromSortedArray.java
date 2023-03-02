package com.leetcode.array;

import java.util.*;

/**
 * level: easy
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * <p>
 * Output: 2, nums = [1,2,_]
 * <p>
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 * <p>
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 */
public class Array0026_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        Array0026_RemoveDuplicatesFromSortedArray algorithm = new Array0026_RemoveDuplicatesFromSortedArray();
        System.out.println(algorithm.removeDuplicates(nums));
        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

    public int removeDuplicates(int[] nums) {
        // k指向需要填充的数值, 因为第一个肯定会填入, 所以直接从第二个元素开始
        int k = 1;
        for (int i = 1; i < nums.length; i++) {
            // 如果第i个元素小于k-1的元素, 说明此元素已经被添加过
            if (nums[i] <= nums[k - 1]) {
                continue;
            }
            int temp = nums[k];
            nums[k] = nums[i];
            nums[i] = temp;
            k++;
        }
        return k;
    }
}