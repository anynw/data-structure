package com.anynw.algorithms4th.sort;

import com.anynw.algorithms4th.utils.SortUtil;

import java.util.Arrays;

/**
 * 希尔排序：
 *
 * @author wuhp
 * @date 2022/10/11
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 6, 3, 7, 2, 5, 4, 1, 0};
        shellSort(arr);
        System.out.println("希尔排序 = " + Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        int N = arr.length;
        int h = 1;
        if (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                // 将a[i] 插入到 a[i-h]、a[i-2*h]、a[i-3*h]...中
                for (int j = i; j >= h && SortUtil.less(arr[j], arr[j - h]); j -= h) {
                    SortUtil.exch(arr, j, j - h);
                }
            }
            h = h / 3;
        }

    }
}
