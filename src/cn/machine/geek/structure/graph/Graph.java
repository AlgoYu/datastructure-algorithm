package cn.machine.geek.structure.graph;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.*;

/**
 * @Author: MachineGeek
 * @Description: 图结构
 * @Email: 794763733@qq.com
 * @Date: 2021/2/25
 */
public class Graph<V,E> {
    private Map<V,Vertex<V,E>> vertexs;
    private Set<Edge<V,E>> edges;

    /**
    * @Author: MachineGeek
    * @Description: 顶点
    * @Date: 2021/2/25
    * @Return:
    */
    public static class Vertex<V,E>{
        private V value;
        // 入边
        Set<Edge<V,E>> inEdges;
        // 出边
        Set<Edge<V,E>> outEdges;

        public Vertex(V value) {
            this.value = value;
            this.inEdges = new HashSet<>();
            this.outEdges = new HashSet<>();
        }

        @Override
        public boolean equals(Object obj) {
            Vertex<V,E> vertex = (Vertex<V, E>) obj;
            return Objects.equals(value,vertex.value);
        }

        @Override
        public int hashCode() {
            return value == null? 0 : value.hashCode();
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 边
    * @Date: 2021/2/25
    * @Return:
    */
    public static class Edge<V,E>{
        // 起始顶点
        private Vertex<V,E> from;
        // 结束顶点
        private Vertex<V,E> to;
        // 权重
        private E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            Edge<V,E> edge = (Edge<V, E>) obj;
            return Objects.equals(from,edge.from) && Objects.equals(to,edge.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }
    }

    public Graph() {
        this.vertexs = new HashMap<>();
        this.edges = new HashSet<>();
    }

    /**
    * @Author: MachineGeek
    * @Description: 顶点数量
    * @Date: 2021/2/25
     * @param
    * @Return: int
    */
    public int vertexSize(){
        return vertexs.size();
    }

    /**
    * @Author: MachineGeek
    * @Description: 边数量
    * @Date: 2021/2/25
     * @param
    * @Return: int
    */
    public int edgeSize(){
        return edges.size();
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加顶点
    * @Date: 2021/2/25
     * @param v
    * @Return: void
    */
    public void addVertex(V v){
        if(!vertexs.containsKey(v)){
            vertexs.put(v,new Vertex<>(v));
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加边
    * @Date: 2021/2/25
     * @param from
     * @param to
    * @Return: void
    */
    public void addEdge(V from,V to){
        addEdge(from,to,null);
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加边
    * @Date: 2021/2/25
     * @param from
     * @param to
     * @param weight
    * @Return: void
    */
    public void addEdge(V from,V to,E weight){
        // 如果没有这两个顶点，则创建
        Vertex<V, E> fromVertex = vertexs.get(from);
        if(fromVertex == null){
            fromVertex = new Vertex<>(from);
            vertexs.put(from,fromVertex);
        }
        Vertex<V, E> toVertex = vertexs.get(to);
        if(toVertex == null){
            toVertex = new Vertex<>(to);
            vertexs.put(to,toVertex);
        }
        // 添加边
        Edge<V,E> edge = new Edge<>(fromVertex,toVertex,weight);
        // 删除旧边
        if(fromVertex.outEdges.remove(edge)){
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        // 添加新边
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    /**
    * @Author: MachineGeek
    * @Description: 删除顶点
    * @Date: 2021/2/25
     * @param v
    * @Return: void
    */
    public void removeVertex(V v){
        Vertex<V, E> vertex = vertexs.remove(v);
        if(vertex != null){
            // 删除所有出的边
            for (Iterator<Edge<V,E>> outEdges =  vertex.outEdges.iterator(); outEdges.hasNext();){
                Edge<V, E> outEdge = outEdges.next();
                outEdge.to.inEdges.remove(outEdge);
                outEdges.remove();
                edges.remove(outEdge);
            }
            // 删除所有入的边
            for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator();iterator.hasNext();){
                Edge<V, E> inEdge = iterator.next();
                inEdge.from.outEdges.remove(inEdge);
                iterator.remove();
                edges.remove(inEdge);
            }
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 删除边
    * @Date: 2021/2/25
     * @param from
     * @param to
    * @Return: void
    */
    public void removeEdge(V from,V to){
        Vertex<V, E> fromVertex = vertexs.get(from);
        Vertex<V,E> toVertex = vertexs.get(to);
        // 顶点为空直接退出
        if(fromVertex == null || toVertex == null){
            return;
        }
        // 删除边
        Edge<V,E> edge = new Edge<>(fromVertex,toVertex,null);
        if(fromVertex.outEdges.remove(edge)){
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }
}
