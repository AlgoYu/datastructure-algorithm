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
        graph.addEdge("V1","V4",5);
        graph.addEdge("V2","V5",5);
        graph.addEdge("V5","V8",5);
        graph.addEdge("V8","V1",5);
        graph.depthFirstSearch("V1", new Graph.Visitor<String>() {
            @Override
            protected boolean operate(String value) {
                System.out.println(value);
                return false;
            }
        });
    }
}
