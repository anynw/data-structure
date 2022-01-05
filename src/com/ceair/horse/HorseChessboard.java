package com.ceair.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘问题
 *
 * @author wuhp
 * @date 2022/1/5
 */
public class HorseChessboard {
    private static int X;//列
    private static int Y;//行
    private static boolean visited[];//标记是否被访问过
    private static boolean finished;//标记所有被访问

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];
        System.out.println("计算开始：");
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");

        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
        /**
         * 耗时：14s
         * 1	8	11	16	3	18	13	64
         * 10	27	2	7	12	15	4	19
         * 53	24	9	28	17	6	63	14
         * 26	39	52	23	62	29	20	5
         * 43	54	25	38	51	22	33	30
         * 40	57	42	61	32	35	48	21
         * 55	44	59	50	37	46	31	34
         * 58	41	56	45	60	49	36	47
         */
    }

    /**
     * 骑士周游
     *
     * @param chessboard 棋盘
     * @param row
     * @param column
     * @param step       第几步，从1开始
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true;
        ArrayList<Point> ps = next(new Point(column, row));
        // 排序
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        // 判断是否完成
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置，计算马还能走多少步，加入集合，最多8个位置
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> list = new ArrayList<Point>();
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            list.add(p1);
        }

        Point p2 = new Point();
        if ((p2.x = curPoint.x - 1) >= 0 && (p2.y = curPoint.y - 2) >= 0) {
            list.add(p2);
        }

        Point p3 = new Point();
        if ((p3.x = curPoint.x + 1) < X && (p3.y = curPoint.y - 2) >= 0) {
            list.add(p3);
        }

        Point p4 = new Point();
        if ((p4.x = curPoint.x + 2) < X && (p4.y = curPoint.y - 1) >= 0) {
            list.add(p4);
        }

        Point p5 = new Point();
        if ((p5.x = curPoint.x + 2) < X && (p5.y = curPoint.y + 1) < Y) {
            list.add(p5);
        }

        Point p6 = new Point();
        if ((p6.x = curPoint.x + 1) < X && (p6.y = curPoint.y + 2) < Y) {
            list.add(p6);
        }

        Point p7 = new Point();
        if ((p7.x = curPoint.x - 1) >= 0 && (p7.y = curPoint.y + 2) < Y) {
            list.add(p7);
        }

        Point p8 = new Point();
        if ((p8.x = curPoint.x - 2) >= 0 && (p8.y = curPoint.y + 1) < Y) {
            list.add(p8);
        }
        return list;
    }

    /**
     * 非递减排序，减少回溯的次数，性能提高，1400ms->12ms
     *
     * @param ps
     */
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 > count2) {
                    return 1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }
}
