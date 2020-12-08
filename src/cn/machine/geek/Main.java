package cn.machine.geek;

import cn.machine.geek.structure.HeadLinkedList;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        HeadLinkedList<Integer> headLinkedList = new HeadLinkedList<>();
        headLinkedList.add(0);
        headLinkedList.insert(0,1);
        headLinkedList.insert(2,5);
        System.out.println(headLinkedList.size());
        System.out.println(headLinkedList.get(0));
        System.out.println(headLinkedList.get(2));
    }
}
