package cn.machine.geek;

import cn.machine.geek.structure.graph.Graph;
import cn.machine.geek.structure.unionfind.UnionFind;
import cn.machine.geek.structure.unionfind.UnionFindSize;


/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class Main {
    public static void main(String[] args) {
        Graph<String,Integer> graph = new Graph<>();
        graph.addEdge("V1","V2",3);
        graph.addEdge("V2","V1",5);
        graph.removeEdge("V1","V2");
        graph.removeEdge("V2","V1");
        graph.addVertex("V3");
        graph.addEdge("V1","V3",10);
        graph.addEdge("V1","V3",11);
    }
}
