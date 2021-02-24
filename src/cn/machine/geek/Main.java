package cn.machine.geek;

import cn.machine.geek.algorithm.search.BinarySearch;
import cn.machine.geek.algorithm.sort.*;
import cn.machine.geek.structure.heap.BinaryHeap;
import cn.machine.geek.structure.trie.Trie;

import java.util.Arrays;
import java.util.Comparator;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        Integer[] elements = new Integer[]{500,3,4,4,5,6,1,10,2,9,7,8};
        Double[] elements1 = new Double[]{0.3,0.1,0.2,0.3,0.5,0.5,0.5,0.6,0.7,0.8,0.9,0.2};
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        long time = System.currentTimeMillis();
        new BucketSort().sort(elements1);
        System.out.println("耗时：" + (System.currentTimeMillis() - time) + "毫秒");
        System.out.println(Arrays.toString(elements1));
    }
}
