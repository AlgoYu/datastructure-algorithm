package cn.machine.geek;

import cn.machine.geek.algorithm.Josephus;
import cn.machine.geek.structure.queue.CircleQueue;
import cn.machine.geek.structure.queue.LinkedQueue;
import cn.machine.geek.structure.set.LinkedSet;
import cn.machine.geek.structure.stack.LinkedStack;
import cn.machine.geek.structure.stack.Stack;
import cn.machine.geek.structure.tree.AVLTree;
import cn.machine.geek.structure.tree.BinarySearchTree;
import cn.machine.geek.structure.tree.RedBlackTree;
import sun.jvm.hotspot.gc_implementation.parallelScavenge.PSYoungGen;

import java.util.Comparator;
import java.util.Random;
import java.util.function.BinaryOperator;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        LinkedSet<Integer> linkedSet = new LinkedSet<>();
        linkedSet.remove(1);
        linkedSet.add(2);
        linkedSet.add(3);
        linkedSet.add(3);
        linkedSet.add(2);
        System.out.println(linkedSet.contains(5));
        System.out.println(linkedSet.contains(2));
        linkedSet.remove(2);
        linkedSet.remove(3);
        linkedSet.add(5);
        linkedSet.add(5);
        linkedSet.add(6);
        linkedSet.traversal(new LinkedSet.Visitor<Integer>() {
            @Override
            protected boolean operate(Integer element) {
                if(element == 5){
                    System.out.println("遍历到"+element);
                    return true;
                }
                return false;
            }
        });
    }
}
