package cn.machine.geek;

import cn.machine.geek.structure.heap.BinaryHeap;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
        binaryHeap.add(5);
        binaryHeap.add(7);
        binaryHeap.add(10);
        binaryHeap.add(2);
        binaryHeap.add(78);
        binaryHeap.add(64);
        System.out.println(binaryHeap.get());
        System.out.println(binaryHeap.remove());
        System.out.println(binaryHeap.remove());
        System.out.println(binaryHeap.remove());
        System.out.println(binaryHeap.remove());
        System.out.println(binaryHeap.remove());
    }
}
