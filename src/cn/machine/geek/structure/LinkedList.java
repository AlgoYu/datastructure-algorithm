package cn.machine.geek.structure;

/**
 * @Author: MachineGeek
 * @Description: 链表
 * @Email: 794763733@qq.com
 * @Date: 2020/12/3
 */
public class LinkedList<E> {
    /**
    * @Author: MachineGeek
    * @Description: 内部节点
    * @Date: 2020/12/3
     * @param
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

    /**
    * @Author: MachineGeek
    * @Description: 获取元素
    * @Date: 2020/12/3
     * @param index
    * @Return: void
    */
    public E get(int index){
        return getNode(index).element;
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取节点
    * @Date: 2020/12/3
     * @param index
    * @Return: E
    */
    public Node<E> getNode(int index){
        checkRange(index);
        Node<E> temp = head;
        while (index > 0){
            temp = temp.next;
            index--;
        }
        return temp;
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加节点
    * @Date: 2020/12/3
     * @param element
    * @Return: void
    */
    public void add(E element){
        Node<E> node = new Node<>(element,null);
        if(head == null){
            head = node;
        }else{
            getNode(size - 1).next = new Node<>(element,null);
        }
        size++;
    }

    /**
    * @Author: MachineGeek
    * @Description: 检查值域
    * @Date: 2020/12/3
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
    * @Description: 插入节点
    * @Date: 2020/12/3
     * @param index
     * @param element
    * @Return: void
    */
    public void insert(int index, E element){
        if(index < 0 || index > size){
            throw new RuntimeException("范围越界");
        }
        Node<E> node = new Node<>(element,null);
        if(index == 0){
            node.next = head;
            head = node;
        }else{
            Node<E> temp = getNode(index - 1);
            node.next = temp.next;
            temp.next = node;
        }
        size++;
    }

    /**
    * @Author: MachineGeek
    * @Description: 删除节点
    * @Date: 2020/12/3
     * @param index
    * @Return: E
    */
    public E remove(int index){
        checkRange(index);
        E element;
        if(index == 0){
            element = head.element;
            head = head.next;
        }else{
            Node<E> node = getNode(index - 1);
            element = node.next.element;
            node.next = node.next.next;
        }
        size--;
        return element;
    }

    /**
    * @Author: MachineGeek
    * @Description: 修改元素
    * @Date: 2020/12/3
     * @param index
     * @param element
    * @Return: E
    */
    public E modify(int index, E element){
        Node<E> node = getNode(index);
        E temp = node.element;
        node.element = element;
        return temp;
    }

    /**
    * @Author: MachineGeek
    * @Description: 元素数量
    * @Date: 2020/12/3
     * @param
    * @Return: int
    */
    public int size(){
        return size;
    }

    /**
    * @Author: MachineGeek
    * @Description: 查找元素
    * @Date: 2020/12/3
     * @param element
    * @Return: int
    */
    public int indexOf(E element){
        Node<E> temp = head;
        int index = 0;
        while (temp != null){
            if(temp.element.equals(element)){
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1;
    }
}
