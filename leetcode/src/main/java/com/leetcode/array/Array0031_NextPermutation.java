package com.leetcode.array;


/**
 * level:medium
 * <p>
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * <p>
 * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * <p>
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * <p>
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * <p>
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * <p>
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * <p>
 * Output: [1,3,2]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,2,1]
 * <p>
 * Output: [1,2,3]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,1,5]
 * <p>
 * Output: [1,5,1]
 */
public class Array0031_NextPermutation {

    public static void main(String[] args) {
        int[] nums = {1,2,5,3,4};
        Array0031_NextPermutation algorithm = new Array0031_NextPermutation();
        algorithm.nextPermutation(nums);
        for (int num : nums) {
            System.out.printf("%d ", num);
        }
    }

    // 1 2 5 4 3
    public void nextPermutation(int[] nums) {
        if (nums.length == 1){
            return;
        }
        if (nums.length == 2){
            swap(nums, 0, 1);
            return;
        }
        // 从右到左, 寻找nums[i + 1] <= nums[i], 找到i的位置, 比如1 2 5 4 3, 找到的就是索引为1的位置
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]){
            i--;
        }
        // 小于0说明整个数组降序,直接反转即可
        if (i >= 0){
            int j = nums.length - 1;
            while (nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
