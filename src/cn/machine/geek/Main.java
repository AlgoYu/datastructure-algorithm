package cn.machine.geek;

import cn.machine.geek.structure.LinkedList;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.insert(4,"0");
        linkedList.modify(0,"jaisd");
        System.out.println(linkedList.get(4));
        System.out.println(linkedList.indexOf("4"));
        System.out.println( linkedList.remove(3));
        System.out.println(linkedList.size());
    }
}
