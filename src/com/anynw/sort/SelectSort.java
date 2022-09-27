package com.anynw.sort;

import java.util.Arrays;

/**
 * 交换排序:时间复杂度O(n²)
 *
 * @author huaping
 * @time 2020-03-22 7:43:16 PM
 */
public class SelectSort {

    public static void main(String[] args) {
        //		int[] arr = { 121, 302, 3, 42 };
        int[] arr = {121, 302, 3, 42, -5, 334, 3432, -665, 19};
        System.out.println("原始数组：" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("交换排序后数组：" + Arrays.toString(arr));

        // 第一次交换排序【3，302，121，42】 121 和 3 进行了交换
        int min = 0;
        int minIndex = 0;
        min = arr[0];
        for (int i = 0 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        // 交换
        arr[minIndex] = arr[0];
        // 最小值放前面
        arr[0] = min;
        System.out.println("第一次交换排序：" + Arrays.toString(arr));// 3，302，121，42

        // 第二次交换排序
        minIndex = 1;
        min = arr[1];
        for (int i = 1 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        if (minIndex != 1) {
            // 交换
            arr[minIndex] = arr[1];
            // 最小值放前面
            arr[1] = min;
        }
        System.out.println("第二次交换排序：" + Arrays.toString(arr));// 3，42，121，302

        // 第三次交换排序
        minIndex = 2;
        min = arr[2];
        for (int i = 2 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        if (minIndex != 2) {
            // 交换
            arr[minIndex] = arr[2];
            // 最小值放前面
            arr[2] = min;
        }
        System.out.println("第三次交换排序：" + Arrays.toString(arr));// 3，42，121，302

    }

    /**
     * 依次比较最小值，如果有比当前值还小，获取该值的下标，并重新定义最小值，最后进行交换
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int j = 0; j < arr.length - 1; j++) {
            int min = j;
            int minIndex = j;
            min = arr[j];
            for (int i = j + 1; i < arr.length; i++) {
                if (min < arr[i]) { // 此处符号可以决定排序方式：从大到小排序或者从小到大排序
                    min = arr[i];
                    minIndex = i;
                }
            }
            if (minIndex != j) {
                // 交换
                arr[minIndex] = arr[j];
                // 最小值放前面
                arr[j] = min;
            }
        }
    }

}
