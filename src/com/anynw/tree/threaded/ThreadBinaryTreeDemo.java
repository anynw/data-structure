package com.anynw.tree.threaded;

/**
 * 二叉树遍历
 * - 前序遍历：
 * 先输出父节点，再遍历左子树和右子树
 * - 中序遍历：
 * 先遍历左子树，再输出父节点，再遍历右子树
 * - 后序遍历：
 * 先遍历左子树，再遍历右子树，最后输出父节点
 * <p>
 * 线索化中序二叉树遍历
 *
 * @author wuhp
 * @date 2021/12/25
 */
public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "1");
        HeroNode node2 = new HeroNode(3, "3");
        HeroNode node3 = new HeroNode(6, "6");
        HeroNode node4 = new HeroNode(8, "8");
        HeroNode node5 = new HeroNode(10, "10");
        HeroNode node6 = new HeroNode(14, "14");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);

        System.out.println("10号节点线索化之前的左右节点");
        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());
        // 测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        System.out.println("10号节点线索化之后的左右节点");
        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());

        System.out.println("使用线索化的方式[中序]遍历二叉树");
        threadedBinaryTree.threadedlist();

    }
}

// 定义线索化的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    // 前驱节点
    private HeroNode pre = null;

    // 重载
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    // 遍历线索化二叉树（中序）的方法
    public void threadedlist() {
        // 定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;
        // 循环找到leftType = 1 的节点
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点的右指针指向的后继鸡诶单，就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }
    }

    // 进行线索化，中序
    private void threadedNodes(HeroNode node) {
        // 节点为空，不能线索化
        if (node == null) {
            return;
        }
        // 1.先线索化左子树
        threadedNodes(node.getLeft());
        // 2.再线索化当前节点
        // 2.1.处理当前节点的前驱节点
        if (node.getLeft() == null) {
            // 当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点的左指针的类型
            node.setLeftType(1);
        }
        // 处理后继节点
        if (pre != null && pre.getRight() == null) {
            // 让前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型为1
            pre.setRightType(1);
        }
        // 每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        // 3.最后线索化右子树
        threadedNodes(node.getRight());

    }

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

    // 前序查找
    public HeroNode preOrderSearch(int id) {
        if (root != null) {
            return root.preOrderSearch(id);
        } else {
            return null;
        }
    }

    // 中序查找
    public HeroNode midOrderSearch(int id) {
        if (root != null) {
            return root.midOrderSearch(id);
        } else {
            return null;
        }
    }

    // 后序查找
    public HeroNode postOrderSearch(int id) {
        if (root != null) {
            return root.postOrderSearch(id);
        } else {
            return null;
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
    // leftType=0 代表指向的是左子树，如果是1，代表指向的是前驱节点
    // rightType=0 代表指向的右子树，如果是1，代表指向的后继节点
    private int leftType;
    private int rightType;

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

    /**
     * 前序查找
     *
     * @param id
     * @return
     */
    public HeroNode preOrderSearch(int id) {
        // 先比较当前节点
        if (this.id == id) {
            return this;
        }
        // 定义返回值
        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(id);
        }
        return resNode;
    }

    /**
     * 中序查找
     *
     * @param id
     * @return
     */
    public HeroNode midOrderSearch(int id) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.midOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.id == id) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.midOrderSearch(id);
        }
        return resNode;
    }

    /**
     * 后序查找
     *
     * @param id
     * @return
     */
    public HeroNode postOrderSearch(int id) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        // 如果左右子树都没有找到，比较当前节点
        if (this.id == id) {
            return this;
        }
        return resNode;
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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
