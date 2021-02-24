package cn.machine.geek.algorithm.sort;

import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 快速排序
 * @Email: 794763733@qq.com
 * @Date: 2021/2/24
 */
public class QuickSort<E> {
    public void sort(E[] elements, Comparator<E> comparator){
        if(elements == null || comparator == null || elements.length < 2){
            return;
        }
        pivot(elements,0,elements.length,comparator);
    }

    /**
    * @Author: MachineGeek
    * @Description: 制造轴点
    * @Date: 2021/2/24
     * @param elements
     * @param left
     * @param right
     * @param comparator
    * @Return: void
    */
    private void pivot(E[] elements, int left, int right, Comparator<E> comparator){
        if(right - left < 2){
            return;
        }
        // 找到轴点
        int index = index(elements, left, right, comparator);
        // 递归左边
        pivot(elements,left,index,comparator);
        // 递归右边
        pivot(elements,index + 1,right,comparator);
    }

    /**
    * @Author: MachineGeek
    * @Description: 选一个数作为轴点，把比它大的值放到数组的右边，把比它小的值放到数组的左边。
    * @Date: 2021/2/24
     * @param elements
     * @param left
     * @param right
     * @param comparator
    * @Return: int
    */
    private int index(E[] elements,int left,int right,Comparator<E> comparator){
        // 获取一个随机数作为轴点
        int mid = (int) (left + (Math.random() * (right - left)));
        // 交换与左边第一个元素的位置
        E temp = elements[mid];
        elements[mid] = elements[left];
        elements[left] = temp;
        // 使右边指向第一个元素
        right--;
        // 左右指针只要不相撞就循环
        while (left < right){
            // 从右往左扫描，小于轴点的值直接覆盖左边，同时自己也成为一个垃圾值。
            while (left < right){
                if(comparator.compare(temp,elements[right]) < 0){
                    right--;
                }else{
                    elements[left++] = elements[right];
                    break;
                }
            }
            // 从右往左扫描，大于轴点的值直接覆盖右边，同时自己也成为一个垃圾值。
            while (left < right){
                if(comparator.compare(temp,elements[left]) > 0){
                    left++;
                }else {
                    elements[right--] = elements[left];
                    break;
                }
            }
        }
        // 最后把轴点放在合适的位置上，左边都是小于它的元素，右边都是大于它的元素。
        elements[left] = temp;
        return left;
    }
}
