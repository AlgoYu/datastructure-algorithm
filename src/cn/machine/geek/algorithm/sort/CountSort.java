package cn.machine.geek.algorithm.sort;

/**
 * @Author: MachineGeek
 * @Description: 计数排序(Integer数值版)
 * @Email: 794763733@qq.com
 * @Date: 2021/2/24
 */
public class CountSort {
    public void sort(Integer[] elements) {
        if (elements == null || elements.length < 2) {
            return;
        }
        // 找出最大的数和最小的数
        int max = elements[0];
        int min = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
            if (elements[i] < min) {
                min = elements[i];
            }
        }
        // 开辟这个区间的内存空间
        int[] counts = new int[max - min + 1];
        // 统计每个数出现的个数
        for (int i = 0; i < elements.length; i++) {
            counts[elements[i] - min]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        // 从后往前遍历元素，并放置合适的位置
        Integer[] integers = new Integer[elements.length];
        for (int i = elements.length - 1; i >= 0; i--) {
            integers[--counts[elements[i] - min]] = elements[i];
        }
        // 赋值回去
        for (int i = 0; i < elements.length; i++) {
            elements[i] = integers[i];
        }
    }
}
