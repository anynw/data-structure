package com.ceair.search;

/**
 * 线性查找：给定一个值，再数组中查找，如果能找到，则返回下标，找不到，返回-1
 *
 * @author wuhp
 * @date 2021/12/20
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1, 4, 22, -43, 67};
        System.out.println(seqSearch(arr, 11));
    }

    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
