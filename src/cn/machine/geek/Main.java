package cn.machine.geek;

import cn.machine.geek.structure.map.TreeMap;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        treeMap.put("haha",123);
        treeMap.put("2",23);
        treeMap.put("123",42);
        treeMap.put("222",21);
        System.out.println(treeMap.put("222",33));
        System.out.println(treeMap.put("222",null));
        System.out.println(treeMap.get("haha"));
        System.out.println(treeMap.get("asdasd"));
        System.out.println(treeMap.containsKey("@13123"));
        System.out.println(treeMap.containsKey("2"));
        System.out.println(treeMap.containsValue(3212));
        System.out.println(treeMap.containsValue(42));
        System.out.println(treeMap.containsValue(null));
        System.out.println(treeMap.containsValue(null));
    }
}
