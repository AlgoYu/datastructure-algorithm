package cn.machine.geek.algorithm.sort;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 桶排序（数值版）
 * @Email: 794763733@qq.com
 * @Date: 2021/2/24
 */
public class BucketSort {
    public void sort(Double[] elements){
        if(elements == null || elements.length < 2){
            return;
        }
        // 创建桶数组
        List<Double>[] buckets = new List[elements.length];
        // 遍历数组放入桶中
        for (int i = 0; i < elements.length; i++){
            int bucketIndex = (int) (elements[i] * elements.length);
            if(buckets[bucketIndex] == null){
                buckets[bucketIndex] = new LinkedList<>();
            }
            buckets[bucketIndex].add(elements[i]);
        }
        // 遍历桶
        int index = 0;
        for (int i = 0; i < buckets.length; i++){
            if(buckets[i] == null){
                continue;
            }
            buckets[i].sort(null);
            for (Double element : buckets[i]){
                elements[index++] = element;
            }
        }
    }
}
