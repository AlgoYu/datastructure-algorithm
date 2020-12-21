package cn.machine.geek.structure.queue;

/**
 * @Author: MachineGeek
 * @Description: 链表队列
 * @Email: 794763733@qq.com
 * @Date: 2020/12/21
 */
public class LinkedQueue<E> {
    /**
     * @Author: MachineGeek
     * @Description: 内部节点
     * @Date: 2020/12/21
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
     * @Description: 返回元素数量
     * @Date: 2020/12/21
     * @param
     * @Return: int
     */
    public int size(){
        return size;
    }

    /**
     * @Author: MachineGeek
     * @Description: 入队列
     * @Date: 2020/12/14
     * @param element
     * @Return: void
     */
    public void enqueue(E element){
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
    * @Description: 出队列
    * @Date: 2020/12/21
     * @param
    * @Return: E
    */
    public E dequeue(){
        if(null == first){
            throw new RuntimeException("范围越界");
        }
        E element = first.element;
        if(first == last){
            last = null;
        }
        first = first.next;
        size--;
        return element;
    }
}
