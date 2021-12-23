package com.ceair.tree;

/**
 * 二叉树遍历
 * - 前序遍历：
 * 先输出父节点，再遍历左子树和右子树
 * - 中序遍历：
 * 先遍历左子树，再输出父节点，再遍历右子树
 * - 后序遍历：
 * 先遍历左子树，再遍历右子树，最后输出父节点
 *
 * @author wuhp
 * @date 2021/12/22
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode node1 = new HeroNode(1, "根节点");
        HeroNode node2 = new HeroNode(2, "左节点");
        HeroNode node3 = new HeroNode(3, "右节点");
        HeroNode node4 = new HeroNode(4, "右右节点");

        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(node1);
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.midOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    // 前序遍历
    public void preOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空，不能遍历");
        } else {
            this.root.preOrder();
        }
    }

    // 中序遍历
    public void midOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空，不能遍历");
        } else {
            this.root.midOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空，不能遍历");
        } else {
            this.root.postOrder();
        }
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;// null
    private HeroNode right;// null

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 前序遍历
    public void preOrder() {
        // 先输出根节点，即父节点
        System.out.println(this);
        // 遍历左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        // 遍历右子树
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    // 中序遍历
    public void midOrder() {
        // 先遍历左子树
        if (this.left != null) {
            this.left.midOrder();
        }
        // 输出父节点
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        // 先遍历左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        // 再遍历右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
        // 最后输出父节点
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
