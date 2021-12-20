package com.ceair.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.二分查找,要求首先数组必须有序
 * 2.假设范围数组长度为N，则二分查找的时间复杂度为O（logN）
 * 3.优点是比较次数少，查找速度快，平均性能好
 * 4.其缺点是要求待查表为有序表，且插入删除困难
 *
 * @author wuhp
 * @date 2021/12/20
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 22, 43, 43, 43, 67};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 671));
        System.out.println(binarySearch2(arr, 0, arr.length - 1, 67));
    }

    /**
     * 返回查找值的下标
     *
     * @param arr   有序数组
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int value) {
        if (left > right || value < arr[left] || value > arr[right]) {
            return -1;
        }
        // 存在溢出风险
        // int mid = (left + right) / 2;
        int mid = left + (right - left) / 2;
        int midVal = arr[mid];
        if (value > midVal) {
            return binarySearch(arr, mid + 1, right, value);
        } else if (value < midVal) {
            return binarySearch(arr, left, mid - 1, value);
        } else { // value == midVal
            return mid;
        }
    }

    /**
     * 找出相同数的下标集合
     *
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        List<Integer> resIndexList = new ArrayList<Integer>();
        if (left > right || value < arr[left] || value > arr[right]) {
            return resIndexList;
        }
        // int mid = (left + right) / 2;
        int mid = left + (right - left) / 2;
        int midVal = arr[mid];
        if (value > midVal) {
            return binarySearch2(arr, mid + 1, right, value);
        } else if (value < midVal) {
            return binarySearch2(arr, left, mid - 1, value);
        } else { // value == midVal
            // 找到元素之后，想左遍历，向右遍历，分别找出值为midVal的下标，加入到ArrayList中
            // 左边遍历
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != value) {
                    break;
                }
                resIndexList.add(temp);
                // temp 左移
                temp -= 1;
            }
            // 将匹配到的第一个数的下标加入到集合
            resIndexList.add(mid);
            // 右边遍历
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
        }
        return resIndexList;
    }
}
