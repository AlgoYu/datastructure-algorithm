package cn.machine.geek;

import cn.machine.geek.algorithm.Josephus;
import cn.machine.geek.structure.queue.CircleQueue;
import cn.machine.geek.structure.queue.LinkedQueue;
import cn.machine.geek.structure.stack.LinkedStack;
import cn.machine.geek.structure.stack.Stack;
import cn.machine.geek.structure.tree.BinarySearchTree;
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
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(5);
        binarySearchTree.add(6);
        binarySearchTree.add(1);
        binarySearchTree.add(2);
        binarySearchTree.add(0);
        binarySearchTree.add(7);
        binarySearchTree.add(8);
        binarySearchTree.remove(8);
        binarySearchTree.remove(6);
        binarySearchTree.remove(1);
        binarySearchTree.remove(5);
        binarySearchTree.remove(0);
        binarySearchTree.remove(2);
        binarySearchTree.remove(7);
    }
}
