package cn.machine.geek;

import cn.machine.geek.structure.graph.Graph;
import cn.machine.geek.structure.skiplist.SkipList;
import cn.machine.geek.structure.unionfind.UnionFind;
import cn.machine.geek.structure.unionfind.UnionFindSize;

import java.util.Set;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        SkipList<String,Integer> skipList = new SkipList<>();
        skipList.put("qwe",2);
        skipList.put("qwe1",4);
        skipList.put("qwe",5);
        skipList.put("qwea",2);
        System.out.println(skipList.remove("qwe"));
        System.out.println(skipList.remove("qwe1"));
        System.out.println(skipList.remove("qwe"));
        System.out.println(skipList.size());
    }
}
