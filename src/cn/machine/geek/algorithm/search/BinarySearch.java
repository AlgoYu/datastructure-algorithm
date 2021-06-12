package cn.machine.geek.algorithm.search;

import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 二分搜索
 * @Email: 794763733@qq.com
 * @Date: 2021/2/23
 */
public class BinarySearch<E> {
    public int search(E[] elements, E element, Comparator<E> comparator) {
        if (elements == null || comparator == null || elements.length == 0) {
            return -1;
        }
        // 初始化左右边界
        int left = 0, right = elements.length;
        // 循环查找排除另一半
        while (left < right) {
            // 获得中间索引下标
            int mid = (left + right) >> 1;
            // 比较
            int value = comparator.compare(element, elements[mid]);
            // 如果要查找的元素比中间元素大，排除左边一半。
            if (value > 0) {
                left = mid + 1;
                // 如果要查找的元素比中间元素小，排除左边一半。
            } else if (value < 0) {
                right = mid - 1;
                // 元素相等，返回中间索引下标
            } else {
                return mid;
            }
        }
        // 找不到就返回负数
        return -1;
    }
}
