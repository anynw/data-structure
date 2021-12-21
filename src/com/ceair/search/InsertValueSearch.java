package com.ceair.search;

import java.util.Arrays;

/**
 * 插值查找算法，类似与二分查找，是二分查找的升级版
 * 1.对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找能提高效率
 * 2.关键字分布不均匀的情况下， 数据跳跃性大，性能并不一定比二分查找好
 * @author wuhp
 * @date 2021/12/21
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
        int i = insertValueSearch(arr, 0, arr.length - 1, 89);
        System.out.println(i);

    }

    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        // int mid = left + (right - left) / 2;
        // 优化二分查找的参数 1/2 => (value-arr[left])/(arr[right]-arr[left])
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (value > midValue) {
            return insertValueSearch(arr, mid + 1, right, value);
        } else if (value < midValue) {
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
