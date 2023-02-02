package com.leetcode.array;

/**
 * level: medium
 *
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 * Example 1:
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 * 简单理解,就是n块不同高度的木板竖立在那,找出面积最大的两块木板(只能是长方形或正方形)
 */
public class Array0011_ContainerWithMostWater {

    public static void main(String[] args) {
        int[] nums = {1,8,6,2,5,4,8,3,7};
        Array0011_ContainerWithMostWater algorithm = new Array0011_ContainerWithMostWater();
        int result = algorithm.maxArea(nums);
        System.out.println(result);
    }

    public int maxArea(int[] height) {
        return twoPointer(height);
    }

    /**
     * 暴力破解, 时间复杂度过高, leetcode提交失败
     * @param height
     * @return
     */
    public int bruteForce(int[] height){
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int minHeight = Math.min(height[i], height[j]);
                int width = j - i;
                max = Math.max(max, minHeight * width);
            }
        }
        return max;
    }

    /**
     * 双指针, 一开始指针指向首尾, 然后哪个数值小, 哪个指针移动
     * @param height
     * @return
     */
    public int twoPointer(int[] height){
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        while (start < end){
            if (height[start] < height[end]){
                max = Math.max(max, (end - start) * height[start++]);
            }else {
                max = Math.max(max, (end - start) * height[end--]);
            }
        }
        return max;
    }
}
