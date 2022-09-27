package com.anynw.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法，所有点直接之间的最短路径，时间复杂度比较高
 *
 * @author wuhp
 * @date 2022/1/5
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] {0, 5, 7, N, N, N, 2};
        matrix[1] = new int[] {5, 0, N, 9, N, N, 3};
        matrix[2] = new int[] {7, N, 0, N, 8, N, N};
        matrix[3] = new int[] {N, 9, N, 0, N, 4, N};
        matrix[4] = new int[] {N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[] {N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[] {2, 3, N, N, 4, 6, 0};
        /**
         * A A A A A A A A到A：0 A到B：5 A到C：7 A到D：N A到E：N A到F：N A到G：2
         * B B B B B B B B到A：5 B到B：0 B到C：N B到D：9 B到E：N B到F：N B到G：3
         * C C C C C C C C到A：7 C到B：N C到C：0 C到D：N C到E：8 C到F：N C到G：N
         * D D D D D D D D到A：N D到B：9 D到C：N D到D：0 D到E：N D到F：4 D到G：N
         * E E E E E E E E到A：N E到B：N E到C：8 E到D：N E到E：0 E到F：5 E到G：4
         * F F F F F F F F到A：N F到B：N F到C：N F到D：4 F到E：5 F到F：0 F到G：6
         * G G G G G G G G到A：2 G到B：3 G到C：N G到D：N G到E：4 G到F：6 G到G：0
         */

        Graph graph = new Graph(vertex.length, matrix, vertex);
        /**
         * A A A F G G A A到A：0 A到B：5 A到C：7 A到D：12 A到E：6 A到F：8 A到G：2
         * B B A B G G B B到A：5 B到B：0 B到C：12 B到D：9 B到E：7 B到F：9 B到G：3
         * C A C F C E A C到A：7 C到B：12 C到C：0 C到D：17 C到E：8 C到F：13 C到G：9
         * G D E D F D F D到A：12 D到B：9 D到C：17 D到D：0 D到E：9 D到F：4 D到G：10
         * G G E F E E E E到A：6 E到B：7 E到C：8 E到D：9 E到E：0 E到F：5 E到G：4
         * G G E F F F F F到A：8 F到B：9 F到C：13 F到D：4 F到E：5 F到F：0 F到G：6
         * G G A F G G G G到A：2 G到B：3 G到C：9 G到D：10 G到E：4 G到F：6 G到G：0
         */
        graph.floyd();
        graph.show(vertex);
    }
}

// 创建图
class Graph {
    private char[] vertex;//存放顶点的数据
    private int[][] dis;// 保存从各个顶点到其它顶点的距离
    private int[][] pre;// 保存到达目标顶点的前驱顶点

    /**
     * @param length
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 初始化pre，存放前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 显示pre数组和dis数组
     */
    public void show(char[] vertex) {
        for (int i = 0; i < dis.length; i++) {
            // pre
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + "  ");
            }
            // dis
            for (int k = 0; k < dis.length; k++) {
                String str = dis[i][k] == 65535 ? "N" : String.valueOf(dis[i][k]);
                System.out.print(vertex[i] + "到" + vertex[k] + "：" + str + "  ");
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd() {
        int len = 0;
        // 中间顶点遍历，i就是中间顶点的下标
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                for (int k = 0; k < dis.length; k++) {
                    len = dis[j][i] + dis[i][k];
                    if (len < dis[j][k]) {//小于直连距离
                        dis[j][k] = len;// 更新距离
                        pre[j][k] = pre[i][k];// 更新前驱节点
                    }
                }
            }
        }
    }
}