package cn.machine.geek;

import cn.machine.geek.algorithm.Josephus;
import cn.machine.geek.structure.queue.CircleQueue;
import cn.machine.geek.structure.queue.LinkedQueue;
import cn.machine.geek.structure.stack.LinkedStack;
import cn.machine.geek.structure.stack.Stack;
import cn.machine.geek.structure.tree.AVLTree;
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
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.add(5);
        avlTree.add(6);
        avlTree.add(0);
        avlTree.add(10);
        avlTree.remove(0);
    }
}
