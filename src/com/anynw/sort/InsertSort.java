package com.anynw.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 可以看做第一个数为一个有序数组，剩余的数组为无序数组
 * 将无序数组中的数依次遍历入有序数组；
 *
 * @author huaping
 * @time 2020-03-22 10:46:24 PM
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {908, 200, 60, 9};
        System.out.println("原始数组：" + Arrays.toString(arr));

        //insertSort(arr);
        insertSort(arr);

        int insertVal = arr[1];// 200
        int insertIndex = 1 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            // 后移一位，数组{ 908, 200, 60, 9 }->{ 908, 908, 60, 9 }
            arr[insertIndex + 1] = arr[insertIndex];
            // 此处为了退出循环
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;

        System.out.println("第一次选择排序:" + Arrays.toString(arr));

        insertVal = arr[2];// 60
        insertIndex = 2 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;

        System.out.println("第二次选择排序:" + Arrays.toString(arr));

        insertVal = arr[3];// 9
        insertIndex = 3 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;

        System.out.println("第三次选择排序:" + Arrays.toString(arr));

    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int insertVal = arr[i + 1];// 200
            int insertIndex = i + 1 - 1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // 后移一位，数组{ 908, 200, 60, 9 }->{ 908, 908, 60, 9 }
                arr[insertIndex + 1] = arr[insertIndex];
                // 此处为了退出循环
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;

            System.out.println("第" + (i + 1) + "次选择排序:" + Arrays.toString(arr));
        }
    }

    /**
     * 插入排序2
     *
     * @param arr
     */
    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];// 200
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // 后移一位，数组{ 908, 200, 60, 9 }->{ 908, 908, 60, 9 }
                arr[insertIndex + 1] = arr[insertIndex];
                // 此处为了退出循环
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;

            System.out.println("第" + i + "次选择排序:" + Arrays.toString(arr));
        }
    }

}
