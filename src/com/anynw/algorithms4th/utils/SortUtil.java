package com.anynw.algorithms4th.utils;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author wuhp
 * @date 2022/10/10
 */
public class SortUtil {
    public static void sort(int[] a) {
        // 各种排序实现
    }

    public static boolean less(int v, int w) {
        return v < w;
    }

    public static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(int[] a) {
        // 单行打印数组
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

}
