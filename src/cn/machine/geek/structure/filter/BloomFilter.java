package cn.machine.geek.structure.filter;

/**
 * @Author: MachineGeek
 * @Description: 布隆过滤器
 * @Email: 794763733@qq.com
 * @Date: 2021/2/28
 */
public class BloomFilter<E> {
    private int bitSize;
    private long[] bits;
    private int hashSize;

    public BloomFilter(int n, double p) {
        if(n <= 0 || p <= 0 || p >= 1){
            throw new RuntimeException("wrong bit size or p.");
        }
        double ln2 = Math.log(2);
        // 求位长度
        bitSize = (int) ((- (n * Math.log(p))) / (ln2 * ln2));
        // 求哈希函数个数
        hashSize = (int) (bitSize * ln2 / n);
        // 求Long数组长度
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
    }

    /**
    * @Author: MachineGeek
    * @Description: 计算Hash并更改二进制位
    * @Date: 2021/2/28
     * @param element
    * @Return: boolean
    */
    public boolean put(E element){
        check(element);
        int hashCode1 = element.hashCode();
        int hashCode2 = hashCode1 >>> 16;
        boolean flag = false;
        for (int i = 1; i <= hashSize; i++){
            int index = hashCode1 + (i * hashCode2);
            if(index < 0){
                index = ~index;
            }
            if (setBit(index)){
                flag = true;
            }
        }
        return flag;
    }

    public boolean contains(E element){
        check(element);
        int hashCode1 = element.hashCode();
        int hashCode2 = hashCode1 >>> 16;
        for (int i = 1; i <= hashSize; i++){
            int index = hashCode1 + (i * hashCode2);
            if(index < 0){
                index = ~index;
            }
            if(!get(index)){
                return false;
            }
        }
        return true;
    }

    /**
    * @Author: MachineGeek
    * @Description: 设置index的二进制位为1
    * @Date: 2021/2/28
     * @param index
    * @Return: void
    */
    private boolean setBit(int index){
        long value = bits[index / Long.SIZE];
        int newValue = 1 << (index % Long.SIZE);
        bits[index / Long.SIZE] = newValue;
        return (value & newValue) == 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取index的二进制位
    * @Date: 2021/2/28
     * @param index
    * @Return: boolean
    */
    private boolean get(int index){
        long value = bits[index / Long.SIZE];
        return (value & 1 << (index % Long.SIZE)) != 0;
    }

    /**
    * @Author: MachineGeek
    * @Description: 检查值
    * @Date: 2021/2/28
     * @param
    * @Return: void
    */
    private void check(E element){
        if(element == null){
            throw new RuntimeException("Element is null.");
        }
    }
}