package com.anynw.algorithms4th.sort;

import com.anynw.algorithms4th.utils.SortUtil;

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
        // mergeSort(arr, 0, arr.length - 1);
        mergeSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 自顶向下的排序
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) >>> 1;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        // merge(arr, low, mid, high);
        merge2(arr, low, mid, high);
    }

    /**
     * 自底向上的排序
     *
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        int N = arr.length;
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int low = 0; low < N - sz; low += sz + sz) {
                merge2(arr, low, low + sz - 1, Math.min(N - 1, low + sz + sz - 1));
            }
        }
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

    /**
     * 1.复制一份元素到临时数组中
     * 2.临时数组归并回原数组中
     *
     * @param arr
     * @param low
     * @param mid
     * @param highmerge(arr,low,mid,high);
     */
    public static void merge2(int[] arr, int low, int mid, int high) {
        int N = arr.length;
        int[] temp = new int[N];
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            temp[k] = arr[k];
        }
        /**
         * 原地归并抽象方法：
         * 左半边用尽，取右半边元素
         * 右半边用尽，取左半边元素
         * 左半边元素比右半边元素大，取右半边元素
         * 左半边元素比右半边元素小，取左半边元素
         */
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                arr[k] = temp[j++];
            } else if (j > high) {
                arr[k] = temp[i++];
            } else if (SortUtil.less(temp[j], temp[i])) {
                arr[k] = temp[j++];
            } else {
                arr[k] = temp[i++];
            }
        }
    }
}
