package com.leetcode.array;

/**
 * level: medium
 * <p>
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 * <p>
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 * <p>
 * 0 <= j <= nums[i] and
 * <p>
 * i + j < n
 * <p>
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * <p>
 * Output: 2
 * <p>
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,3,0,1,4]
 * <p>
 * Output: 2
 */
public class Array0045_JumpGameⅡ {

    public static void main(String[] args) {
        Array0045_JumpGameⅡ algorithm = new Array0045_JumpGameⅡ();
        int[] nums = {2,3,1,1,4};
        System.out.println(algorithm.jump(nums));
    }

    /**
     * 比如一开始我们在0位置, nums[0]=2, 这时候我们下一次可以跳的最远的位置是Math.max(1 + nums[1], 2 + nums[2]),本题中最大的是1+nums[1]=4.<br/>
     * curEnd就是下一次可以跳到最远位置的起始索引, 也就是1<br/>
     * curFar就是下一次可以跳到最远位置的目标索引, 也就是4<br/>
     * i==curEnd,说明到达下一步的起始索引,所以步数加1,curEnd更新为下一次可到达的最远位置.<br/>
     * 贪心算法
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int ans = 0, n = nums.length - 1;
        int curEnd = 0, curFar = 0;
        for (int i = 0; i < n; i++) {
            curFar = Math.max(nums[i] + i, curFar);
            if (curFar >= n){ // 优化,到这里我发现最远的已经可以到最后了, 就不必继续遍历了
                return ++ans;
            }
            if (i == curEnd){
                ans++;
                curEnd = curFar;
            }
        }
        return ans;
    }
}
