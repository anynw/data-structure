package com.ceair.search;

import java.util.Arrays;

/**
 * 斐波那契查好算法（黄金分割法算法）：有序数列
 * @author wuhp
 * @date 2021/12/21
 */
public class FibonacciSearch {

    private static final int maxSize = 20;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(fib()));
        int[] arr = {1, 2, 34, 44, 89, 333};
        System.out.println("index = " + fibonacciSearch(arr, -333));
    }

    // 生成一个斐波那契数组
    public static int[] fib() {
        int[] arr = new int[maxSize];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    public static int fibonacciSearch(int[] arr, int key) {
        // mid = low + f[k-1] -1
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        // 斐波那契数组的下标
        int k = 0;
        int[] f = fib();
        while (high > f[k] - 1) {
            k++;
        }

        int[] temp = Arrays.copyOf(arr, f[k]);


        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid < high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
