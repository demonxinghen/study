package com.leetcode.array;

import java.util.Stack;

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
        System.out.println(dynamicProgramming(height));
        System.out.println(stackStore(height));
        return result;
    }

    /**
     * 栈的使用, 没看懂
     * @param height
     * @return
     */
    private int stackStore(int[] height) {
        int ans = 0, current = 0;
        Stack<Integer> stack = new Stack<>();

        while (current < height.length){
            while (!stack.empty() && height[current] > height[stack.peek()]){
                int top = stack.pop();
                if (stack.empty()){
                    break;
                }
                int distance = current - stack.peek() - 1;
                int boundedHeight = Math.min(height[current], height[stack.peek()] - height[top]);

                ans += distance * boundedHeight;
            }
            stack.push(current++);
        }
        return ans;
    }

    /**
     * 动态规划, 和暴力破解的区别在于寻找最大木板之后存在了数组中, 减少了不必要的循环
     * @param height
     * @return
     */
    private int dynamicProgramming(int[] height) {
        if (height.length == 0) return 0;
        int ans = 0;
        int size = height.length;

        int[] leftMax = new int[size], rightMax = new int[size];

        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        rightMax[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
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
