package cn.machine.geek;

import cn.machine.geek.structure.map.HashMap;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        HashMap<Object,Integer> map = new HashMap<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 70000; i++){
            map.put(i,i * 10);
        }
        for (int i = 0; i < 70000; i++){
            map.remove(i);
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(map.size());

        System.out.println(map.get(2));
    }
}
