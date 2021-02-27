package cn.machine.geek;

import cn.machine.geek.structure.filter.BloomFilter;
import cn.machine.geek.structure.graph.Graph;
import cn.machine.geek.structure.unionfind.UnionFind;
import cn.machine.geek.structure.unionfind.UnionFindSize;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        BloomFilter<Integer> integerBloomFilter = new BloomFilter<>(1000000,0.01);
        integerBloomFilter.put(5);
        integerBloomFilter.put(8);
        integerBloomFilter.put(10);
        System.out.println(integerBloomFilter.contains(5));
        System.out.println(integerBloomFilter.contains(23));
        System.out.println(integerBloomFilter.contains(89));
    }
}
