package cn.machine.geek.structure.balance;

import java.util.Arrays;
import java.util.List;

/**
 * @Author XiaoYu
 * @Description 权重选择器/分流器/加权随机/AB测试/抽奖等，增删改查的方法没加，构造函数传入集合使用。
 * @Datetime 2022/2/17 4:45 下午
 **/
public class WeightSelector<T> {
    // 结构体
    static class Node<T> {
        // 权重的值最好总和加起来不要超过int类型存储的上限，这方面没有做检查。
        int weight;
        int curWeight;
        T data;

        public Node(int weight, T data) {
            this.weight = weight;
            this.data = data;
        }
    }

    // 自定义获取算法
    interface WeightedAlgorithm<T> {
        T getNext(Node<T>[] nodes);
    }

    // 所有的节点
    Node<T>[] nodes;
    // 长度
    int n;
    // 权重总和
    int total;
    // 前缀和
    int[] prefixSum;
    // 自定义权重算法
    WeightedAlgorithm<T> weightedAlgorithm;

    // 初始化轮询器
    public WeightSelector(List<Node<T>> data) {
        this(data, null);
    }

    /**
     * 边界检查
     *
     * @param data 权重集合
     * @return 数据长度
     */
    private int checkBounds(List<Node<T>> data) {
        if (data == null) {
            throw new RuntimeException("数据集合为空");
        }
        int len = data.size();
        if (len == 0) {
            throw new RuntimeException("节点长度不能为0");
        }
        return len;
    }

    // 初始化轮询器
    public WeightSelector(List<Node<T>> data, WeightedAlgorithm<T> algorithm) {
        n = checkBounds(data);
        nodes = new Node[n];
        total = 0;
        prefixSum = new int[n];
        // 初始化节点
        for (int i = 0; i < n; i++) {
            nodes[i] = data.get(i);
            nodes[i].curWeight = 0;
            total += nodes[i].weight;
            if (i > 0) {
                prefixSum[i] = prefixSum[i - 1] + nodes[i].weight;
                continue;
            }
            prefixSum[i] = nodes[i].weight;
        }
        weightedAlgorithm = algorithm;
    }

    /**
     * 获取轮询的下一个节点
     *
     * @return 下一个节点
     */
    public T getRoundRobinNext() {
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

    /**
     * 获取权重随机值。不计入加权平滑轮询中）
     *
     * @return 随机获取的节点
     */
    public T getRandomNext() {
        // 随机获取一个值
        int x = (int) (Math.random() * total) + 1;
        // 二分搜索
        int left = 0, right = n - 1;
        int mid = 0;
        while (left < right) {
            mid = (right - left) >> 1 + left;
            if (prefixSum[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nodes[left].data;
    }

    /**
     * 根据权重获取随机值，静态方法，一次性获取。
     *
     * @param data 数据集合
     * @param <T>  自己定义的数据
     * @return 加权随机获取的值
     */
    public static <T> T getRandomNext(List<Node<T>> data) {
        if (data == null) {
            throw new RuntimeException("数据集合为空");
        }
        int tN = data.size();
        if (tN == 0) {
            throw new RuntimeException("节点长度不能为0");
        }
        int tTotal = data.get(0).weight;
        int[] tPreSum = new int[tN];
        tPreSum[0] = tTotal;
        for (int i = 1; i < tN; i++) {
            tPreSum[i] = tPreSum[i - 1] + data.get(i).weight;
            tTotal += tPreSum[i] - tPreSum[i - 1];
        }
        // 随机获取一个值
        int x = (int) (Math.random() * tTotal) + 1;
        // 二分搜索
        int left = 0, right = tN - 1;
        int mid = 0;
        while (left < right) {
            mid = (right - left) >> 1 + left;
            if (tPreSum[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return data.get(left).data;
    }

    /**
     * 使用自定义算法获取下一个值
     *
     * @return
     */
    public T getMyNext() {
        if (weightedAlgorithm == null) {
            throw new RuntimeException("没有自定义的获取算法");
        }
        return weightedAlgorithm.getNext(nodes);
    }

    public static void main(String[] args) {
        // 初始化节点权重
        WeightSelector.Node<String> node1 = new Node<>(20, "节点1");
        WeightSelector.Node<String> node2 = new Node<>(30, "节点2");
        WeightSelector.Node<String> node3 = new Node<>(50, "节点3");
        WeightSelector<String> weightSelector = new WeightSelector<String>(Arrays.asList(node1, node2, node3));
        // 随机算法测试
        System.out.println("---------随机算法 START----------");
        System.out.println(weightSelector.getRandomNext());
        System.out.println(weightSelector.getRandomNext());
        System.out.println(weightSelector.getRandomNext());
        System.out.println(weightSelector.getRandomNext());
        System.out.println(weightSelector.getRandomNext());
        System.out.println("---------随机算法 END----------");
        // 加权轮询测试
        System.out.println("---------加权轮询 START----------");
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println(weightSelector.getRoundRobinNext());
        System.out.println("---------加权轮询 END----------");
        // 自定义算法测试
        WeightSelector<String> customAlgorithm = new WeightSelector<String>(Arrays.asList(node1, node2, node3), (o1) -> {
            return o1[0].data;
        });
        System.out.println("---------自定义算法 START----------");
        System.out.println(customAlgorithm.getMyNext());
        System.out.println(customAlgorithm.getMyNext());
        System.out.println(customAlgorithm.getMyNext());
        System.out.println(customAlgorithm.getMyNext());
        System.out.println("---------自定义算法 END----------");
        // 静态方法一次性测试
        System.out.println("---------静态方法 START----------");
        System.out.println(WeightSelector.getRandomNext(Arrays.asList(node1, node2, node3)));
        System.out.println(WeightSelector.getRandomNext(Arrays.asList(node1, node2, node3)));
        System.out.println(WeightSelector.getRandomNext(Arrays.asList(node1, node2, node3)));
        System.out.println(WeightSelector.getRandomNext(Arrays.asList(node1, node2, node3)));
        System.out.println("---------静态方法 END----------");
    }
}
