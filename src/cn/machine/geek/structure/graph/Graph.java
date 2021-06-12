package cn.machine.geek.structure.graph;

import cn.machine.geek.structure.unionfind.UnionFind;

import java.util.*;

/**
 * @Author: MachineGeek
 * @Description: 图结构
 * @Email: 794763733@qq.com
 * @Date: 2021/2/25
 */
public class Graph<V, E> {
    private Map<V, Vertex<V, E>> vertexs;
    private Set<Edge<V, E>> edges;
    private WeightManager<E> weightManager;

    /**
     * @Author: MachineGeek
     * @Description: 顶点
     * @Date: 2021/2/25
     * @Return:
     */
    public static class Vertex<V, E> {
        private V value;
        // 入边
        private Set<Edge<V, E>> inEdges;
        // 出边
        private Set<Edge<V, E>> outEdges;

        public Vertex(V value) {
            this.value = value;
            this.inEdges = new HashSet<>();
            this.outEdges = new HashSet<>();
        }

        @Override
        public boolean equals(Object obj) {
            Vertex<V, E> vertex = (Vertex<V, E>) obj;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 边
     * @Date: 2021/2/25
     * @Return:
     */
    public static class Edge<V, E> {
        // 起始顶点
        private Vertex<V, E> from;
        // 结束顶点
        private Vertex<V, E> to;
        // 权重
        private E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            Edge<V, E> edge = (Edge<V, E>) obj;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 权重管理器
     * @Date: 2021/2/26
     * @Return:
     */
    public interface WeightManager<E> {
        int compare(E e1, E e2);

        E add(E e1, E e2);
    }

    /**
     * @Author: MachineGeek
     * @Description: 路径
     * @Date: 2021/2/26
     * @Return:
     */
    public static class Path<V, E> {
        private E weight;
        private List<Edge<V, E>> paths;

        public Path(E weight) {
            this.weight = weight;
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 遍历器
     * @Date: 2021/2/25
     * @Return:
     */
    public static abstract class Visitor<V> {
        boolean stop;

        protected abstract boolean operate(V value);
    }

    public Graph() {
        this(null);
    }

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
        this.vertexs = new HashMap<>();
        this.edges = new HashSet<>();
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 顶点数量
     * @Date: 2021/2/25
     * @Return: int
     */
    public int vertexSize() {
        return vertexs.size();
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 边数量
     * @Date: 2021/2/25
     * @Return: int
     */
    public int edgeSize() {
        return edges.size();
    }

    /**
     * @param v
     * @Author: MachineGeek
     * @Description: 增加顶点
     * @Date: 2021/2/25
     * @Return: void
     */
    public void addVertex(V v) {
        if (!vertexs.containsKey(v)) {
            vertexs.put(v, new Vertex<>(v));
        }
    }

    /**
     * @param from
     * @param to
     * @Author: MachineGeek
     * @Description: 增加边
     * @Date: 2021/2/25
     * @Return: void
     */
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    /**
     * @param from
     * @param to
     * @param weight
     * @Author: MachineGeek
     * @Description: 增加边
     * @Date: 2021/2/25
     * @Return: void
     */
    public void addEdge(V from, V to, E weight) {
        // 如果没有这两个顶点，则创建
        Vertex<V, E> fromVertex = vertexs.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertexs.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertexs.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertexs.put(to, toVertex);
        }
        // 添加边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, weight);
        // 删除旧边
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        // 添加新边
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    /**
     * @param v
     * @Author: MachineGeek
     * @Description: 删除顶点
     * @Date: 2021/2/25
     * @Return: void
     */
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertexs.remove(v);
        if (vertex != null) {
            // 删除所有出的边
            for (Iterator<Edge<V, E>> outEdges = vertex.outEdges.iterator(); outEdges.hasNext(); ) {
                Edge<V, E> outEdge = outEdges.next();
                outEdge.to.inEdges.remove(outEdge);
                outEdges.remove();
                edges.remove(outEdge);
            }
            // 删除所有入的边
            for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
                Edge<V, E> inEdge = iterator.next();
                inEdge.from.outEdges.remove(inEdge);
                iterator.remove();
                edges.remove(inEdge);
            }
        }
    }

    /**
     * @param from
     * @param to
     * @Author: MachineGeek
     * @Description: 删除边
     * @Date: 2021/2/25
     * @Return: void
     */
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertexs.get(from);
        Vertex<V, E> toVertex = vertexs.get(to);
        // 顶点为空直接退出
        if (fromVertex == null || toVertex == null) {
            return;
        }
        // 删除边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, null);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    /**
     * @param first
     * @param visitor
     * @Author: MachineGeek
     * @Description: 深度优先搜索
     * @Date: 2021/2/25
     * @Return: void
     */
    public void depthFirstSearch(V first, Visitor<V> visitor) {
        if (visitor == null || vertexs.size() == 0) {
            return;
        }
        Vertex<V, E> vertex = vertexs.get(first);
        // 创建标记已访问Set
        Set<Vertex<V, E>> visited = new HashSet<>();
        // 创建回退栈
        Stack<Vertex<V, E>> vertexStack = new Stack<>();
        // 加入栈
        vertexStack.push(vertex);
        // 深度优先遍历
        while (!vertexStack.isEmpty()) {
            Vertex<V, E> pop = vertexStack.pop();
            // 如果未被访问
            if (!visited.contains(pop)) {
                // 访问
                visitor.operate(pop.value);
                // 如果遍历器要求停止，直接返回
                if (visitor.stop) {
                    return;
                }
                // 标记访问
                visited.add(pop);
            }
            // 找一条边进入
            for (Edge<V, E> edge : pop.outEdges) {
                if (!visited.contains(edge.to)) {
                    // 把当前顶点入栈
                    vertexStack.push(pop);
                    // 把要去的顶点入栈
                    vertexStack.push(edge.to);
                    break;
                }
            }
        }
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 广度优先搜索
     * @Date: 2021/2/25
     * @Return: void
     */
    public void breadthFirstSearch(V first, Visitor<V> visitor) {
        if (visitor == null || vertexs.size() == 0) {
            return;
        }
        // 如果顶点为空直接退出
        Vertex<V, E> vertex = vertexs.get(first);
        if (vertex == null) {
            return;
        }
        // 创建标记已访问Set
        Set<Vertex<V, E>> visited = new HashSet<>();
        // 广度优先遍历
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(vertex);
        // 队列不为空则一直遍历
        while (!queue.isEmpty()) {
            // 取出队头顶点
            Vertex<V, E> poll = queue.poll();
            // 访问
            visitor.operate(poll.value);
            // 标记被访问
            visited.add(poll);
            // 如果遍历器要求停止则退出
            if (visitor.stop) {
                return;
            }
            // 加入所有的能访问但未被访问的顶点
            for (Edge<V, E> edge : poll.outEdges) {
                if (!visited.contains(edge.to)) {
                    queue.offer(edge.to);
                }
            }
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 普利姆最小生成树
     * @Date: 2021/2/26
     * @Return: void
     */
    public Set<Edge<V, E>> prim() {
        if (edges.size() == 0) {
            return null;
        }
        // 随便取一个顶点
        Vertex<V, E> vertex = vertexs.values().iterator().next();
        // 存放最小生成树路径
        Set<Edge<V, E>> minEdges = new HashSet<>();
        // 存放已加入的顶点
        Set<Vertex<V, E>> linkedVertex = new HashSet<>();
        // 加入起点
        linkedVertex.add(vertex);
        // 创建一个最小堆
        PriorityQueue<Edge<V, E>> minHeap = new PriorityQueue<>(new Comparator<Edge<V, E>>() {
            @Override
            public int compare(Edge<V, E> o1, Edge<V, E> o2) {
                return weightManager.compare(o1.weight, o2.weight);
            }
        });
        // 把顶点的边都加入堆
        for (Edge<V, E> edge : vertex.outEdges) {
            minHeap.offer(edge);
        }
        // 最小生成树的边应该只有这么长
        int minEdgeSize = vertexs.size() - 1;
        // 堆不为空，并且已加入的边数量小于顶点数量。
        while (!minHeap.isEmpty() && minEdges.size() < minEdgeSize) {
            // 拿出当前顶点中最小的边
            Edge<V, E> minEdge = minHeap.remove();
            // 如果连接边的顶点已经加入集合则跳过
            if (linkedVertex.contains(minEdge.to)) {
                continue;
            }
            // 加入到顶点信息中
            minEdges.add(minEdge);
            // 加入顶点
            linkedVertex.add(minEdge.to);
            // 把下一个顶点的出边加入到堆中
            minHeap.addAll(minEdge.to.outEdges);
        }
        return minEdges;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 克鲁斯卡尔算法
     * @Date: 2021/2/26
     * @Return: java.util.Set<cn.machine.geek.structure.graph.Graph.Edge < V, E>>
     */
    public Set<Edge<V, E>> kruskal() {
        if (edges.size() == 0) {
            return null;
        }
        // 创建一个最小堆
        PriorityQueue<Edge<V, E>> minHeap = new PriorityQueue<>(new Comparator<Edge<V, E>>() {
            @Override
            public int compare(Edge<V, E> o1, Edge<V, E> o2) {
                return weightManager.compare(o1.weight, o2.weight);
            }
        });
        // 创建一个并查集
        UnionFind<Vertex<V, E>> unionFind = new UnionFind<>();
        // 把边都加入堆，把顶点加入并查集。
        for (Edge<V, E> edge : edges) {
            minHeap.offer(edge);
            unionFind.makeSet(edge.from);
            unionFind.makeSet(edge.to);
        }
        // 存放最小生成树路径
        Set<Edge<V, E>> minEdges = new HashSet<>();
        int minEdgeSize = vertexs.size() - 1;
        while (!minHeap.isEmpty() && minEdges.size() < minEdgeSize) {
            Edge<V, E> edge = minHeap.remove();
            // 如果不属于一个集合
            if (!unionFind.isSame(edge.from, edge.to)) {
                // 加入边
                minEdges.add(edge);
                // 并集
                unionFind.union(edge.from, edge.to);
            }
        }
        return minEdges;
    }

    /**
     * @param first
     * @Author: MachineGeek
     * @Description: 迪杰斯特拉算法
     * @Date: 2021/2/26
     * @Return: void
     */
    public Map<V, Path<V, E>> dijkstra(V first) {
        // 第一个顶点
        Vertex<V, E> vertex = vertexs.get(first);
        if (vertex == null) {
            return null;
        }
        // 去其他顶点的路径
        Map<V, Path<V, E>> selectedPaths = new HashMap<>();
        // 已确定的顶点的路径
        Map<Vertex<V, E>, Path<V, E>> paths = new HashMap<>();
        // 初始化可达路径
        for (Edge<V, E> edge : vertex.outEdges) {
            Path<V, E> path = new Path<>(edge.weight);
            path.paths.add(edge);
            paths.put(edge.to, path);
        }
        // 遍历可达路径
        while (!paths.isEmpty()) {
            // 选出最短的一条
            Map.Entry<Vertex<V, E>, Path<V, E>> minEntry = minPath(paths);
            // 当前最短路径到达的顶点
            Vertex<V, E> minVertex = minEntry.getKey();
            // 加入已选择
            selectedPaths.put(minVertex.value, minEntry.getValue());
            // 从路径中删除
            paths.remove(minVertex);
            // 对最短到达的顶点的延长路径进行比较
            for (Edge<V, E> edge : minVertex.outEdges) {
                // 如果已确定顶点 直接跳过
                if (selectedPaths.containsKey(edge.to.value)) {
                    continue;
                }
                // 延长路径
                E newWeight = weightManager.add(minEntry.getValue().weight, edge.weight);
                // 以前的路径
                Path<V, E> oldPath = paths.get(edge.to);
                // 如果以前的路径不存在或者新路径更短则加入新路径
                if (oldPath != null || weightManager.compare(newWeight, oldPath.weight) >= 0) {
                    continue;
                }
                if (oldPath == null) {
                    oldPath = new Path<>(newWeight);
                    paths.put(edge.to, oldPath);
                } else {
                    oldPath.paths.clear();
                }
                oldPath.paths.addAll(minEntry.getValue().paths);
                oldPath.paths.add(edge);
            }
        }
        selectedPaths.remove(vertex.value);
        return selectedPaths;
    }

    private Map.Entry<Vertex<V, E>, Path<V, E>> minPath(Map<Vertex<V, E>, Path<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, Path<V, E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, Path<V, E>> min = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, Path<V, E>> next = iterator.next();
            if (weightManager.compare(next.getValue().weight, min.getValue().weight) < 0) {
                min = next;
            }
        }
        return min;
    }
}
