package com.ceair.avl;

/**
 * 平衡二叉树
 *
 * @author wuhp
 * @date 2021/12/27
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        AVLTree avlTree = new AVLTree();

        for (int i : arr){
            avlTree.add(new Node(i));
        }
        System.out.println("中序遍历平衡二叉树");
        avlTree.midOrder();

        System.out.println(avlTree.height());
        System.out.println(avlTree.leftHeight());
        System.out.println(avlTree.rightHeight());

        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }

}

// 创建平衡二叉树
class AVLTree {
    private Node root;

    public int height() {
        return root == null ? 0 : root.height();
    }

    public int leftHeight() {
        return root == null ? 0 : root.leftHeight();
    }

    public int rightHeight() {
        return root == null ? 0 : root.rightHeight();
    }


    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //删除节点，三种情况分析
    public void delNode(int value) {
        if (root == null) {
            return;
        }
        // 待删除的节点
        Node targetNode = search(value);
        // 没找到待删除的节点
        if (targetNode == null) {
            return;
        }
        // 找到了，如果targetNode没有父节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }

        // 父节点
        Node parent = searchParent(value);
        // 删除叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            // 判断targetNode是父节点的左子节点还是右子节点
            if (parent.left != null && parent.left.value == value) {// left
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {// right
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {//删除两颗子树的节点
            /**
             *  * 1.找出待删除的节点targetNode 10
             *  * 2.找出待删除的节点的父节点parent 7
             *  * 3.找到targetNode的右节点中的的最小值 12  定义变量 temp = 12
             *  * 4.删除该最小值 12 令 targetNode.value = temp（12）
             */
            int minValue = delRightTreeMin(targetNode.right);
            targetNode.value = minValue;
        } else {// 删除只有一颗子树的节点
            // 如果targetNode存在左子节点
            if (targetNode.left != null) {
                if (parent != null) {
                    if (parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;// 只剩下两个节点，删除的刚好的父节点
                }
            } else {// targetNode存在右子节点
                if (parent != null) {
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }
        }
    }

    /**
     * 删除以node为根节点的树的最小节点的值，并返回该值
     *
     * @param node
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    // 中序遍历
    public void midOrder() {
        if (root != null) {
            root.midOrder();
        } else {
            System.out.println("空二叉树不能遍历");
        }
    }

    public Node getRoot() {
        return root;
    }
}


// 创建节点
class Node {
    int value;
    Node left;
    Node right;

    /**
     * 返回左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        return left == null ? 0 : left.height();
    }

    /**
     * 返回右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        return right == null ? 0 : right.height();
    }

    /**
     * 计算树的高度
     *
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 找出待删除的节点targetNode
     *
     * @param value 待删除的值
     * @return
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        }
        if (value > this.value) {//right
            return this.right == null ? null : this.right.search(value);
        } else {
            return this.left == null ? null : this.left.search(value);
        }
    }

    /**
     * 找出待删除的节点的父节点parent
     *
     * @param value 待删除的值
     * @return
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;// 没有父节点
            }
        }
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {// 左
            if (this.left != null) {
                // 递归向左👈
                this.left.add(node);
            } else {
                this.left = node;
            }
        } else {
            if (this.right != null) {
                // 递归向右👉
                this.right.add(node);
            } else {
                this.right = node;
            }
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

