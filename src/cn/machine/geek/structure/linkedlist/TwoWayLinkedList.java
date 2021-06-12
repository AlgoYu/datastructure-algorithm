package cn.machine.geek.structure.linkedlist;

/**
 * @Author: MachineGeek
 * @Description: 双向链表
 * @Email: 794763733@qq.com
 * @Date: 2020/12/14
 */
public class TwoWayLinkedList<E> {
    /**
     * @param
     * @Author: MachineGeek
     * @Description: 内部节点
     * @Date: 2020/12/14
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
     * @param index
     * @Author: MachineGeek
     * @Description: 获取元素
     * @Date: 2020/12/14
     * @Return: E
     */
    public E get(int index) {
        return getNode(index).element;
    }

    /**
     * @param index
     * @Author: MachineGeek
     * @Description: 边界检查
     * @Date: 2020/12/14
     * @Return: void
     */
    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("范围越界");
        }
    }

    /**
     * @param index
     * @Author: MachineGeek
     * @Description: 获取节点
     * @Date: 2020/12/14
     * @Return: cn.machine.geek.structure.linkedlist.TwoWayLinkedList<E>.Node<E>
     */
    public Node<E> getNode(int index) {
        checkRange(index);
        Node<E> temp;
        int count;
        if (index > (size >> 1)) {
            temp = last;
            count = index - 1;
            while (count > index) {
                temp = temp.prev;
                count--;
            }
        } else {
            temp = first;
            count = 0;
            while (count < index) {
                temp = temp.next;
                count++;
            }
        }
        return temp;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 返回元素数量
     * @Date: 2020/12/14
     * @Return: int
     */
    public int size() {
        return size;
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 增加元素
     * @Date: 2020/12/14
     * @Return: void
     */
    public void add(E element) {
        insert(size, element);
    }

    /**
     * @param index
     * @param element
     * @Author: MachineGeek
     * @Description: 插入元素
     * @Date: 2020/12/14
     * @Return: void
     */
    public void insert(int index, E element) {
        if (index < 0 || index > size) {
            throw new RuntimeException("范围越界");
        }
        if (index == size) {
            Node<E> temp = last;
            last = new Node<>(element, temp, null);
            if (null != last.prev) {
                last.prev.next = last;
            } else {
                first = last;
            }
        } else {
            Node<E> node = getNode(index);
            Node<E> temp = new Node<>(element, node.prev, node);
            node.prev = temp;
            if (null == temp.prev) {
                first = temp;
            } else {
                temp.prev.next = temp;
            }
        }
        size++;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 是否为空
     * @Date: 2020/12/14
     * @Return: boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param index
     * @Author: MachineGeek
     * @Description: 删除节点
     * @Date: 2020/12/14
     * @Return: void
     */
    public void remove(int index) {
        Node<E> node = getNode(index);
        if (null == node.prev) {
            node.next.prev = null;
            first = node.next;
        } else if (null == node.next) {
            node.prev.next = null;
            last = node.prev;
        } else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        size--;
    }

    /**
     * @param index
     * @param element
     * @Author: MachineGeek
     * @Description: 修改元素
     * @Date: 2020/12/14
     * @Return: E
     */
    public E modify(int index, E element) {
        Node<E> node = getNode(index);
        node.element = element;
        return node.element;
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 查找元素
     * @Date: 2020/12/14
     * @Return: int
     */
    public int indexOf(E element) {
        Node<E> temp = first;
        int index = 0;
        while (temp != null) {
            if (temp.element.equals(element)) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 清空链表
     * @Date: 2021/2/3
     * @Return: void
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
}
