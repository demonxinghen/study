package com.leetcode.array;

/**
 * level: easy
 * <p>
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of the elements may be changed.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,2,3], val = 3
 * <p>
 * Output: 2, nums = [2,2,_,_]
 * <p>
 * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
 * <p>
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 */
public class Array0027_RemoveElement {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int val = 1;
        Array0027_RemoveElement algorithm = new Array0027_RemoveElement();
        System.out.println(algorithm.removeElement(nums, val));
        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

    public int removeElement(int[] nums, int val) {
        int lo = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val){
                nums[lo] = nums[i];
                lo++;
            }
        }
        return lo;
    }
}