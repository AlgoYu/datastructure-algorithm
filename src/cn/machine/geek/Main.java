package cn.machine.geek;

import cn.machine.geek.algorithm.search.BinarySearch;
import cn.machine.geek.algorithm.search.KMP;

import java.util.Comparator;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        BinarySearch<Integer> binarySearch = new BinarySearch<>();
        int search = binarySearch.search(new Integer[]{1}, 2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(search);
    }
}
