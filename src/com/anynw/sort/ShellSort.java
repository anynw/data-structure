package com.anynw.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author huaping
 * @time 2020-03-22 11:55:23 PM
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        //		shellSort(arr);
        shellSortPlus(arr);
        System.out.println("希尔排序后：" + Arrays.toString(arr));

        // 第一次希尔排序：10个数据分成10/2=5组
        int temp = 0;
        for (int i = 5; i < arr.length; i++) {
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第一次：" + Arrays.toString(arr));// [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]

        // 第二次希尔排序:10个数据分为5/2=2组
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序第二次：" + Arrays.toString(arr));// [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]

        // 第三次希尔排序:10个数据分为2/2=1组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第三次：" + Arrays.toString(arr));// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

    }

    /**
     * 希尔排序算法:交换法，性能比较低
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "次：" + Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序算法：移位法
     *
     * @param arr
     */
    public static void shellSortPlus(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    // 需要进行移动
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

}
