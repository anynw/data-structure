package com.anynw.sort;

import java.util.Arrays;

/**
 * 1.排序默认从小到大
 * 2.模拟10万数据测试排序大概耗时
 * 3.冒泡排序：15s
 * 4.选择排序：2s
 * 5.插入排序：4s
 * 6.希尔排序(交换法):10s
 * 7.希尔排序(移位法):23ms
 *
 * @author huaping
 * @time 2020-03-22 10:54:26 PM
 */
public class Sorts {

    public static int DEFAULT_SIZE = 100000;

    public static void main(String[] args) {
        int[] arr = {1, 2, 44, -2, 0, 5};
        //		System.out.println("原始数组：" + Arrays.toString(arr));
        //		bubbleSort(arr);
        //		System.out.println("冒泡排序：" + Arrays.toString(arr));
        //		selectSort(arr);
        //		System.out.println("选择排序：" + Arrays.toString(arr));
        //		insertSort(arr);
        //		System.out.println("插入排序：" + Arrays.toString(arr));
        //		shellSort(arr);
        //		System.out.println("希尔排序（交换法）：" + Arrays.toString(arr));
        shellSortPlus(arr);
        System.out.println("希尔排序（移位法）：" + Arrays.toString(arr));

        System.out.println("测试各种排序方式耗时：模拟十万数据");
        int[] array = new int[DEFAULT_SIZE];
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            array[i] = (int)(Math.random() * DEFAULT_SIZE);
        }
        long start = System.currentTimeMillis();
        //		bubbleSort(array);
        //		selectSort(array);
        //		insertSort(array);
        //		shellSort(array);
        shellSortPlus(array);
        long end = System.currentTimeMillis();
        System.out.printf("排序模拟十万数据耗时%d秒", (end - start) / 1000);
        System.out.println();
        System.out.println(start);
        System.out.println(end);

    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

    /**
     * 希尔排序：交换法
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
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
        }
    }

    /**
     * 希尔排序：移位法
     *
     * @param arr
     */
    public static void shellSortPlus(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];// 移位
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

}
