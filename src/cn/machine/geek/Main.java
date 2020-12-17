package cn.machine.geek;

import cn.machine.geek.algorithm.Josephus;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        Josephus<Integer> josephus = new Josephus<>();
        josephus.add(1);
        josephus.add(2);
        josephus.add(3);
        josephus.add(4);
        josephus.josephusProblem(4,3);
    }
}
