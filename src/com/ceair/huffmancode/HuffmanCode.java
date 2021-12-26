package com.ceair.huffmancode;

import java.util.*;

/**
 * 赫夫曼编码
 * @author wuhp
 * @date 2021/12/26
 */
public class HuffmanCode {
    public static void main(String[] args) {
        // String content = "I love you";
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        System.out.println(bytes.length);

        List<Node> nodes = getNodes(bytes);
        System.out.println(nodes);

        Node root = createHuffmanTree(nodes);
        System.out.println("赫夫曼树root节点：" + root);
        System.out.println("前序遍历：");
        root.preOrder();

    }

    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        // 遍历，统计每一个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
        for (byte bt : bytes) {
            Integer count = counts.get(bt);
            if (count == null) {
                counts.put(bt, 1);// 第一次
            } else {
                counts.put(bt, count + 1);
            }
        }
        // 把每一个键值对转成 一个Node对象，加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 通过list创建对应的赫夫曼树
     *
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 从小到大排序
            Collections.sort(nodes);
            // 取出最小的二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            // 创建一个新的二叉树，跟节点没有data，只有weight
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            // 将已经处理的两颗二叉树从nodes中删除
            nodes.remove(left);
            nodes.remove(right);
            // 加入新的二叉树到nodes中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}


// 创建Node
class Node implements Comparable<Node> {
    Byte data;// 存放数据
    int weight;// 权值，表示字符出现的次数
    Node left;
    Node right;

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
