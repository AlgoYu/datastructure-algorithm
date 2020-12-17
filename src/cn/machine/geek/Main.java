package cn.machine.geek;

import cn.machine.geek.structure.CircleTwoWayLinkedList;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        CircleTwoWayLinkedList<Integer> linkedList = new CircleTwoWayLinkedList<>();
        linkedList.insert(0,0);
        linkedList.insert(1,1);
        linkedList.insert(0,-1);
        linkedList.add(2);
        linkedList.remove(3);
        linkedList.remove(0);
        linkedList.remove(0);
        linkedList.remove(0);
    }
}
