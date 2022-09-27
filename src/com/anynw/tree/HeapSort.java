package com.anynw.tree;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 堆排序
 * 升序：大顶堆；降序：小顶堆。
 * 复杂度：O(nlogn) 线性对数阶
 * 测试：1亿个元素大小的数组，M1 mac pro 排序不到一秒，稳定在87ms左右
 * 2021-12-25 33:08:40:929
 * 2021-12-25 33:08:41:016
 * <p>
 * 2021-12-25 36:08:27:193
 * 2021-12-25 36:08:27:282
 * <p>
 * 2021-12-25 37:08:05:358
 * 2021-12-25 37:08:05:446
 *
 * @author wuhp
 * @date 2021/12/25
 */
public class HeapSort {

    private static int maxSize = 100000000;

    public static void main(String[] args) {
        // int[] arr = {4, 6, 8, 5, 9};
        // int[] arr = {4, 6, 8, 5, 9,-1,3434,54,33,63,463,232444,98};
        // adjustHeap(arr, 1, arr.length);// [4, 9, 8, 5, 6]
        // adjustHeap(arr, 0, arr.length);// [9, 6, 8, 5, 4]
        // heapSort(arr);
        // System.out.println(Arrays.toString(arr));

        // 测试性能
        int[] arr2 = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            arr2[i] = (int)Math.random() * maxSize;
        }
        String dateTimePattern = "yyyy-MM-dd mm:hh:ss:SSS";
        String start = LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateTimePattern));
        System.out.println(start);
        heapSort(arr2);
        String end = LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateTimePattern));
        System.out.println(end);

    }

    /**
     * 堆排序静态算法
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        //1.将无序数组构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //2.将堆顶元素与末尾元素交换，将最大元素放在数组的末端
        //3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行，直到序列有序
        int temp = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 将数组（二叉树）调整成一个大顶堆
     *
     * @param arr    待调整的数组
     * @param i      非叶子节点再数组中的索引下标
     * @param length 表示对多少个元素进行调整，length 的大小逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 取出当前元素的值，保存在临时变量
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 说明左子节点的值小于右子节点的值
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            // 如果子节点大于父节点
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k; // 将i指向k，继续循环比较
            } else {
                break;
            }
        }
        // 循环结束,已i为父节点的最大值，方法最顶（局部调整）
        arr[i] = temp;
    }

}
