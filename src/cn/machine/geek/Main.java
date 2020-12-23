package cn.machine.geek;

import cn.machine.geek.algorithm.Josephus;
import cn.machine.geek.structure.queue.CircleQueue;
import cn.machine.geek.structure.queue.LinkedQueue;
import cn.machine.geek.structure.stack.LinkedStack;
import cn.machine.geek.structure.stack.Stack;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        CircleQueue<Integer> circleQueue = new CircleQueue<>();
        circleQueue.enqueue(1);
        circleQueue.enqueue(2);
        circleQueue.enqueue(2);
        circleQueue.enqueue(2);
        circleQueue.enqueue(2);
        circleQueue.enqueue(2);
        circleQueue.enqueue(2);
        circleQueue.enqueue(2);
        circleQueue.enqueue(2);
        circleQueue.enqueue(2);
        circleQueue.enqueue(2);
        System.out.print(circleQueue.dequeue());
        System.out.print(circleQueue.dequeue());
        System.out.print(circleQueue.dequeue());
        circleQueue.clear();
    }
}
