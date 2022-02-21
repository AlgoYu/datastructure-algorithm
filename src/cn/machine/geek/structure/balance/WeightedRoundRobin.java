package cn.machine.geek.structure.balance;

import java.util.List;

/**
 * @Author XiaoYu
 * @Description TODO 加权平滑轮询算法
 * @Datetime 2022/2/17 4:45 下午
 **/
public class WeightedRoundRobin<T> {
    // 结构体
    static class Node<T> {
        int weight;
        int curWeight;
        T data;

        public Node(int weight, T data) {
            this.weight = weight;
            this.data = data;
        }
    }

    // 所有的节点
    Node<T>[] nodes;
    // 长度
    int n;

    // 初始化轮询器
    public WeightedRoundRobin(List<Node<T>> data) {
        n = data.size();
        if (n == 0) {
            throw new RuntimeException("节点长度不能为0");
        }
        nodes = new Node[n];
        // 初始化节点
        for (int i = 0; i < n; i++) {
            nodes[i] = data.get(i);
            nodes[i].curWeight = 0;
        }
    }

    /**
     * 获取加权平滑轮询的下一个节点
     *
     * @return 下一个节点
     */
    public T getNext() {
        // 权重和与下标
        int total = 0, index = -1;
        for (int i = 0; i < n; i++) {
            // 加上遍历到的元素权重
            total += nodes[i].weight;
            // 当前元素增加一倍自己本身的权重
            nodes[i].curWeight += nodes[i].weight;
            // 选中的下标为负数或者已选择的元素当前权重比此元素小
            if (index == -1 || nodes[index].curWeight < nodes[i].curWeight) {
                // 选择元素
                index = i;
            }
        }
        // 删除权重和
        nodes[index].curWeight -= total;
        return nodes[index].data;
    }
}
