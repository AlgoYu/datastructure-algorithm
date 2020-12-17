package cn.machine.geek;

import cn.machine.geek.structure.CircleSingleLinkedList;
import cn.machine.geek.structure.TwoWayLinkedList;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        TwoWayLinkedList<Integer> linkedList = new TwoWayLinkedList<>();
        linkedList.insert(0,0);
        linkedList.insert(1,1);
        linkedList.insert(0,-1);
        linkedList.add(2);
        linkedList.remove(3);
        linkedList.remove(0);
    }
}
