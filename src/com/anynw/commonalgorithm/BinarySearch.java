package com.anynw.commonalgorithm;

/**
 * 二分查找
 *
 * @author wuhp
 * @date 2021/12/29
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("递归查找");
        int index = recursiveSearch(arr, 0, arr.length - 1, -13);
        System.out.println(index);
        System.out.println("非递归查找");
        int i = binarySearch(arr, 9);
        System.out.println(i);

    }

    /**
     * 递归查找
     *
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static int recursiveSearch(int[] arr, int left, int right, int value) {
        if (left > right || arr[left] > value || arr[right] < value) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        int midVal = arr[mid];
        if (value < midVal) {
            return recursiveSearch(arr, left, mid - 1, value);
        } else if (value > midVal) {
            return recursiveSearch(arr, mid + 1, right, value);
        } else {
            return mid;
        }
    }

    /**
     * 非递归查找
     *
     * @param arr
     * @param value
     * @return
     */
    public static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = arr[mid];
            if (value < midVal) {
                right = mid - 1;
            } else if (value > midVal) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
