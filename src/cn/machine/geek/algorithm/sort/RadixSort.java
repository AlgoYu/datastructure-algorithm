package cn.machine.geek.algorithm.sort;

/**
 * @Author: MachineGeek
 * @Description: 基数排序（非负数值版）
 * @Email: 794763733@qq.com
 * @Date: 2021/2/24
 */
public class RadixSort {
    public void sort(Integer[] elements) {
        if (elements == null || elements.length < 2) {
            return;
        }
        // 找出最大数
        int max = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
        }
        for (int i = 1; i <= max; i *= 10) {
            countingSort(elements, i);
        }
    }

    private void countingSort(Integer[] elements, int divider) {
        int[] counts = new int[10];
        // 统计每个整数出现的个数
        for (int i = 0; i < elements.length; i++) {
            counts[elements[i] / divider % 10]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        // 从后往前遍历元素，并放置合适的位置
        Integer[] integers = new Integer[elements.length];
        for (int i = elements.length - 1; i >= 0; i--) {
            integers[--counts[elements[i] / divider % 10]] = elements[i];
        }
        // 赋值回去
        for (int i = 0; i < elements.length; i++) {
            elements[i] = integers[i];
        }
    }
}
