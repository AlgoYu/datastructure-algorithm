package cn.machine.geek.structure.linkedlist;

/**
 * @Author: MachineGeek
 * @Description: 双向循环链表
 * @Email: 794763733@qq.com
 * @Date: 2020/12/17
 */
public class CircleTwoWayLinkedList<E> {
    /**
     * @Author: MachineGeek
     * @Description: 内部节点
     * @Date: 2020/12/17
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

    // 头节点
    private Node<E> first;
    private Node<E> last;
    // 元素数量
    private int size;

    /**
     * @Author: MachineGeek
     * @Description: 获取元素
     * @Date: 2020/12/17
     * @param index
     * @Return: E
     */
    public E get(int index){
        return getNode(index).element;
    }

    /**
     * @Author: MachineGeek
     * @Description: 边界检查
     * @Date: 2020/12/17
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
    * @Date: 2020/12/17
     * @param index
    * @Return: cn.machine.geek.structure.linkedlist.TwoWayLinkedList<E>.Node<E>
    */
    public Node<E> getNode(int index){
        checkRange(index);
        Node<E> temp;
        int count;
        if(index > (size >> 1)){
            temp = last;
            count = index - 1;
            while (count > index){
                temp = temp.prev;
                count--;
            }
        }else{
            temp = first;
            count = 0;
            while (count < index){
                temp = temp.next;
                count++;
            }
        }
        return temp;
    }

    /**
     * @Author: MachineGeek
     * @Description: 返回元素数量
     * @Date: 2020/12/17
     * @param
     * @Return: int
     */
    public int size(){
        return size;
    }

    /**
     * @Author: MachineGeek
     * @Description: 增加元素
     * @Date: 2020/12/17
     * @param element
     * @Return: void
     */
    public void add(E element){
        insert(size,element);
    }

    /**
     * @Author: MachineGeek
     * @Description: 插入元素
     * @Date: 2020/12/17
     * @param index
     * @param element
     * @Return: void
     */
    public void insert(int index,E element){
        if(index < 0 || index > size){
            throw new RuntimeException("范围越界");
        }
        if(index == size){
            Node<E> temp = last;
            last = new Node<>(element,temp,first);
            if(null != last.prev){
                last.prev.next = last;
                first.prev = last;
            }else{
                first = last;
                first.prev = last;
                last.next = first;
            }
        }else{
            Node<E> node = getNode(index);
            Node<E> temp = new Node<>(element,node.prev,node);
            node.prev = temp;
            if(last == temp.prev){
                first = temp;
                last.next = first;
            }else{
                temp.prev.next = temp;
            }
        }
        size++;
    }

    /**
     * @Author: MachineGeek
     * @Description: 是否为空
     * @Date: 2020/12/17
     * @param
     * @Return: boolean
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * @Author: MachineGeek
     * @Description: 删除节点
     * @Date: 2020/12/17
     * @param index
     * @Return: void
     */
    public void remove(int index){
        Node<E> node = getNode(index);
        if(size == 1){
            first = null;
            last = null;
        }else{
            node.prev.next = node.next;
            node.next.prev = node.prev;
            if(first == node){
                first = node.next;
            }
            if(last == node){
                last = node.prev;
            }
        }
        size--;
    }

    /**
     * @Author: MachineGeek
     * @Description: 修改元素
     * @Date: 2020/12/14
     * @param index
     * @param element
     * @Return: E
     */
    public E modify(int index,E element){
        Node<E> node = getNode(index);
        node.element = element;
        return node.element;
    }

    /**
     * @Author: MachineGeek
     * @Description: 查找元素
     * @Date: 2020/12/14
     * @param element
     * @Return: int
     */
    public int indexOf(E element){
        Node<E> temp = first;
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
