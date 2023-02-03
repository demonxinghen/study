package com.leetcode.array;

/**
 * level: hard
 * <p>
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * <p>
 * Output: 2.00000
 * <p>
 * Explanation: merged array = [1,2,3] and median is 2.
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * <p>
 * Output: 2.50000
 * <p>
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */
public class Array0004_MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 6};
        int[] nums2 = {2, 4};
        Array0004_MedianOfTwoSortedArrays algorithm = new Array0004_MedianOfTwoSortedArrays();
        double result = algorithm.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] result = mergeTwoArray(nums1, nums2);
        return calcMedian(result);
    }

    /**
     * 合并两个数组到一个数组,然后取中间值
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] mergeTwoArray(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int h = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                result[h++] = nums1[i++];
            } else {
                result[h++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            result[h++] = nums1[i++];
        }
        while (j < nums2.length) {
            result[h++] = nums2[j++];
        }
        return result;
    }

    public double calcMedian(int[] nums) {
        return nums.length % 2 == 0 ? (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2.0 : nums[nums.length / 2] / 1.0;
    }
}
