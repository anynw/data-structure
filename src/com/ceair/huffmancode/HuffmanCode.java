package com.ceair.huffmancode;

import java.util.*;

/**
 * 赫夫曼编码
 *
 * @author wuhp
 * @date 2021/12/26
 */
public class HuffmanCode {
    public static void main(String[] args) {
        // String content = "I love you";
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        System.out.println(bytes.length); // 40

        List<Node> nodes = getNodes(bytes);
        System.out.println(nodes);

        Node root = createHuffmanTree(nodes);
        System.out.println("赫夫曼树root节点：" + root);
        System.out.println("前序遍历：");
        root.preOrder();

        Map<Byte, String> codes = getCodes(root);
        System.out.println("生成的哈夫曼编码表：" + codes);

        System.out.println("------zip-------");
        byte[] zipBytes = zip(bytes, codes);
        System.out.println(Arrays.toString(zipBytes));
        System.out.println(zipBytes.length); // 17
        System.out.println("压缩率 = " + (double)(bytes.length - zipBytes.length) / bytes.length); //57.5%


    }

    // 拼接路径，存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();
    // 哈夫曼表存放形式
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

    // 哈夫曼编码生成方法的重载
    public static Map<Byte, String> getCodes(Node node) {
        if (node == null) {
            return null;
        }
        getCodes(node.left, "0", stringBuilder);
        getCodes(node.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 压缩
     *
     * @param bytes        字符串对应的byte[]数组
     * @param huffmanCodes 哈夫曼编码表
     * @return 返回哈夫曼编码处理过的bytep[]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println(stringBuilder);//1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        // 将 stringBuilder 转成byte[]
        int len = (stringBuilder.length() + 7) / 8;
        // 创建存在压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        // 记录是第几个byte
        int index = 0;
        // 每8为对应一个byte，所以步长为8
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            // 防止越界
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 生成哈夫曼编码
     *
     * @param node
     * @param code
     * @param builder
     */
    private static void getCodes(Node node, String code, StringBuilder builder) {
        StringBuilder stringBuilder = new StringBuilder(builder);
        stringBuilder.append(code);
        if (node != null) {// 节点为空，不做处理
            if (node.data == null) { // 非叶子节点
                getCodes(node.left, "0", stringBuilder);// 向左递归
                getCodes(node.right, "1", stringBuilder);// 向右递归
            } else {// 说明是一个叶子节点
                // 找到了某个节点的最后
                huffmanCodes.put(node.data, stringBuilder.toString());
            }
        }
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
