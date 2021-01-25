package cn.machine.geek;

import cn.machine.geek.algorithm.Josephus;
import cn.machine.geek.structure.queue.CircleQueue;
import cn.machine.geek.structure.queue.LinkedQueue;
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
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
        redBlackTree.add(7);
        redBlackTree.add(1);
        redBlackTree.add(28);
        redBlackTree.add(10);
        redBlackTree.add(4);
        redBlackTree.add(2);
        redBlackTree.add(9);
        redBlackTree.add(6);
        redBlackTree.add(22);
        redBlackTree.add(31);
    }
}
