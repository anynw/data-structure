package com.ceair.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 图
 *
 * @author wuhp
 * @date 2021/12/28
 */
public class Graph {

    public static void main(String[] args) {
        int n = 5;
        String[] vertexs = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // 添加边
        // 关系：A-B;A-C;B-C;B-D;B-E
        /**
         * [0, 1, 1, 0, 0]
         * [0, 1, 1, 1, 1]
         * [0, 0, 1, 0, 0]
         * [0, 0, 0, 1, 0]
         * [0, 0, 0, 0, 1]
         */
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        System.out.println("显示图");
        graph.showGraph();

    }

    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;

    // 构造器
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    /**
     * 插入节点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }


    /**
     * 添加边
     *
     * @param v1     点的下标，第几个顶点
     * @param v2     第二个顶点对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v2] = weight;
        numOfEdges++;
    }

    /**
     * 返回节点的个数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的数目
     *
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示矩阵
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
