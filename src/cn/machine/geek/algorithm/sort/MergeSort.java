package cn.machine.geek.algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 归并排序
 * @Email: 794763733@qq.com
 * @Date: 2021/2/23
 */
public class MergeSort<E> {
    public void sort(E[] elements, Comparator<E> comparator) {
        if (elements == null || comparator == null || elements.length < 2) {
            return;
        }
        divide(elements, 0, elements.length, comparator);
    }

    /**
     * @param elements
     * @param left
     * @param right
     * @param comparator
     * @Author: MachineGeek
     * @Description: 分割数组
     * @Date: 2021/2/23
     * @Return: void
     */
    private void divide(E[] elements, int left, int right, Comparator<E> comparator) {
        if (right - left < 2) {
            return;
        }
        int mid = (left + right) >> 1;
        // 从中间位置切割左边和右边
        divide(elements, left, mid, comparator);
        divide(elements, mid, right, comparator);
        // 合并左边和右边
        merge(elements, left, right, mid, comparator);
    }

    /**
     * @param elements
     * @param left
     * @param right
     * @param mid
     * @param comparator
     * @Author: MachineGeek
     * @Description: 合并数组
     * @Date: 2021/2/23
     * @Return: void
     */
    private void merge(E[] elements, int left, int right, int mid, Comparator<E> comparator) {
        // 备份左边数组
        int leftLength = mid - left;
        E[] leftArray = (E[]) new Object[leftLength];
        // 这里要注意备份数组的下标和数组元素原始的下标的对应关系
        for (int i = 0; i < leftLength; i++) {
            leftArray[i] = elements[left + i];
        }
        // 有序排序元素下标
        int index = left;
        // 左边数组下标
        int leftIndex = 0;
        // 右边数组下标
        int rightIndex = mid;
        // 左边数组没有结束
        while (leftIndex < leftLength) {
            if (rightIndex < right && comparator.compare(elements[rightIndex], leftArray[leftIndex]) < 0) {
                elements[index++] = elements[rightIndex++];
            } else {
                elements[index++] = leftArray[leftIndex++];
            }
        }
    }
}
