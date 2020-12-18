package cn.machine.geek.structure.stack;

/**
 * @Author: MachineGeek
 * @Description: 链表栈（双向链表）
 * @Email: 794763733@qq.com
 * @Date: 2020/12/18
 */
public class LinkedStack<E> {
    /**
     * @Author: MachineGeek
     * @Description: 内部节点
     * @Date: 2020/12/18
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
    * @Description: 弹出元素
    * @Date: 2020/12/18
     * @param
    * @Return: E
    */
    public E pop(){
        if(null == last){
            throw new RuntimeException("范围越界");
        }
        E element = last.element;
        last = last.prev;
        size--;
        return element;
    }

    /**
     * @Author: MachineGeek
     * @Description: 边界检查
     * @Date: 2020/12/18
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
     * @Description: 返回元素数量
     * @Date: 2020/12/18
     * @param
     * @Return: int
     */
    public int size(){
        return size;
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加元素
    * @Date: 2020/12/18
     * @param element
    * @Return: void
    */
    public void push(E element){
        if(size == 0){
            first = new Node<>(element,null,null);
            last = first;
        }else{
            last.next = new Node<>(element,last,null);
            last = last.next;
        }
        size++;
    }

    /**
    * @Author: MachineGeek
    * @Description: 是否为空
    * @Date: 2020/12/14
     * @param
    * @Return: boolean
    */
    public boolean isEmpty(){
        return size == 0;
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
