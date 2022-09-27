package com.anynw.leetcode;

/**
 * 请实现有重复数字的升序数组的二分查找
 * 给定一个 元素有序的（升序）长度为n的整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的第一个出现的target，如果目标值存在返回下标，否则返回 -1
 * 数据范围：0<=n<=100000
 * 进阶：时间复杂度 : O(logn)，空间复杂度 : O(n)
 *
 * @author wuhp
 * @date 2021/12/30
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 4, 5};
        int index = search(nums, 4);
        System.out.println("index = " + index);
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 边界判断
        if (nums.length == 0) {
            return -1;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {// 找到了，但是不确定是从左边找到的还是从右边的找到的
                while (mid > 0 && nums[mid] == nums[mid - 1]) {
                    mid--;
                }
                return mid;
            }
        }
        return -1;
    }
}
