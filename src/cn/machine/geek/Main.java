package cn.machine.geek;

import cn.machine.geek.structure.heap.BinaryHeap;
import cn.machine.geek.structure.queue.PriorityQueue;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.enQueue(19);
        priorityQueue.enQueue(42);
        priorityQueue.enQueue(345);
        priorityQueue.enQueue(55);
        priorityQueue.enQueue(10);
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
    }
}
