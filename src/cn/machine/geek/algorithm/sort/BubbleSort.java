package cn.machine.geek.algorithm.sort;

import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 冒泡排序
 * @Email: 794763733@qq.com
 * @Date: 2021/2/23
 */
public class BubbleSort<E> {
    public void sort(E[] elements, Comparator<E> comparator){
        if(elements == null || comparator == null || elements.length < 2){
            return;
        }
        for (int i = elements.length - 1; i > 0; i--){
            // 记录最后一次交换值
            int lastSwap = 1;
            // 遍历
            for (int j = 1; j <= i; j++){
                // 比较相邻交换
                if(comparator.compare(elements[j],elements[j-1]) < 0){
                    E temp = elements[j];
                    elements[j] = elements[j-1];
                    elements[j-1] = temp;
                    // 记录最后一次的交换位置
                    lastSwap = j;
                }
            }
            // 赋值最后一次的交换位置，如若没有交换，则下次判断为空会退出循环
            i = lastSwap;
        }
    }
}
