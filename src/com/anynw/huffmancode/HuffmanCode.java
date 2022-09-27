package com.anynw.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼编码
 * 解压思路：
 * 1.huffmanCodeBytes = [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
 * 重新转成 哈夫曼编码对应的二进制字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
 * 2.将哈夫曼编码对应的二进制字符串转成 content 字符串内容
 *
 * @author wuhp
 * @date 2021/12/26
 */
public class HuffmanCode {
    public static void main(String[] args) {
        /**
         // String content = "I love you";
         String content = "i like like like java do you like a java";
         byte[] bytes = content.getBytes();
         // System.out.println(bytes.length); // 40
         //
         // List<Node> nodes = getNodes(bytes);
         // System.out.println(nodes);
         //
         // Node root = createHuffmanTree(nodes);
         // System.out.println("哈夫曼树root节点：" + root);
         // System.out.println("前序遍历：");
         // root.preOrder();
         //
         // Map<Byte, String> codes = getCodes(root);
         // System.out.println("生成的哈夫曼编码表：" + codes);
         //
         // System.out.println("------zip-------");
         // byte[] zipBytes = zip(bytes, codes);
         // System.out.println(Arrays.toString(zipBytes));
         // System.out.println(zipBytes.length); // 17
         // System.out.println("压缩率 = " + (double) (bytes.length - zipBytes.length) / bytes.length); //57.5%

         byte[] huffmanCodeBytes = huffmanZip(bytes);
         System.out.println(Arrays.toString(huffmanCodeBytes));

         System.out.println(0b100000000);// 256

         System.out.println(Arrays.toString(bytes));

         System.out.println("解码：");
         byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
         System.out.println(new String(sourceBytes));// iaikal kal kajoe aoeleijkuljoe

         */
        // 测试压缩文件
        // String srcFile = "/Users/wuhp/Documents/images/src.png";
        // String dstFile = "/Users/wuhp/Documents/images/dst.zip";
        // zipFile(srcFile, dstFile);

        String zipFile = "/Users/wuhp/Documents/images/dst.zip";
        String dstFile = "/Users/wuhp/Documents/images/src2.png";
        unZipFile(zipFile, dstFile);

    }

    // 拼接路径，存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();
    // 哈夫曼表存放形式
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

    /**
     * 压缩文件
     *
     * @param srcFile 源文件
     * @param dstFile 目标文件
     */
    public static void zipFile(String srcFile, String dstFile) {
        // 定义输入流
        FileInputStream is = null;
        // 定义输出流
        FileOutputStream os = null;
        // 定义对象流
        ObjectOutputStream oos = null;
        try {
            // 创建文件的输入流
            is = new FileInputStream(srcFile);
            // 创建一个与源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            // 对源文件进行压缩，返回哈夫曼字节数组
            byte[] huffmanBytes = huffmanZip(b);

            // 创建文件的输出流
            os = new FileOutputStream(dstFile);
            // 创建一个与文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 把哈夫曼编码后的字节数组写入压缩嗯见
            oos.writeObject(huffmanBytes);

            // 把哈夫曼编码写入压缩文件，为了以后恢复源文件使用
            oos.writeObject(huffmanCodes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压文件
     *
     * @param zipFile 待解压的文件
     * @param dstFile 解压文件的文件路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        InputStream is = null;
        OutputStream os = null;
        ObjectInputStream ois = null;
        try {
            File file;
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            // 读取bytes[]数组
            byte[] huffmanBytes = (byte[])ois.readObject();
            // 读取哈夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>)ois.readObject();
            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 解码
     *
     * @param huffmanCodes 哈夫曼编码表map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byte2BitString(!flag, huffmanBytes[i]));
        }
        // 把字符串安装指定的哈夫曼编码进行编码
        // 把哈夫曼编码表进行调换，反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // System.out.println("反向map = " + map);

        List<Byte> list = new ArrayList<>();
        // 根据count移动，所以for循环不需要i++
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {// 没有取到值
                    count++;
                } else {// 取到了值
                    // list.add(b);
                    flag = false;// 跳出循环
                }
            }
            list.add(b);
            i += count;// i 直接移动到count的位置
        }
        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 将一个byte 转成一个二进制的字符串
     *
     * @param flag true：需要补高位
     * @param b
     * @return 该b 对应的二进制的字符串（按照补码返回）
     */
    private static String byte2BitString(boolean flag, byte b) {
        int temp = b;// 将b转成int
        // 如果是正数，存在补高位
        if (flag) {
            temp |= 256; // 按位或256，两个有一个为1，结果为1，否则为0
        }
        String str = Integer.toBinaryString(temp);// 返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 压缩方法
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过哈夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 哈夫曼编码生成方法的重载
     *
     * @param node
     * @return
     */
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
        // System.out.println(stringBuilder);//1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
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
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
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
     * 通过list创建对应的哈夫曼树
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
