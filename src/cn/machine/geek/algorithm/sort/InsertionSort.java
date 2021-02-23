package cn.machine.geek.algorithm.sort;

import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 插入排序
 * @Email: 794763733@qq.com
 * @Date: 2021/2/23
 */
public class InsertionSort<E> {
    public void sort(E[] elements, Comparator<E> comparator){
        if(elements == null || comparator == null || elements.length < 2){
            return;
        }
        // 遍历数组
        for (int i = 1; i < elements.length; i++){
            // 记录当前元素
            int index = i;
            E temp = elements[index];
            // 与前面的元素进行比较，如果前面的元素比较大，就直接拿前面的元素覆盖当前位置
            while (index > 0 && comparator.compare(temp,elements[index - 1]) < 0){
                elements[index] = elements[index - 1];
                index = index - 1;
            }
            // 最后把元素放入合适的位置
            elements[index] = temp;
        }
    }
}
