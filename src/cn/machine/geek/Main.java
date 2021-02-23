package cn.machine.geek;

import cn.machine.geek.algorithm.sort.BubbleSort;
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
        Integer[] elements = new Integer[]{3,4,1,4,5,6,1,10};
        new BubbleSort<Integer>().sort(elements, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        long time = System.currentTimeMillis();
        System.out.println(Arrays.toString(elements));
        System.out.println(System.currentTimeMillis() - time);
    }
}
