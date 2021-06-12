package cn.machine.geek.structure.stack;

/**
 * @Author: MachineGeek
 * @Description: 链表栈（双向链表）
 * @Email: 794763733@qq.com
 * @Date: 2020/12/18
 */
public class LinkedStack<E> {
    /**
     * @param
     * @Author: MachineGeek
     * @Description: 内部节点
     * @Date: 2020/12/18
     * @Return:
     */
    public class Node<E> {
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
     * @param
     * @Author: MachineGeek
     * @Description: 弹出元素
     * @Date: 2020/12/18
     * @Return: E
     */
    public E pop() {
        if (null == last) {
            throw new RuntimeException("范围越界");
        }
        E element = last.element;
        if (last == first) {
            first = null;
        }
        last = last.prev;
        size--;
        return element;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 返回元素数量
     * @Date: 2020/12/18
     * @Return: int
     */
    public int size() {
        return size;
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 增加元素
     * @Date: 2020/12/18
     * @Return: void
     */
    public void push(E element) {
        if (size == 0) {
            first = new Node<>(element, null, null);
            last = first;
        } else {
            last.next = new Node<>(element, last, null);
            last = last.next;
        }
        size++;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 是否为空
     * @Date: 2020/12/18
     * @Return: boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
