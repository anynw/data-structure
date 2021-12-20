package com.ceair.sort;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 1.桶：bucket ； 基数：radix
 * 2.基数排序（桶排序）时间复杂度：O（n*k）n为最大数的位数，k为桶的数量，默认为10。
 * 3.优点：相比于其他排序，n值越大效率越高。
 * 4.缺点：排序数据过多的时候，极其消耗内存，易导致堆内存溢出，它是一种空间换取时间的排序算法。
 * @author wuhp
 * @date 2021/12/20
 */
public class RadixSort {

    // 一亿
    public static int DEFAULT_SIZE = 100000000;
    // 100000000 * 11 * 4 /1024 /1024 /1024 = 4.097G
    // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    // 内存不足，添加虚拟机参数，查看堆的内存 -XX:+PrintGCDetails

    public static void main(String[] args) {
        // int[] arr = {222,13,6,37,908,48};
        // System.out.println("排序前：" + Arrays.toString(arr));
        // radixSort(arr);
        // System.out.println("排序后：" + Arrays.toString(arr));


        int[] array = new int[DEFAULT_SIZE];
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            array[i] = (int) (Math.random() * DEFAULT_SIZE);
        }
        String start = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd mm:hh:ss"));
        System.out.println(start);
        radixSort(array);
        String end = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd mm:hh:ss"));
        System.out.println(end);


    }

    public static void radixSort(int[] arr) {
        // 定义最大值
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 计算最大值的位数，个位，十位，百位... 决定循环遍历的次数
        int maxLength = (max + "").length();

        for (int j = 0, n = 1; j < maxLength; j++, n *= 10) {
            // 0-9号，一共10个桶
            int[][] bucket = new int[10][arr.length];
            // 定义一维数组，桶。计算存放的元素个数,按照最大值，最悲观的情况考虑，所有的元素都放入了一个桶中
            // 空间换时间
            int[] bucketElement = new int[10];

            for (int k = 0; k < arr.length; k++) {
                // 位数
                int bucketIndex = arr[k] / n % 10;
                // 定义桶对应的元素个数
                bucket[bucketIndex][bucketElement[bucketIndex]] = arr[k];
                // 每次放入元素个数+1
                bucketElement[bucketIndex]++;
            }
            // 按照桶的顺序放入元素
            int index = 0;
            for (int m = 0; m < bucketElement.length; m++) {
                if (bucketElement[m] != 0) {
                    for (int mn = 0; mn < bucketElement[m]; mn++) {
                        arr[index++] = bucket[m][mn];
                    }
                    // 清空桶的数据
                    bucketElement[m] = 0;
                }

            }

        }


    }
}
