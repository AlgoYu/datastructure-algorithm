package cn.machine.geek.structure.trie;

import cn.machine.geek.structure.map.HashMap;

/**
 * @Author: MachineGeek
 * @Description: 字典树
 * @Email: 794763733@qq.com
 * @Date: 2021/2/22
 */
public class Trie<V> {
    private int size;
    private Node<V> root;

    public Trie() {
        root = new Node<>();
    }

    public static class Node<V> {
        private Node<V> parent;
        private Character character;
        private V value;
        private boolean word;
        private HashMap<Character, Node<V>> children;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 返回元素长度
     * @Date: 2021/2/22
     * @Return: int
     */
    public int size() {
        return size;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 是否为空
     * @Date: 2021/2/22
     * @Return: boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 清空字典树
     * @Date: 2021/2/22
     * @Return: void
     */
    public void clear() {
        root.children = null;
        size = 0;
    }

    /**
     * @param key
     * @Author: MachineGeek
     * @Description: 获取节点元素
     * @Date: 2021/2/22
     * @Return: V
     */
    public V get(String key) {
        Node<V> node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * @param str
     * @Author: MachineGeek
     * @Description: 是否包含
     * @Date: 2021/2/22
     * @Return: boolean
     */
    public boolean contains(String str) {
        return getNode(str) != null;
    }

    /**
     * @param key
     * @param value
     * @Author: MachineGeek
     * @Description: 增加元素
     * @Date: 2021/2/22
     * @Return: V
     */
    public V add(String key, V value) {
        if (key == null || key.length() == 0) {
            return null;
        }
        Node<V> temp = root;
        for (int i = 0; i < key.length(); i++) {
            if (temp.children == null) {
                temp.children = new HashMap<>();
            }
            char ch = key.charAt(i);
            Node<V> node = temp.children.get(ch);
            if (node == null) {
                node = new Node<>();
                node.character = ch;
                node.parent = temp;
                temp.children.put(ch, node);
            }
            temp = node;
        }
        if (!temp.word) {
            temp.word = true;
            temp.value = value;
            size++;
            return null;
        }
        V old = temp.value;
        temp.value = value;
        return old;
    }

    /**
     * @param key
     * @Author: MachineGeek
     * @Description: 删除元素
     * @Date: 2021/2/22
     * @Return: V
     */
    public V remove(String key) {
        if (key == null || key.length() == 0) {
            return null;
        }
        Node<V> node = getNode(key);
        if (node == null || !node.word) {
            return null;
        }
        V old = node.value;
        if (node.children != null && !node.children.isEmpty()) {
            node.word = false;
            node.value = null;
            return old;
        }
        while (node.parent != null) {
            node.parent.children.remove(node.character);
            if (node.parent.word || node.parent.children.size() > 0) {
                break;
            }
            node = node.parent;
        }
        return old;
    }

    /**
     * @param prefix
     * @Author: MachineGeek
     * @Description: 匹配前缀
     * @Date: 2021/2/22
     * @Return: boolean
     */
    public boolean starsWith(String prefix) {
        checkRange();
        Node<V> temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (temp.children == null) {
                return false;
            }
            temp = temp.children.get(prefix.charAt(i));
            if (temp == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 检查值域
     * @Date: 2021/2/22
     * @Return: void
     */
    private void checkRange() {
        if (size == 0) {
            throw new RuntimeException("Trie is empty.");
        }
    }

    /**
     * @param key
     * @Author: MachineGeek
     * @Description: 获取节点
     * @Date: 2021/2/22
     * @Return: cn.machine.geek.structure.trie.Trie.Node<V>
     */
    private Node<V> getNode(String key) {
        checkRange();
        Node<V> temp = root;
        for (int i = 0; i < key.length(); i++) {
            if (temp.children == null) {
                return null;
            }
            temp = temp.children.get(key.charAt(i));
            if (temp == null) {
                return null;
            }
        }
        return temp.word ? temp : null;
    }
}
