package com.ceair.commonalgorithm;

/**
 * 汉诺塔
 *
 * @author wuhp
 * @date 2021/12/29
 */
public class Hanoi {

    private static volatile int count;

    public static void main(String[] args) {
        hannoi(7, 'A', 'B', 'C');
        System.out.println("count = " + count);
    }



    /**
     * 汉诺塔移动方案
     *
     * @param num 盘的个数
     * @param a
     * @param b
     * @param c
     */
    public static void hannoi(int num, char a, char b, char c) {
        count++;
        if (num == 1) {
            System.out.println("第1个盘从： " + a + "->" + c);
        }else {
            // 1.先把最上面的所有盘 从A到B
            hannoi(num-1, a, c, b);
            // 2.把最下面的盘A到C
            System.out.println("第" + num + "个盘从： " + a + "->" + c);
            // 3.把B的所有盘 从B到C
            hannoi(num-1, b, a, c);
        }
    }
}
