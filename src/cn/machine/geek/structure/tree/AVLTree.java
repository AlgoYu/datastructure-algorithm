package cn.machine.geek.structure.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: MachineGeek
 * @Description: AVL树/二叉平衡树
 * @Email: 794763733@qq.com
 * @Date: 2020/12/31
 */
public class AVLTree<E> {
    /**
    * @Author: MachineGeek
    * @Description: 节点
    * @Date: 2020/12/31
    * @Return:
    */
    public class Node<E>{
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        private int height;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
            height = 1;
        }
    }
    /**
    * @Author: MachineGeek
    * @Description: 遍历操作抽象类
    * @Date: 2020/12/31
    * @Return:
    */
    public static abstract class Visitor<E>{
        boolean stop;
        protected abstract boolean operate(E element);
    }
    private Node<E> root;
    private int size;
    private Comparator<E> comparator;

    /**
    * @Author: MachineGeek
    * @Description: 空构造函数
    * @Date: 2020/12/31
     * @param
    * @Return:
    */
    public AVLTree() {
    }

    /**
    * @Author: MachineGeek
    * @Description: 自定义比较器的构造函数
    * @Date: 2020/12/31
     * @param comparator
    * @Return:
    */
    public AVLTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加节点
    * @Date: 2020/12/31
     * @param element
    * @Return: void
    */
    public void add(E element){
        if(size == 0){
            root = new Node<>(element,null);
        }else {
            Node<E> temp = root;
            Node<E> parent = null;
            int value = 0;
            while (temp != null) {
                parent = temp;
                value = compare(temp.element, element);
                if(value == 0){
                    temp.element = element;
                    return;
                }
                if (value > 0) {
                    temp = temp.left;
                } else{
                    temp = temp.right;
                }
            }
            Node<E> node = new Node<>(element,parent);
            if (value > 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
            balance(node,false);
        }
        size++;
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取树的高度
    * @Date: 2020/12/31
     * @param
    * @Return: int
    */
    public int height(){
        return height(root);
    }

    private int height(Node<E> node){
        if(node == null){
            return 0;
        }
        return Math.max(height(node.left),height(node.right)) + 1;
    }

    /**
    * @Author: MachineGeek
    * @Description: 比较元素
    * @Date: 2020/12/31
     * @param element1
     * @param element2
    * @Return: int
    */
    private int compare(E element1,E element2){
        if(comparator != null){
            return comparator.compare(element1,element2);
        }
        return ((Comparable)element1).compareTo(element2);
    }

    /**
    * @Author: MachineGeek
    * @Description: 清空二叉树
    * @Date: 2020/12/31
     * @param
    * @Return: void
    */
    public void clear(){
        root = null;
        size = 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 删除节点
    * @Date: 2020/12/31
     * @param element
    * @Return: void
    */
    public void remove(E element){
        Node<E> node = getNode(element);
        if(node == null){
            return;
        }
        if(node.left != null && node.right != null){
            Node<E> predecessor = predecessorNode(node);
            node.element = predecessor.element;
            node = predecessor;
        }
        Node<E> replace = node.left != null? node.left : node.right;
        if(replace != null){
            replace.parent = node.parent;
            if(replace.parent == null) {
                root = replace;
            }else if(node.parent.left == node){
                node.parent.left = replace;
            }else{
                node.parent.right = replace;
            }
        }else if(node.parent == null){
            root = null;
        }else{
            if(node.parent.left == node){
                node.parent.left = null;
            }else{
                node.parent.right = null;
            }
        }
        balance(node,true);
        size--;
    }

    /**
    * @Author: MachineGeek
    * @Description: 找到当前节点的中序遍历前驱节点
    * @Date: 2020/12/31
     * @param node
    * @Return: cn.machine.geek.structure.tree.BinarySearchTree<E>.Node<E>
    */
    private Node<E> predecessorNode(Node<E> node){
        if(node == null){
            return null;
        }
        if(node.left != null){
            node = node.left;
            while (node.right != null){
                node = node.right;
            }
            return node;
        }
        while (node.parent != null && node.parent.left == node){
            node = node.parent;
        }
        return node.parent;
    }

    /**
    * @Author: MachineGeek
    * @Description: 找到当前节点的中序遍历后继节点
    * @Date: 2020/12/31
     * @param node
    * @Return: cn.machine.geek.structure.tree.BinarySearchTree<E>.Node<E>
    */
    private Node<E> subsequentNode(Node<E> node){
        if(node == null){
            return null;
        }
        if(node.right != null){
            node = node.right;
            while (node.left != null){
                node = node.left;
            }
            return node;
        }
        while (node.parent != null && node.parent.right == node){
            node = node.parent;
        }
        return node.parent;
    }

    /**
    * @Author: MachineGeek
    * @Description: 查找节点
    * @Date: 2020/12/31
     * @param element
    * @Return: cn.machine.geek.structure.tree.BinarySearchTree<E>.Node<E>
    */
    public Node<E> getNode(E element){
        Node<E> temp = root;
        while (temp != null){
            int value = compare(temp.element,element);
            if(value == 0){
                return temp;
            }
            if(value > 0){
                temp = temp.left;
            }else{
                temp = temp.right;
            }
        }
        return temp;
    }

    /**
    * @Author: MachineGeek
    * @Description: 前序遍历
    * @Date: 2020/12/31
     * @param
    * @Return: void
    */
    public void preorderTraverse(Visitor<E> visitor){
        if(visitor == null){
            return;
        }
        preorder(root,visitor);
    }

    /**
    * @Author: MachineGeek
    * @Description: 中序遍历
    * @Date: 2020/12/31
     * @param
    * @Return: void
    */
    public void inorderTraverse(Visitor<E> visitor){
        if(visitor == null){
            return;
        }
        inorder(root,visitor);
    }

    /**
    * @Author: MachineGeek
    * @Description: 后序遍历
    * @Date: 2020/12/31
     * @param
    * @Return: void
    */
    public void postorderTraverse(Visitor<E> visitor){
        if(visitor == null){
            return;
        }
        postorder(root,visitor);
    }

    /**
    * @Author: MachineGeek
    * @Description: 层序遍历
    * @Date: 2020/12/31
     * @param
    * @Return: void
    */
    public void levelOrderTraverse(Visitor<E> visitor){
        if(size == 0 || visitor == null){
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(visitor.operate(node.element)){
                return;
            }
            if(node.left != null ){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 判断是否是一颗完全二叉树
    * @Date: 2020/12/31
     * @param
    * @Return: boolean
    */
    public boolean isCompletion(){
        boolean is = true;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> temp = queue.poll();
            if(leaf && (temp.left !=null || temp.right !=null)){
                return false;
            }

            if(temp.left != null){
                queue.add(temp.left);
            }else if(temp.right != null){
                return false;
            }

            if(temp.right != null){
                queue.add(temp.right);
            }else{
                leaf = true;
            }
        }
        return is;
    }

    /**
    * @Author: MachineGeek
    * @Description: 前序
    * @Date: 2020/12/31
     * @param node
    * @Return: void
    */
    private void preorder(Node<E> node, Visitor<E> visitor){
        if(null == node || visitor.stop){
            return;
        }
        visitor.stop = visitor.operate(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
    * @Author: MachineGeek
    * @Description: 中序
    * @Date: 2020/12/31
     * @param node
    * @Return: void
    */
    private void inorder(Node<E> node,Visitor<E> visitor){
        if(null == node || visitor.stop){
            return;
        }
        inorder(node.left,visitor);
        if(visitor.stop){
            return;
        }
        visitor.stop = visitor.operate(node.element);
        inorder(node.right,visitor);
    }

    /**
    * @Author: MachineGeek
    * @Description: 后序
    * @Date: 2020/12/31
     * @param node
    * @Return: void
    */
    private void postorder(Node<E> node,Visitor<E> visitor){
        if(null == node || visitor.stop){
            return;
        }
        postorder(node.left,visitor);
        postorder(node.right,visitor);
        if(visitor.stop){
            return;
        }
        visitor.stop = visitor.operate(node.element);
    }

    /**
    * @Author: MachineGeek
    * @Description: 是否为空
    * @Date: 2020/12/31
     * @param
    * @Return: boolean
    */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 包含元素
    * @Date: 2020/12/31
     * @param element
    * @Return: boolean
    */
    public boolean contains(E element){
        return getNode(element) != null;
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取节点的平衡因子
    * @Date: 2020/12/31
     * @param node
    * @Return: int
    */
    private int balanceFactor(Node<E> node){
        return (node.left!=null?node.left.height:0) - (node.right!=null?node.right.height:0);
    }

    /**
    * @Author: MachineGeek
    * @Description: 判断节点是否平衡
    * @Date: 2020/12/31
     * @param node
    * @Return: boolean
    */
    private boolean isBalance(Node<E> node){
        return Math.abs(balanceFactor(node)) <= 1;
    }

    /**
    * @Author: MachineGeek
    * @Description: 更新高度
    * @Date: 2020/12/31
     * @param node
    * @Return: int
    */
    private void updateHeight(Node<E> node){
        node.height = Math.max(node.left!=null?node.left.height:0,node.right!=null?node.right.height:0) + 1;
    }

    /**
    * @Author: MachineGeek
    * @Description: 返回节点中更高的子节点
    * @Date: 2020/12/31
     * @param node
    * @Return: cn.machine.geek.structure.tree.AVLTree<E>.Node<E>
    */
    private Node<E> higherChild(Node<E> node){
        int leftHeight = node.left == null?0:node.left.height;
        int rightHeight = node.right == null?0:node.right.height;
        if(leftHeight > rightHeight){
            return node.left;
        }else{
            return node.right;
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 平衡二叉树
    * @Date: 2020/12/31
     * @param node
    * @Return: int
    */
    private void balance(Node<E> node,boolean isBreak){
        while ((node = node.parent) != null){
            if(isBalance(node)){
                updateHeight(node);
            }else{
                Node<E> child = higherChild(node);
                Node<E> grandchild = higherChild(child);
                if(child == node.left){
                    if(grandchild == child.right){
                        leftRotate(child);
                    }
                    rightRotate(node);
                }else{
                    if(grandchild == child.left){
                        rightRotate(child);
                    }
                    leftRotate(node);
                }
                if(isBreak){
                    break;
                }
            }
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 对节点进行左旋
     * @Date: 2020/12/31
     * @param node
     * @Return: cn.machine.geek.structure.tree.AVLTree<E>.Node<E>
     */
    private void leftRotate(Node<E> node){
        Node<E> right = node.right;
        node.right = right.left;
        right.left = node;
        right.parent = node.parent;
        node.parent = right;
        if(node.right != null){
            node.right.parent = node;
        }
        if(right.parent == null){
            root = right;
        }else if(right.parent.left == node){
            right.parent.left = right;
        }else{
            right.parent.right = right;
        }
        updateHeight(node);
        updateHeight(right);
    }

    /**
    * @Author: MachineGeek
    * @Description: 对节点进行右旋
    * @Date: 2020/12/31
     * @param node
    * @Return: cn.machine.geek.structure.tree.AVLTree<E>.Node<E>
    */
    private void rightRotate(Node<E> node){
        Node<E> left = node.left;
        node.left = left.right;
        left.right = node;
        left.parent = node.parent;
        node.parent = left;
        if(node.left != null){
            node.left.parent = node;
        }
        if(left.parent == null){
            root = left;
        }else if(left.parent.left == node){
            left.parent.left = left;
        }else{
            left.parent.right = left;
        }
        updateHeight(node);
        updateHeight(left);
    }

    /**
    * @Author: MachineGeek
    * @Description: 统一旋转，无视LL,RR,LR,RL。
    * @Date: 2020/12/31
     * @param root
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param e
     * @param g
    * @Return: void
    */
    private void unifyRotate(Node<E> root,Node<E> a,Node<E> b,Node<E> c,Node<E> d,Node<E> e,Node<E> f
            ,Node<E> g){
        d.parent = root.parent;
        if(d.parent == null){
            root = d;
        }else if(d.parent.left == root){
            d.parent.left = d;
        }else{
            d.parent.right = d;
        }

        b.right = c;
        if(c != null){
            c.parent = b;
        }
        updateHeight(b);

        f.left = e;
        if(e != null){
            e.parent = f;
        }
        updateHeight(f);

        d.left = b;
        d.right = f;
        updateHeight(d);
    }
}