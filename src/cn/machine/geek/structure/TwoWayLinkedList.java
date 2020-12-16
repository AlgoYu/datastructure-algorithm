package cn.machine.geek.structure;

/**
 * @Author: MachineGeek
 * @Description: 双向链表
 * @Email: 794763733@qq.com
 * @Date: 2020/12/14
 */
public class TwoWayLinkedList<E> {
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

    // 头节点
    private Node<E> first;
    private Node<E> last;
    // 元素数量
    private int size;

    /**
     * @Author: MachineGeek
     * @Description: 获取元素
     * @Date: 2020/12/14
     * @param index
     * @Return: E
     */
    public E get(int index){
        return getNode(index).element;
    }

    /**
     * @Author: MachineGeek
     * @Description: 边界检查
     * @Date: 2020/12/14
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
    * @Date: 2020/12/14
     * @param index
    * @Return: cn.machine.geek.structure.TwoWayLinkedList<E>.Node<E>
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
     * @Date: 2020/12/14
     * @param
     * @Return: int
     */
    public int size(){
        return size;
    }

    /**
     * @Author: MachineGeek
     * @Description: 增加元素
     * @Date: 2020/12/14
     * @param element
     * @Return: void
     */
    public void add(E element){
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
     * @Description: 插入元素
     * @Date: 2020/12/14
     * @param index
     * @param element
     * @Return: void
     */
    public void insert(int index,E element){
        if(index < 0 || index > size){
            throw new RuntimeException("范围越界");
        }
        if(index == size){
            last.next = new Node<>(element,last,null);
        }else{
            Node<E> node = getNode(index);
            Node<E> temp = new Node<>(element,node.prev,node);
            node.prev = temp;
            if(null == temp.prev){
                first = temp;
            }else{
                temp.prev.next = temp;
            }
        }
        size++;
    }

    /**
     * @Author: MachineGeek
     * @Description: 删除节点
     * @Date: 2020/12/14
     * @param index
     * @Return: void
     */
    public void remove(int index){
        Node<E> node = getNode(index);
        if(null == node.prev){
            node.next.prev = null;
            first = node.next;
        }else if(null == node.next){
            node.prev.next = null;
            last = node.prev;
        }else{
            node.next.prev = node.prev;
            node.prev.next = node.next;
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
