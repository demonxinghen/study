package com.leetcode.array;

/**
 * level: hard
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * Example 1:
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * <p>
 * Output: 6
 * <p>
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * <p>
 * Output: 9
 * <p>
 * 大致就是数字代表不同长度的木板, 所有木板间总共能蓄水多少.
 */
public class Array0042_TrappingRainWater {

    public static void main(String[] args) {
        Array0042_TrappingRainWater algorithm = new Array0042_TrappingRainWater();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        algorithm.trap(height);
    }

    public int trap(int[] height) {
        int result = bruteForce(height);
        System.out.println(result);
        return result;
    }

    private int bruteForce(int[] height) {
        int ans = 0;
        int size = height.length;

        for (int i = 1; i < size - 1; i++) { // 因为要寻找左边和右边最大的木板, 所以遍历范围是第二个到倒数第二个
            int leftMax = 0;
            int rightMax = 0;
            for (int j = i; j >=0; j--) {
                leftMax = Math.max(leftMax, height[j]); // 找出左边最大的木板,包括自己
            }
            for (int j = i; j < size; j++) {
                rightMax = Math.max(rightMax, height[j]); // 找出右边最大的木板,包括自己
            }
            ans += Math.min(leftMax, rightMax) - height[i]; // 根据短板理论(木桶效应), 左右短的木板就是当前位置的最大蓄水量, 减去自己是因为自己可能也有长度, 长度内无法蓄水
        }
        return ans;
    }
}
