package com.leetcode.array;

/**
 * level: medium
 * <p>
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * <p>
 * Output: true
 * <p>
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,0,4]
 * <p>
 * Output: false
 * <p>
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 */
public class Array0055_JumpGame {

    private boolean flag = false;

    public static void main(String[] args) {
        Array0055_JumpGame algorithm = new Array0055_JumpGame();
        int[] nums = {1, 2, 3};
        System.out.println(algorithm.canJump(nums));
    }

    /**
     * 记录每一步可以走到的最大位置, 只要无论如何走不到某个位置, 就失败
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }
}
