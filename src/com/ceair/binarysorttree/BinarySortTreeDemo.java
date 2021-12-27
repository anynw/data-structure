package com.ceair.binarysorttree;

/**
 * 二叉排序树:
 * 删除逻辑三种情况
 * 一、删除叶子节点，例如节点2，5，9，12
 * 1.找出待删除的节点targetNode
 * 2.找出待删除的节点的父节点parent
 * 3.判断targetNode是父节点的左节点还是右节点
 * 如果是父节点的左节点：parent.left = null
 * 如果是父节点的右节点：parent.right = null
 * 二、删除只有一颗子树的节点，例如节点 1
 * 1.找出待删除的节点targetNode 1
 * 2.找出待删除的节点的父节点parent 3
 * 3.判断targetNode是父节点的左节点还是右节点
 * 如果是父节点的左节点: 例如 1就是3的左子节点
 * //----------如果parent为空，删除的是根节点，直接根节点指向 targetNode.left 否则执行以下逻辑
 * 如果targetNode存在左节点，（案例中 1 不存在）
 * parent.left = targetNode.left
 * 如果targetNode存在右节点，（案例中 1 存在 右节点 2）
 * parent.left = targetNode.right
 * 如果是父节点的右节点：
 * //----------如果parent为空，删除的是根节点，直接根节点指向 targetNode.right 否则执行以下逻辑
 * 如果targetNode存在左子节点
 * parent.right = targetNode.left
 * 如果targetNode存在右子节点
 * parent.right = targetNode.right
 * 三、删除有两颗子树的节点 例如 7 3 10
 * 1.找出待删除的节点targetNode 10
 * 2.找出待删除的节点的父节点parent 7
 * 3.找到targetNode的右节点中的的最小值 12  定义变量 temp = 12
 * 4.删除该最小值 12 令 targetNode.value = temp（12）
 *
 * @author wuhp
 * @date 2021/12/26
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {7, 3, 10, 12, 5, 1, 9};
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();

        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历二叉排序树");
        binarySortTree.midOrder();

        System.out.println("查找父节点：" + binarySortTree.searchParent(5));


        /**
         * 二叉树图示：
         *  * * * * * * * * * * * * * * *
         *  *          7                *
         *  *      3        10          *
         *  *  1      5  9     12       *
         *  *      2                    *
         *  * * * * * * * * * * * * * * *
         */

        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(7);
        binarySortTree.delNode(10);
        binarySortTree.delNode(3);// 两颗子树的节点
        binarySortTree.delNode(1); // 只有一颗子树的节点
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);// 叶子节点

        System.out.println("删除节点后：");
        binarySortTree.midOrder();
        System.out.println("root=" + binarySortTree.getRoot());


    }
}

// 创建二叉排序树
class BinarySortTree {
    private Node root;

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
