package com.anynw.sort;

import java.util.Arrays;

/**
 * 详细说明冒泡排序，时间复杂度O(n²)
 *
 * @author huaping
 * @time 2020-03-22 5:55:49 PM
 */
public class BubbleSort {

    public static void main(String[] args) {
        // 定义一个数组，模拟冒泡排序，从小到大
        // int[] arr0 = { 2, -3, 5, 9, -4 };
        int[] arr0 = {2, -3, 4, 5, 6};
        int temp0 = 0;
        boolean flag = false;
        for (int j = 0; j < arr0.length - 1; j++) {
            for (int i = 0; i < arr0.length - 1 - j; i++) {
                // 前面的数大于后边的数，进行交换
                if (arr0[i] > arr0[i + 1]) {
                    temp0 = arr0[i];
                    arr0[i] = arr0[i + 1];
                    arr0[i + 1] = temp0;
                    flag = true;
                }
            }
            System.out.println("冒泡排序ARR0第" + (j + 1) + "次排序得到的数组:" + Arrays.toString(arr0));
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }

        int[] arr = {2, -3, 5, 9, -4};
        // 比较次数为 arr的大小 -1
        // 第一次排序，将最大的值移到最右边
        for (int i = 0; i < arr.length - 1 - 0; i++) {
            int temp = 0;
            // 前面的数大于后边的数，进行交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第一次交换过后的数组：" + Arrays.toString(arr));
        // 第二次排序，将第二个最大值放到最大值的左边
        for (int i = 0; i < arr.length - 1 - 1; i++) {
            int temp = 0;
            // 前面的数大于后边的数，进行交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第二次交换过后的数组：" + Arrays.toString(arr));
        // 第三次排序，依次按照第一次和第二次的规则移动最大值
        for (int i = 0; i < arr.length - 1 - 2; i++) {
            int temp = 0;
            // 前面的数大于后边的数，进行交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第三次交换过后的数组：" + Arrays.toString(arr));
        // 第四次排序
        for (int i = 0; i < arr.length - 1 - 3; i++) {
            int temp = 0;
            // 前面的数大于后边的数，进行交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第四次交换过后的数组：" + Arrays.toString(arr));

    }
}
