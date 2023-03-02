package com.leetcode.array;


/**
 * level: medium
 * <p>
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * <p>
 * Return the single element that appears only once.
 * <p>
 * Your solution must run in O(log n) time and O(1) space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * <p>
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,3,7,7,10,11,11]
 * <p>
 * Output: 10
 */
public class Array0540_SingleElementInASortedArray {

    public static void main(String[] args) {
        Array0540_SingleElementInASortedArray algorithm = new Array0540_SingleElementInASortedArray();
        int[] nums = {3,3,7,7,10,11,11};
        System.out.println(algorithm.singleNonDuplicate(nums));
    }

    public int singleNonDuplicate(int[] nums) {
        System.out.println(twoPointers(nums));
        System.out.println(binarySearch(nums));
        return singleNoneDuplicate(nums);
    }

    /**
     * @param nums
     * @return
     */
    private int binarySearch(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = (right - left) / 2 + left;
            // 因为一对数值, 在没有目标值的情况下, 第一个索引是偶数, 第二个是奇数
            //    中间索引是偶数,且mid+1和mid值一样, 说明左边没有目标值, 所以left = mid + 1
            // 同理中间索引是奇数,且mid-1和mid值一样, 说明左边没有目标值, 所以right = mid
            if (mid % 2 == 0 && nums[mid] == nums[mid + 1] || mid % 2 == 1 && nums[mid] == nums[mid - 1]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }

    /**
     * 因为题目说了每个数字都会出现两次, 除了目标值, 所以不同的必然是连续索引中的第一个, 或者走到最后一个了, 那必然是它
     * @param nums
     */
    private int twoPointers(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int start = 0;
        while (start < nums.length){
            if (start == nums.length - 1 || nums[start] != nums[start + 1]){
                return nums[start];
            }
            start = start + 2;
        }
        return -1;
    }

    /**
     * 异或运算, 复杂付O(n)
     * @param nums
     * @return
     */
    private int singleNoneDuplicate(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
