package com.ceair.horse;

import java.awt.*;
import java.util.ArrayList;

/**
 * 马踏棋盘问题
 *
 * @author wuhp
 * @date 2022/1/5
 */
public class HorseChessboard {
    private static int X;//列
    private static int Y;//行

    public static void main(String[] args) {

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
}
