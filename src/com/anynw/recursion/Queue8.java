package com.anynw.recursion;

/**
 * 八皇后问题
 *
 * @author huaping
 * @time 2020-03-21 8:06:47 PM
 */
public class Queue8 {
    // 使用一维数组模拟八皇后问题，定义皇后个数
    int max = 8;
    // 存放皇后的下标【位置】
    int[] arr = new int[max];
    static int count = 0;
    // 判断冲突次数
    static int judgeNum = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("八皇后问题一共有%d种放置方法", count);
        System.out.println();
        System.out.printf("八皇后问题一共判断了冲突%d次", judgeNum);
    }

    // 放置第n个皇后
    private void check(int n) {
        if (n == max) { // 表示放置第9个皇后，直接打印所有皇后的位置即可，不需要再进行放置
            print();
            return;
        }
        // 此处表示皇后还没放完，依次放，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把第n个皇后放到该行（当前行）的第一个位置,如果不合适，递归会继续调用改变位置
            arr[n] = i;
            if (judge(n)) { // 不冲突
                // 如果符合要求，递归调用，回溯算法
                check(n + 1);
            }
            // 如果冲突，继续for循环，放置皇后的位置，在当前行后移一个位置
        }
    }

    // 判断放置第n个皇后时，其位置与前面已经放置好的皇后位置是否符合要求（是否冲突）：不能在同一列，同一斜线；
    private boolean judge(int n) {
        judgeNum++;
        for (int i = 0; i < n; i++) {
            // arr[i] == arr[n] 表示在同一列
            // Math.abs(n-i) == Math.abs(arr[n]-arr[i] 表示在同一斜线
            // 同一行无须判断，因为i自增，不可能在同一行
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    // 输出皇后摆放的位置
    private void print() {
        count++;
        //		for (int i : arr) {
        //			System.out.print(arr[i] + " ");
        //		}
        // 这里不使用增强for循环
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
