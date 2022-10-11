package com.anynw.algorithms4th.sort;

import com.anynw.algorithms4th.utils.SortUtil;

import java.util.Arrays;

/**
 * 插入排序：类似打扑克牌，从右手拿牌，插入到左手的合适位置，即有序。
 *
 * @author wuhp
 * @date 2022/10/11
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {8, 6, 3, 7, 2, 5, 4, 1, 0};
        insertSort(arr);
        System.out.println("插入排序 = " + Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            // 将a[i] 插入到 a[i-1]、a[i-2]、a[i-3]。。。中
            for (int j = i; j > 0 && SortUtil.less(arr[j], arr[j - 1]); j--) {
                SortUtil.exch(arr, j, j - 1);
            }
        }
    }
}
