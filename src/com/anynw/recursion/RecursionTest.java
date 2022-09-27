package com.anynw.recursion;

/**
 * 递归测试
 *
 * @author huaping
 * @time 2020-03-21 2:31:22 PM
 */
public class RecursionTest {

    public static void main(String[] args) {
        //test1(4);
        //test2(4);
        int factorial = factorial(4);
        System.out.println(factorial);
    }

    // test1 test2 打印问题
    public static void test1(int n) {
        if (n > 2) {
            test1(n - 1);
        }
        System.out.println(n);
    }

    public static void test2(int n) {
        if (n > 2) {
            test2(n - 1);
        } else {
            System.out.println(n);
        }
    }

    // 阶乘
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }

}
