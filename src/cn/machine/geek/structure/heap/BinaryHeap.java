package cn.machine.geek.structure.heap;

import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 二叉堆
 * @Email: 794763733@qq.com
 * @Date: 2021/2/20
 */
public class BinaryHeap<E> {
    private E[] elements;
    private int size;
    private static final int CAPACITY = 10;
    private Comparator<E> comparator;

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[CAPACITY];
    }

    public BinaryHeap() {
        this(null);
    }

    /**
    * @Author: MachineGeek
    * @Description: 返回元素
    * @Date: 2021/2/20
     * @param
    * @Return: int
    */
    public int size(){
        return size;
    }

    /**
    * @Author: MachineGeek
    * @Description: 是否为空
    * @Date: 2021/2/20
     * @param
    * @Return: boolean
    */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 清空二叉堆
    * @Date: 2021/2/20
     * @param
    * @Return: void
    */
    public void clear(){
        for (int i = 0; i < size; i++){
            elements[i] = null;
        }
        size = 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加元素
    * @Date: 2021/2/20
     * @param element
    * @Return: void
    */
    public void add(E element){
        if(element == null){
            throw new RuntimeException("Element is not be null!");
        }
        resize(size + 1);
        elements[size] = element;
        siftUp(size);
        size++;
    }

    public E get(){
        checkRange();
        return elements[0];
    }

    public E remove(){
        return null;
    }

    /**
    * @Author: MachineGeek
    * @Description: 让index位置的元素上滤
    * @Date: 2021/2/20
     * @param index
    * @Return: void
    */
    private void siftUp(int index){
        E temp = elements[index];
        while (index > 0){
            int parentIndex = (index - 1) >> 1;
            if(compare(temp,elements[parentIndex]) <= 0){
                break;
            }
            elements[index] = elements[parentIndex];
            index = parentIndex;
        }
        elements[index] = temp;
    }

    /**
    * @Author: MachineGeek
    * @Description: 检查值域
    * @Date: 2021/2/20
     * @param
    * @Return: void
    */
    private void checkRange(){
        if(size == 0){
            throw new RuntimeException("Heap is empty!");
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 扩容方法
     * @Date: 2020/12/2
     * @param
     * @Return: void
     */
    private void resize(int capacity){
        if(capacity > elements.length){
            E[] temp = (E[]) new Object[elements.length + (elements.length >> 1)];
            for (int i = 0; i < size; i++){
                temp[i] = elements[i];
            }
            elements = temp;
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 比较元素
    * @Date: 2021/2/20
     * @param element1
     * @param element2
    * @Return: int
    */
    private int compare(E element1,E element2){
        if(comparator != null){
            return comparator.compare(element1,element2);
        }
        return ((Comparable)element1).compareTo(element2);
    }
}
