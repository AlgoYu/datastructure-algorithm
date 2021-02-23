package cn.machine.geek;

import cn.machine.geek.algorithm.search.BinarySearch;
import cn.machine.geek.algorithm.sort.BubbleSort;
import cn.machine.geek.algorithm.sort.HeapSort;
import cn.machine.geek.algorithm.sort.InsertionSort;
import cn.machine.geek.algorithm.sort.SelectionSort;
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
        Integer[] elements = new Integer[]{3,4,4,5,6,1,10,2,9,7,8};
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        new InsertionSort<Integer>().sort(elements, comparator);
        long time = System.currentTimeMillis();
        System.out.println(Arrays.toString(elements));
        System.out.println(System.currentTimeMillis() - time);

        System.out.println(new BinarySearch<Integer>().search(elements,2,comparator));
    }
}
