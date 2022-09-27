package com.anynw.algorithms4th.std;

import edu.princeton.cs.algs4.StdDraw;

/**
 * @author wuhp
 * @date 2022/9/27
 */
public class Std {
    public static void main(String[] args) {
        // test1();
        // System.out.println(1 / 0);
        System.out.println(1.0 / 0.0);
        
    }

    public static void test1() {
        int n = 100;
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n * n);
        StdDraw.setPenRadius(.01);
        for (int i = 10; i <= n; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, i * Math.log(i));
        }
    }
}
