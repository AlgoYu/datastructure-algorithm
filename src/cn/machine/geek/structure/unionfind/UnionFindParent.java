package cn.machine.geek.structure.unionfind;

/**
 * @Author: MachineGeek
 * @Description: 并查集基于父节点寻找优化
 * @Email: 794763733@qq.com
 * @Date: 2021/2/24
 */
public class UnionFindParent {
    private int[] parents;

    public UnionFindParent(int capacity) {
        if(capacity <= 0){
            throw new RuntimeException("capacity must be >= 1");
        }
        this.parents = new int[capacity];
        for (int i = 0; i < capacity; i++){
            parents[i] = i;
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 查找元素所属集合
     * @Date: 2021/2/24
     * @param v
     * @Return: int
     */
    public int find(int v){
        rangeCheck(v);
        while (v != parents[v]){
            v = parents[v];
        }
        return v;
    }

    /**
     * @Author: MachineGeek
     * @Description: 查看元素是否在同一个集合
     * @Date: 2021/2/24
     * @param v1
     * @param v2
     * @Return: boolean
     */
    public boolean isSame(int v1, int v2){
        return find(v1) == find(v2);
    }

    /**
     * @Author: MachineGeek
     * @Description: 联合两个集合
     * @Date: 2021/2/24
     * @param v1
     * @param v2
     * @Return: void
     */
    public void union(int v1,int v2){
        int p1 = find(v1);
        int p2 = find(v2);
        if(p1 != p2){
            parents[p1] = p2;
        }
    }

    /**
     * @Author: MachineGeek
     * @Description: 边界检查
     * @Date: 2021/2/24
     * @param index
     * @Return: void
     */
    private void rangeCheck(int index){
        if(index < 0 || index >= parents.length){
            throw new RuntimeException("index is out of size");
        }
    }
}
