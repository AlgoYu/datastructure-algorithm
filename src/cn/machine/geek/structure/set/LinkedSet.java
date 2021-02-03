package cn.machine.geek.structure.set;

/**
 * @Author: MachineGeek
 * @Description: 集合（双链表版）
 * @Email: 794763733@qq.com
 * @Date: 2021/2/3
 */
public class LinkedSet<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    /**
     * @Author: MachineGeek
     * @Description: 内部节点
     * @Date: 2020/12/14
     * @param
     * @Return:
     */
    public class Node<E>{
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 遍历操作抽象类
     * @Date: 2020/12/28
     * @Return:
     */
    public static abstract class Visitor<E>{
        boolean stop;
        protected abstract boolean operate(E element);
    }

    /**
    * @Author: MachineGeek
    * @Description: 返回长度
    * @Date: 2021/2/3
     * @param
    * @Return: int
    */
    public int size(){
        return size;
    }

    /**
    * @Author: MachineGeek
    * @Description: 是否为空
    * @Date: 2021/2/3
     * @param
    * @Return: boolean
    */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 清空集合
    * @Date: 2021/2/3
     * @param
    * @Return: void
    */
    public void clear(){
        first = null;
        last = null;
        size = 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 是否包含这个节点
    * @Date: 2021/2/3
     * @param element
    * @Return: boolean
    */
    public boolean contains(E element){
        return getNode(element) != null;
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取节点
    * @Date: 2021/2/3
     * @param element
    * @Return: cn.machine.geek.structure.set.LinkedSet<E>.Node<E>
    */
    public Node<E> getNode(E element){
        Node<E> temp = first;
        while (temp != null){
            if(temp.element.equals(element)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加节点
    * @Date: 2021/2/3
     * @param element
    * @Return: void
    */
    public void add(E element){
        if(size == 0){
            first = new Node<>(element,null,null);
            last = first;
            size++;
            return;
        }
        Node<E> node = getNode(element);
        if(node == null){
            last = new Node<>(element,last,null);
            last.prev.next = last;
            size++;
        }else{
            node.element = element;
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 删除节点
     * @Date: 2020/12/14
     * @param element
     * @Return: void
     */
    public void remove(E element){
        Node<E> node = getNode(element);
        if(node != null){
            if(node.prev != null){
                node.prev.next = node.next;
            }else{
                first = node.next;
            }
            if(node.next != null){
                node.next.prev = node.prev;
            }else{
                last = node.prev;
            }
            size--;
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 遍历接口
    * @Date: 2021/2/3
     * @param visitor
    * @Return: void
    */
    public void traversal(Visitor<E> visitor){
        if(size == 0){
            return;
        }
        Node<E> temp = first;
        while (temp != null && !visitor.stop){
            visitor.operate(temp.element);
            temp = temp.next;
        }
    }
}