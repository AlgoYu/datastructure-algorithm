package cn.machine.geek.algorithm.sort;

import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 插入排序
 * @Email: 794763733@qq.com
 * @Date: 2021/2/23
 */
public class InsertionSort<E> {
    public void sort(E[] elements, Comparator<E> comparator) {
        if (elements == null || comparator == null || elements.length < 2) {
            return;
        }
        // 遍历数组
        for (int i = 1; i < elements.length; i++) {
            E temp = elements[i];
            int index = search(elements, temp, i, comparator);
            for (int j = i; j > index; j--) {
                elements[j] = elements[j - 1];
            }
            elements[index] = temp;
        }
    }

    /**
     * @param elements
     * @param element
     * @param comparator
     * @Author: MachineGeek
     * @Description: 利用二分搜索优化
     * @Date: 2021/2/23
     * @Return: int
     */
    private int search(E[] elements, E element, int length, Comparator<E> comparator) {
        // 从有序部分中搜索合适的位置
        int left = 0, right = length;
        // 只要left与right相等，位置就找到了
        while (left < right) {
            int mid = (left + right) >> 1;
            // 只要元素大于这个中位值就一直排除左边一半
            if (comparator.compare(element, elements[mid]) > 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
