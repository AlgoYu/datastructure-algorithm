package cn.machine.geek.structure;

/**
 * @Author: MachineGeek
 * @Description: 链表-带虚拟头结点
 * @Email: 794763733@qq.com
 * @Date: 2020/12/8
 */
public class HeadLinkedList<E> {
    /**
    * @Author: MachineGeek
    * @Description: 内部节点
    * @Date: 2020/12/8
    * @Return:
    */
    public class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
    // 头节点
    private Node<E> head;
    // 元素数量
    private int size;

    /*
    * @Author: MachineGeek
    * @Description: 构造函数
    * @Date: 2020/12/8
     * @param
    * @Return:
    */
    public HeadLinkedList(){
        head = new Node<>(null,null);
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取元素
    * @Date: 2020/12/8
     * @param index
    * @Return: E
    */
    public E get(int index){
        return getNode(index).element;
    }

    /**
    * @Author: MachineGeek
    * @Description: 边界检查
    * @Date: 2020/12/8
     * @param index
    * @Return: void
    */
    private void checkRange(int index){
        if(index < 0 || index >= size){
            throw new RuntimeException("范围越界");
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取节点
    * @Date: 2020/12/8
     * @param index
    * @Return: cn.machine.geek.structure.HeadLinkedList<E>.Node<E>
    */
    public Node<E> getNode(int index){
        checkRange(index);
        Node<E> temp = head;
        while (index >= 0){
            temp = temp.next;
            index--;
        }
        return temp;
    }

    /**
    * @Author: MachineGeek
    * @Description: 返回元素数量
    * @Date: 2020/12/8
     * @param
    * @Return: int
    */
    public int size(){
        return size;
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加元素
    * @Date: 2020/12/8
     * @param element
    * @Return: void
    */
    public void add(E element){
        insert(size,element);
    }

    /**
    * @Author: MachineGeek
    * @Description: 插入元素
    * @Date: 2020/12/8
     * @param index
     * @param element
    * @Return: void
    */
    public void insert(int index,E element){
        if(index < 0 || index > size){
            throw new RuntimeException("范围越界");
        }
        Node<E> node = new Node<>(element,null);
        if(index == 0){
            node.next = head.next;
            head.next = node;
        }else{
            Node<E> pre = getNode(index - 1);
            node.next = pre.next;
            pre.next = node;
        }
        size++;
    }

    /**
     * @Author: MachineGeek
     * @Description: 是否为空
     * @Date: 2020/12/8
     * @param
     * @Return: boolean
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 删除节点
    * @Date: 2020/12/8
     * @param index
    * @Return: void
    */
    public void remove(int index){
        checkRange(index);
        if(index == 0){
            head.next = head.next.next;
        }else{
            Node<E> pre = getNode(index - 1);
            pre.next = pre.next.next;
        }
        size--;
    }

    /**
    * @Author: MachineGeek
    * @Description: 修改元素
    * @Date: 2020/12/8
     * @param index
     * @param element
    * @Return: E
    */
    public E modify(int index,E element){
        Node<E> node = getNode(index);
        E value = node.element;
        node.element = element;
        return value;
    }

    /**
    * @Author: MachineGeek
    * @Description: 查找元素
    * @Date: 2020/12/8
     * @param element
    * @Return: int
    */
    public int indexOf(E element){
        Node<E> temp = head;
        int index = 0;
        while (temp.next != null){
            if(temp.next.equals(element)){
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1;
    }
}