package cn.machine.geek.structure.heap;

import java.time.Year;
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
        checkRange();
        E temp = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        siftDown(0);
        return temp;
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
        // 如果index大于0则代表有父节点
        while (index > 0){
            // 找到父节点，如果父节点比当前节点大，则退出循环。
            int parentIndex = (index - 1) >> 1;
            if(compare(temp,elements[parentIndex]) <= 0){
                break;
            }
            // 让父节点覆盖当前节点
            elements[index] = elements[parentIndex];
            // 去父节点的索引再次循环
            index = parentIndex;
        }
        elements[index] = temp;
    }

    /**
     * @Author: MachineGeek
     * @Description: 让index位置的元素下滤
     * @Date: 2021/2/20
     * @param index
     * @Return: void
     */
    private void siftDown(int index){
        E temp = elements[index];
        // 如果index小于floor(size/2)，则说明存在子节点。
        int half = size >> 1;
        while (index < half){
            // 左子节点
            int childIndex = (index << 1) + 1;
            // 如果右子节点存在，并且比左子节点大，则取右子节点。
            if(childIndex + 1 < size && compare(elements[childIndex],elements[childIndex + 1]) < 0){
                childIndex = childIndex+1;
            }
            // 如果index大于子节点则退出循环
            if(compare(temp,elements[childIndex]) >= 0){
                break;
            }
            // 叶子节点覆盖当前节点
            elements[index] = elements[childIndex];
            // 进入右子节点循环
            index = childIndex;
        }
        // 找到最后的索引放入值
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
