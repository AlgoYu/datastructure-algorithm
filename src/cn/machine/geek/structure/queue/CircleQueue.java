package cn.machine.geek.structure.queue;

/**
 * @Author: MachineGeek
 * @Description: 循环队列
 * @Email: 794763733@qq.com
 * @Date: 2020/12/22
 */
public class CircleQueue<E> {
    private int head;
    private int size;
    private E[] elements;
    private static final int CAPACITY = 10;

    public CircleQueue(){
        elements = (E[]) new Object[CAPACITY];
    }

    public CircleQueue(int capacity){
        capacity = 0;
        if(capacity > CAPACITY){
            elements = (E[]) new Object[capacity];
        }else{
            elements = (E[]) new Object[CAPACITY];
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 返回元素数量
     * @Date: 2020/12/22
     * @param
     * @Return: int
     */
    public int size(){
        return size;
    }

    /**
     * @Author: MachineGeek
     * @Description: 入队列
     * @Date: 2020/12/22
     * @param element
     * @Return: void
     */
    public void enqueue(E element){
        resize(size + 1);
        elements[( head+size ) % elements.length] = element;
        size++;
    }

    /**
    * @Author: MachineGeek
    * @Description: 扩容
    * @Date: 2020/12/23
     * @param capacity
    * @Return: void
    */
    private void resize(int capacity){
        if(capacity > elements.length){
            E[] temp = (E[]) new Object[elements.length + (elements.length >> 1)];
            int i = 0;
            int n = size;
            while (i < n){
                temp[i] = dequeue();
                i++;
            }
            elements = temp;
            head = 0;
            size = n;
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 是否为空
     * @Date: 2020/12/22
     * @param
     * @Return: boolean
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * @Author: MachineGeek
     * @Description: 出队列
     * @Date: 2020/12/22
     * @param
     * @Return: E
     */
    public E dequeue(){
        if(size == 0){
            throw new RuntimeException("范围越界");
        }
        E element = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return element;
    }
}
