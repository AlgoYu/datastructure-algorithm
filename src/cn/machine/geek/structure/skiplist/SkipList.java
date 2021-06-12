package cn.machine.geek.structure.skiplist;

import java.util.Comparator;

/**
 * @Author: MachineGeek
 * @Description: 跳表
 * @Email: 794763733@qq.com
 * @Date: 2021/2/28
 */
public class SkipList<K, V> {
    private int size;
    private Node<K, V> first;
    private Comparator<K> comparator;
    private static final int validL = 32;
    private static final double P = 0.25;
    private int validLevel;

    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[level];
        }
    }

    public SkipList() {
        this(null);
    }

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        this.first = new Node<>(null, null, validL);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param key
     * @param value
     * @Author: MachineGeek
     * @Description: 放入节点
     * @Date: 2021/2/28
     * @Return: V
     */
    public V put(K key, V value) {
        checkKey(key);
        Node<K, V> node = first;
        // 用于保存向下走的节点
        Node<K, V>[] downNode = new Node[validLevel];
        // 从最高层开始
        for (int i = validLevel - 1; i >= 0; i--) {
            int cmp = -1;
            // 如果next不等于空，并且key大于下一个节点，就去next
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            // 如果比较结果相等，则覆盖返回
            if (cmp == 0) {
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            // 记录向下走的层和节点
            downNode[i] = node;
        }
        // 如果找不到，则没有这个节点，需要添加
        int newLevel = randomLevel();
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        // 把新节点的高层链表连接
        for (int i = 0; i < newLevel; i++) {
            if (i >= validLevel) {
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = downNode[i].nexts[i];
                downNode[i].nexts[i] = newNode;
            }
        }
        size++;
        validLevel = Math.max(validLevel, newLevel);
        return null;
    }

    /**
     * @param key
     * @Author: MachineGeek
     * @Description: 获取元素
     * @Date: 2021/2/28
     * @Return: V
     */
    public V get(K key) {
        checkKey(key);
        Node<K, V> node = first;
        // 从最高层开始
        for (int i = validLevel - 1; i >= 0; i--) {
            int cmp = -1;
            // 如果next不等于空，并且key大于下一个节点，就往下走
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            // 如果比较结果相等，则直接返回。
            if (cmp == 0) {
                return node.nexts[i].value;
            }
        }
        // 如果所有层都找不到，则返回空
        return null;
    }

    /**
     * @param key
     * @Author: MachineGeek
     * @Description: 删除节点
     * @Date: 2021/2/28
     * @Return: V
     */
    public V remove(K key) {
        checkKey(key);
        Node<K, V> node = first;
        // 用于保存向下走的节点
        Node<K, V>[] downNode = new Node[validL];
        // 标记是否不存在
        boolean exist = false;
        // 从最高层开始
        for (int i = validLevel - 1; i >= 0; i--) {
            int cmp = -1;
            // 如果next不等于空，并且key大于下一个节点，就去next
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            // 如果cmp == 0 代表存在
            if (cmp == 0) {
                exist = true;
            }
            // 记录向下走的层和节点
            downNode[i] = node;
        }
        // 如果不存在
        if (!exist) {
            return null;
        }
        // 指向被删除的节点
        node = node.nexts[0];
        // 把新节点的高层链表连接
        for (int i = 0; i < node.nexts.length; i++) {
            downNode[i].nexts[i] = node.nexts[i];
        }
        size--;
        // 更新有效层
        int newValidLevel = validLevel;
        while (--newValidLevel >= 0 && first.nexts[newValidLevel] == null) {
            validLevel = newValidLevel;
        }
        return node.value;
    }

    /**
     * @param key
     * @Author: MachineGeek
     * @Description: 检查Key
     * @Date: 2021/2/28
     * @Return: void
     */
    private void checkKey(K key) {
        if (key == null) {
            throw new RuntimeException("Key must not be null");
        }
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 随机层
     * @Date: 2021/2/28
     * @Return: int
     */
    private int randomLevel() {
        int n = 1;
        while (Math.random() < P && n < validL) {
            n++;
        }
        return n;
    }

    /**
     * @param k1
     * @param k2
     * @Author: MachineGeek
     * @Description: 比较Key
     * @Date: 2021/2/28
     * @Return: int
     */
    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable) k1).compareTo(k2);
    }
}
