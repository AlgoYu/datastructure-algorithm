package cn.machine.geek;

import cn.machine.geek.structure.graph.Graph;
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
        Graph<String,Integer> graph = new Graph<>();
        graph.addEdge("0","2",2);
        graph.addEdge("0","4",7);
        graph.addEdge("2","0",2);
        graph.addEdge("2","4",4);
        graph.addEdge("2","1",3);
        graph.addEdge("2","5",3);
        graph.addEdge("2","6",6);
        graph.addEdge("2","4",7);

        Set<Graph.Edge<String, Integer>> prim = graph.prim();
        Set<Graph.Edge<String, Integer>> kruskal = graph.kruskal();
    }
}
