package com.anynw.recursion;

/**
 * 模拟迷宫 8行7列
 *
 * @author huaping
 * @time 2020-03-21 3:28:08 PM
 */
public class MiGong {

    public static void main(String[] args) {
        int[][] map = new int[8][7];

        // 前后置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 输出map地图
        /**
         1 1 1 1 1 1 1
         1 0 0 0 0 0 1
         1 0 0 0 0 0 1
         1 0 0 0 0 0 1
         1 0 0 0 0 0 1
         1 0 0 0 0 0 1
         1 0 0 0 0 0 1
         1 1 1 1 1 1 1
         */
        System.out.println("初始地图:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        map[3][1] = 1;
        map[3][2] = 1;
        // 回溯，设置一个密闭空间
        map[1][2] = 1;
        map[2][2] = 1;
        System.out.println("设置挡板过后的地图:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map, 1, 1);

        //
        System.out.println("打印走过的地图:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    // 1.map表示地图
    // 2.i，j表示从哪里开始走
    // 3.约定规则：走的方向-》下 右 上 左
    // 4.0 表示没有走过，可以走；1 表示这是最外层的墙，不通； 2 表示可以到达终点，通； 3 表示 走了一遍，发现不通，需要回溯；

    public static boolean setWay(int[][] map, int i, int j) {
        // 终点是 map[7][6],如果等于2，表示已经到达终点，返回true
        if (map[6][5] == 2) {
            return true;
        } else {
            // 如果此坐标是0，表示可以走，那么假定该位置为2，可以到达终点；
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {// 下
                    return true;
                } else if (setWay(map, i, j + 1)) {// 右
                    return true;
                } else if (setWay(map, i - 1, j)) {// 上
                    return true;
                } else if (setWay(map, i, j - 1)) {// 左
                    return true;
                } else {
                    // 约定的策略都不通，置为3，需要回溯；
                    map[i][j] = 3;
                    return false;
                }
            } else { // 不等于0，有可能等于1，2，3.直接返回false；
                return false;
            }
        }
    }

}
