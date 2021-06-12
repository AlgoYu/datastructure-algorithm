package cn.machine.geek.structure.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: MachineGeek
 * @Description: 二叉搜索树
 * @Email: 794763733@qq.com
 * @Date: 2020/12/28
 */
public class BinarySearchTree<E> {
    /**
     * @Author: MachineGeek
     * @Description: 节点
     * @Date: 2020/12/28
     * @Return:
     */
    public class Node<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 遍历操作抽象类
     * @Date: 2020/12/28
     * @Return:
     */
    public static abstract class Visitor<E> {
        boolean stop;

        protected abstract boolean operate(E element);
    }

    private Node<E> root;
    private int size;
    private Comparator<E> comparator;

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 空构造函数
     * @Date: 2020/12/28
     * @Return:
     */
    public BinarySearchTree() {
    }

    /**
     * @param comparator
     * @Author: MachineGeek
     * @Description: 自定义比较器的构造函数
     * @Date: 2020/12/28
     * @Return:
     */
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 增加节点
     * @Date: 2020/12/28
     * @Return: void
     */
    public void add(E element) {
        if (size == 0) {
            root = new Node<>(element, null);
        } else {
            Node<E> temp = root;
            Node<E> parent = null;
            int value = 0;
            while (temp != null) {
                parent = temp;
                value = compare(temp.element, element);
                if (value == 0) {
                    temp.element = element;
                    return;
                }
                if (value > 0) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            if (value > 0) {
                parent.left = new Node<>(element, parent);
            } else {
                parent.right = new Node<>(element, parent);
            }
        }
        size++;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 获取树的高度
     * @Date: 2020/12/28
     * @Return: int
     */
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * @param element1
     * @param element2
     * @Author: MachineGeek
     * @Description: 比较元素
     * @Date: 2020/12/28
     * @Return: int
     */
    private int compare(E element1, E element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        }
        return ((Comparable) element1).compareTo(element2);
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 清空二叉树
     * @Date: 2020/12/28
     * @Return: void
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 删除节点
     * @Date: 2020/12/29
     * @Return: void
     */
    public void remove(E element) {
        Node<E> node = getNode(element);
        if (node == null) {
            return;
        }
        if (node.left != null && node.right != null) {
            Node<E> predecessor = predecessorNode(node);
            node.element = predecessor.element;
            node = predecessor;
        }
        Node<E> replace = node.left != null ? node.left : node.right;
        if (replace != null) {
            replace.parent = node.parent;
            if (replace.parent == null) {
                root = replace;
            } else if (node.parent.left == node) {
                node.parent.left = replace;
            } else {
                node.parent.right = replace;
            }
        } else if (node.parent == null) {
            root = null;
        } else {
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
        size--;
    }

    /**
     * @param node
     * @Author: MachineGeek
     * @Description: 找到当前节点的中序遍历前驱节点
     * @Date: 2020/12/29
     * @Return: cn.machine.geek.structure.tree.BinarySearchTree<E>.Node<E>
     */
    private Node<E> predecessorNode(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            node = node.left;
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }
        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * @param node
     * @Author: MachineGeek
     * @Description: 找到当前节点的中序遍历后继节点
     * @Date: 2020/12/29
     * @Return: cn.machine.geek.structure.tree.BinarySearchTree<E>.Node<E>
     */
    private Node<E> subsequentNode(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 查找节点
     * @Date: 2020/12/29
     * @Return: cn.machine.geek.structure.tree.BinarySearchTree<E>.Node<E>
     */
    public Node<E> getNode(E element) {
        Node<E> temp = root;
        while (temp != null) {
            int value = compare(temp.element, element);
            if (value == 0) {
                return temp;
            }
            if (value > 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return temp;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 前序遍历
     * @Date: 2020/12/28
     * @Return: void
     */
    public void preorderTraverse(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        preorder(root, visitor);
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 中序遍历
     * @Date: 2020/12/28
     * @Return: void
     */
    public void inorderTraverse(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inorder(root, visitor);
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 后序遍历
     * @Date: 2020/12/28
     * @Return: void
     */
    public void postorderTraverse(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postorder(root, visitor);
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 层序遍历
     * @Date: 2020/12/28
     * @Return: void
     */
    public void levelOrderTraverse(Visitor<E> visitor) {
        if (size == 0 || visitor == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.operate(node.element)) {
                return;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 判断是否是一颗完全二叉树
     * @Date: 2020/12/29
     * @Return: boolean
     */
    public boolean isCompletion() {
        boolean is = true;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> temp = queue.poll();
            if (leaf && (temp.left != null || temp.right != null)) {
                return false;
            }

            if (temp.left != null) {
                queue.add(temp.left);
            } else if (temp.right != null) {
                return false;
            }

            if (temp.right != null) {
                queue.add(temp.right);
            } else {
                leaf = true;
            }
        }
        return is;
    }

    /**
     * @param node
     * @Author: MachineGeek
     * @Description: 前序
     * @Date: 2020/12/28
     * @Return: void
     */
    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (null == node || visitor.stop) {
            return;
        }
        visitor.stop = visitor.operate(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * @param node
     * @Author: MachineGeek
     * @Description: 中序
     * @Date: 2020/12/28
     * @Return: void
     */
    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (null == node || visitor.stop) {
            return;
        }
        inorder(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.operate(node.element);
        inorder(node.right, visitor);
    }

    /**
     * @param node
     * @Author: MachineGeek
     * @Description: 后序
     * @Date: 2020/12/28
     * @Return: void
     */
    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (null == node || visitor.stop) {
            return;
        }
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.operate(node.element);
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 是否为空
     * @Date: 2020/12/30
     * @Return: boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 包含元素
     * @Date: 2020/12/30
     * @Return: boolean
     */
    public boolean contains(E element) {
        return getNode(element) != null;
    }
}
