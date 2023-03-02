package com.leetcode.array;

import java.util.*;

/**
 * level: medium
 * <p>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * <p>
 * Output: [[-1,-1,2],[-1,0,1]]
 * <p>
 * Explanation:
 * <p>
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * <p>
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * <p>
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * <p>
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * <p>
 * Notice that the order of the output and the order of the triplets does not matter.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,1,1]
 * <p>
 * Output: []
 * <p>
 * Explanation: The only possible triplet does not sum up to 0.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [0,0,0]
 * <p>
 * Output: [[0,0,0]]
 * <p>
 * Explanation: The only possible triplet sums up to 0.
 */
public class Array0015_3Sum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Array0015_3Sum algorithm = new Array0015_3Sum();
        List<List<Integer>> result = algorithm.threeSum(nums);
        System.out.println(result);

        List<List<Integer>> result2 = algorithm.threeSumWithSet(nums);
        System.out.println(result2);
    }

    /**
     * 双指针的基础上, 利用set天然去重的特性
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumWithSet(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);

        int sum = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            // 只有第一个数<=0, 才有机会三者之和==0
            if (nums[i] <= 0) {
                // 双指针
                int lo = i + 1;
                int hi = nums.length - 1;
                while (lo < hi) {
                    sum = nums[i] + nums[lo] + nums[hi];
                    if (sum == 0) {
                        result.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                    } else if (sum < 0) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * 利用双指针
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 只有第一个数<=0, 才有机会三者之和==0
            // 如果nums[i] == nums[i - 1], 说明已经解过一次了
            // i为0的时候, i-1会越界,所以单独提出i==0
            if (nums[i] <= 0 && (i == 0 || nums[i] != nums[i - 1])) {
                // 双指针
                int lo = i + 1;
                int hi = nums.length - 1;
                int sum = -nums[i];
                while (lo < hi) {
                    // 就变成两数之和的问题了, 只不过这个数组是有序的
                    if (nums[lo] + nums[hi] == sum) {
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        // nums[lo] == nums[lo + 1]说明有相同解, 直接跳过, 避免重复
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        // 同理
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        // 小于的话, 说明lo需要后移, 用更大的数来计算
                        lo++;
                    } else {
                        // 小于的话, 说明hi需要前移, 用更小的数来计算
                        hi--;
                    }
                }
            }
        }
        return result;
    }

}
