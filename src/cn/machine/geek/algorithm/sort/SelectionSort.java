package cn.machine.geek.algorithm.sort;

import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 选择排序
 * @Email: 794763733@qq.com
 * @Date: 2021/2/23
 */
public class SelectionSort<E> {
    public void sort(E[] elements, Comparator<E> comparator) {
        if (elements == null || comparator == null || elements.length < 2) {
            return;
        }
        // 外层循环递减作为未排序元素的长度
        for (int i = elements.length - 1; i > 0; i--) {
            // 记录最大元素的下标
            int index = 0;
            // 遍历一遍未排序的元素
            for (int j = 1; j <= i; j++) {
                // 如果元素更大则记录它的位置
                if (comparator.compare(elements[index], elements[j]) <= 0) {
                    index = j;
                }
            }
            // 交换到未排序的最后
            E temp = elements[i];
            elements[i] = elements[index];
            elements[index] = temp;
        }
    }
}
