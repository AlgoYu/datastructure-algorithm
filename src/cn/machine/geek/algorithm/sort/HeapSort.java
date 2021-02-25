package cn.machine.geek.algorithm.sort;

import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 堆排序
 * @Email: 794763733@qq.com
 * @Date: 2021/2/23
 */
public class HeapSort<E> {
    public void sort(E[] elements, Comparator<E> comparator){
        if(elements == null || comparator == null || elements.length < 2){
            return;
        }
        // 批量建堆
        heapify(elements,comparator);
        // 堆的长度
        int heapLength = elements.length;
        while (heapLength > 1){
            // 把堆顶交换到最后
            E temp = elements[--heapLength];
            elements[heapLength] = elements[0];
            elements[0] = temp;
            // 下滤
            siftDown(elements,heapLength,comparator,0);
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 批量建堆
    * @Date: 2021/2/23
     * @param elements
     * @param comparator
    * @Return: void
    */
    private void heapify(E[] elements, Comparator<E> comparator){
        for (int i = (elements.length >> 1) - 1; i >= 0; i--){
            siftDown(elements,elements.length,comparator,i);
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 下滤
    * @Date: 2021/2/23
     * @param elements
     * @param comparator
     * @param index
    * @Return: void
    */
    private void siftDown(E[] elements,int size,Comparator<E> comparator,int index){
        E temp = elements[index];
        // 如果index小于floor(size/2)，则说明存在子节点。
        int half = size >> 1;
        while (index < half){
            // 左子节点
            int childIndex = (index << 1) + 1;
            // 如果右子节点存在，并且比左子节点大，则取右子节点。
            if(childIndex + 1 < size && comparator.compare(elements[childIndex],elements[childIndex + 1]) < 0){
                childIndex = childIndex+1;
            }
            // 如果index大于子节点则退出循环
            if(comparator.compare(temp,elements[childIndex]) >= 0){
                break;
            }
            // 叶子节点覆盖当前节点
            elements[index] = elements[childIndex];
            // 进入子节点循环
            index = childIndex;
        }
        // 找到最后的索引放入值
        elements[index] = temp;
    }
}
