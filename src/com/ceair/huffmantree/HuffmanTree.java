package com.ceair.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author wuhp
 * @date 2021/12/25
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        System.out.println(root);

        // System.out.println("测试前序遍历");
        // root.preOrder();
        System.out.println("测试中序遍历");
        root.midOrder();
        // System.out.println("测试后序遍历");
        // root.postOrder();
        System.out.println("栈前序遍历");
        root.stackPreOrder();


    }


    // 创建赫夫曼树
    public static Node createHuffmanTree(int[] arr) {

        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);
            System.out.println("nodes有序:" + nodes);

            // 1.取出根节点权值最小的两颗二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            // 2.构建一个新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            // 3.从List中删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);

            // 4.将parent加入到list中
            nodes.add(parent);
        }

        System.out.println(nodes);
        return nodes.get(0);
    }
}


// 创建节点类
class Node implements Comparable<Node> {
    int value;// 节点权值
    Node left;// 指向左子节点
    Node right;// 指向右子节点

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 栈前序遍历
    public void stackPreOrder() {
        Stack<Node> stack = new Stack<>();
        stack.push(this);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node);
            if (null != node.right) {
                stack.push(node.right);
            }
            if (null != node.left) {
                stack.push(node.left);
            }
        }
    }


    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.value - o.value;
    }
}
