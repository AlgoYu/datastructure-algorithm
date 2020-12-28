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
    public class Node<E>{
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
    public abstract class Visitor<E>{
        boolean stop;
        protected abstract boolean operate(E element);
    }
    private Node<E> root;
    private int size;
    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    /**
    * @Author: MachineGeek
    * @Description: 自定义比较器的构造函数
    * @Date: 2020/12/28
     * @param comparator
    * @Return:
    */
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加节点
    * @Date: 2020/12/28
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
                if (value > 0) {
                    temp = temp.left;
                } else if (value < 0) {
                    temp = temp.right;
                } else {
                    temp.element = element;
                    return;
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
    * @Author: MachineGeek
    * @Description: 比较元素
    * @Date: 2020/12/28
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
    * @Date: 2020/12/28
     * @param
    * @Return: void
    */
    public void clear(){
        root = null;
        size = 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 前序遍历
    * @Date: 2020/12/28
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
    * @Date: 2020/12/28
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
    * @Date: 2020/12/28
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
    * @Date: 2020/12/28
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
    * @Description: 前序
    * @Date: 2020/12/28
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
    * @Date: 2020/12/28
     * @param node
    * @Return: void
    */
    private void inorder(Node<E> node,Visitor<E> visitor){
        if(null == node || visitor.stop){
            return;
        }
        preorder(node.left,visitor);
        if(visitor.stop){
            return;
        }
        visitor.stop = visitor.operate(node.element);
        preorder(node.right,visitor);
    }

    /**
    * @Author: MachineGeek
    * @Description: 后序
    * @Date: 2020/12/28
     * @param node
    * @Return: void
    */
    private void postorder(Node<E> node,Visitor<E> visitor){
        if(null == node || visitor.stop){
            return;
        }
        preorder(node.left,visitor);
        preorder(node.right,visitor);
        if(visitor.stop){
            return;
        }
        visitor.stop = visitor.operate(node.element);
    }

}
