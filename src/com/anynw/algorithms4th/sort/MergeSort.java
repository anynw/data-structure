package com.anynw.algorithms4th.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author wuhp
 * @date 2022/10/11
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 6, 3, 7, 2, 5, 4, 1, 0};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) >>> 1;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    public static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= high) {
            temp[t++] = arr[j++];
        }
        // 方式一
        // for (int k = 0; k < temp.length; k++) {
        //     arr[k + low] = temp[k];
        // }

        // 方式二
        t = 0;
        while (low <= high) {
            arr[low++] = temp[t++];
        }

    }
}
