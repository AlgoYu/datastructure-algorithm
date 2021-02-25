package cn.machine.geek.structure.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: MachineGeek
 * @Description: 并查集-路径减半
 * @Email: 794763733@qq.com
 * @Date: 2021/2/25
 */
public class UnionFind<E> {
    private Map<E,Node<E>> map;

    /**
    * @Author: MachineGeek
    * @Description: 内部节点
    * @Date: 2021/2/25
    * @Return:
    */
    public static class Node<E>{
        private Node<E> parent;
        private int rank;
        private E element;

        public Node(E element) {
            this.parent = this;
            this.element = element;
            this.rank = 1;
        }
    }

    public UnionFind() {
        map = new HashMap<>();
    }

    /**
    * @Author: MachineGeek
    * @Description: 添加节点
    * @Date: 2021/2/25
     * @param element
    * @Return: void
    */
    public void makeSet(E element){
        // 不包含则添加
        if(!map.containsKey(element)){
            map.put(element,new Node<>(element));
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 查找元素属于哪个集合
    * @Date: 2021/2/25
     * @param element
    * @Return: E
    */
    public E find(E element){
        Node<E> node = findNode(element);
        return node == null? null : node.element;
    }

    /**
    * @Author: MachineGeek
    * @Description: 查找元素的集节点
    * @Date: 2021/2/25
     * @param element
    * @Return: cn.machine.geek.structure.unionfind.UnionFind.Node<E>
    */
    private Node<E> findNode(E element){
        Node<E> node = map.get(element);
        if(node == null){
            return null;
        }
        // 向上找到父节点，并在这个过程中，使向上寻找的路径减半。
        while (!Objects.equals(node.element,node.parent.element)){
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }

    /**
    * @Author: MachineGeek
    * @Description: 查看两个元素是否属于同一个集合
    * @Date: 2021/2/25
     * @param element1
     * @param element2
    * @Return: boolean
    */
    public boolean isSame(E element1,E element2){
        return Objects.equals(find(element1),find(element2));
    }

    /**
    * @Author: MachineGeek
    * @Description: 合并两个元素集
    * @Date: 2021/2/25
     * @param element1
     * @param element2
    * @Return: void
    */
    public void union(E element1,E element2){
        Node<E> node1 = findNode(element1);
        Node<E> node2 = findNode(element2);
        // 只要有Null直接返回
        if(node1 == null || node2 == null){
            return;
        }
        // 元素相等直接返回
        if(Objects.equals(node1.element,node2.element)){
            return;
        }
        // 比较谁的rank大，小的一方加入大的一方
        if(node1.rank > node2.rank){
            node2.parent = node1;
        }else if(node2.rank > node1.rank){
            node1.parent = node2;
        }else{
            node1.parent = node2;
            // rank相等，node1加入node2，并让node2的rank加1
            node2.rank += 1;
        }
    }
}
