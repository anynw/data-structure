package com.anynw.algorithms4th.sort;

import com.anynw.algorithms4th.utils.SortUtil;

import java.util.Arrays;

/**
 * 选择排序：与前一个元素对比，交换
 *
 * @author wuhp
 * @date 2022/10/11
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {8, 6, 3, 7, 2, 5, 4, 1, 0};
        selectSort(arr);
        System.out.println("选择排序 = " + Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            // 定义最小元素的索引
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (SortUtil.less(arr[j], arr[min])) {
                    // j 是最小索引，赋值给 min
                    min = j;
                }
                SortUtil.exch(arr, i, min);
            }
        }
    }

}
