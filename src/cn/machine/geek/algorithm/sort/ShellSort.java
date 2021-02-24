package cn.machine.geek.algorithm.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 希尔排序
 * @Email: 794763733@qq.com
 * @Date: 2021/2/24
 */
public class ShellSort<E> {
    public void sort(E[] elements, Comparator<E> comparator){
        if(elements == null || comparator == null || elements.length < 2){
            return;
        }
        List<Integer> steps = sedgewickStepSequence(elements.length);
        for (Integer step : steps){
            stepSort(elements,step,comparator);
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 根据数组长度获得步长序列
    * @Date: 2021/2/24
     * @param length
    * @Return: java.util.List<java.lang.Integer>
    */
    private List<Integer> sedgewickStepSequence(int length){
        List<Integer> stepSequence = new LinkedList<>();
        int k = 0, step = 0;
        while (true){
            if(k % 2 == 0){
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            }else{
                int pow1 = (int) Math.pow(2,(k - 1) >> 1);
                int pow2 = (int) Math.pow(2,(k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if(step >= length){
                break;
            }
            stepSequence.add(0,step);
            k++;
        }
        return stepSequence;
    }

    /**
    * @Author: MachineGeek
    * @Description: 将数组分为num列进行列排序
    * @Date: 2021/2/24
     * @param elements
     * @param col
     * @param comparator
    * @Return: void
    */
    private void stepSort(E[] elements, int col, Comparator<E> comparator){
        // 遍历列
        for (int i = 0; i < col; i++){
            // 比较列元素，如果比自己大就交换位置
            for (int j = i + col; j < elements.length; j += col){
                int index = j;
                while (index >= col && comparator.compare(elements[index],elements[index - col]) < 0){
                    E temp = elements[index];
                    elements[index] = elements[index - col];
                    elements[index - col] = temp;
                    index -= col;
                }
            }
        }
    }
}
