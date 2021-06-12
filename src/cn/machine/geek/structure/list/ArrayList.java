package cn.machine.geek.structure.list;

/**
 * @Author: MachineGeek
 * @Description: 动态数组
 * @Email: 794763733@qq.com
 * @Date: 2020/12/2
 */
public class ArrayList<E> {
    private int size;
    private E[] elements;
    private static final int CAPACITY = 10;

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 无参构造方法
     * @Date: 2020/12/2
     * @Return:
     */
    public ArrayList() {
        size = 0;
        elements = (E[]) new Object[CAPACITY];
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 有参构造方法
     * @Date: 2020/12/2
     * @Return:
     */
    public ArrayList(int capacity) {
        capacity = 0;
        if (capacity > CAPACITY) {
            elements = (E[]) new Object[capacity];
        } else {
            elements = (E[]) new Object[CAPACITY];
        }
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 扩容方法
     * @Date: 2020/12/2
     * @Return: void
     */
    private void resize(int capacity) {
        if (capacity > elements.length) {
            E[] temp = (E[]) new Object[elements.length + (elements.length >> 1)];
            for (int i = 0; i < size; i++) {
                temp[i] = elements[i];
            }
            elements = temp;
        }
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 增加元素
     * @Date: 2020/12/2
     * @Return: void
     */
    public void add(E element) {
        resize(size + 1);
        elements[size] = element;
        size++;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 是否为空
     * @Date: 2020/12/8
     * @Return: boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param index
     * @Author: MachineGeek
     * @Description: 删除元素
     * @Date: 2020/12/2
     * @Return: E
     */
    public E remove(int index) {
        checkRange(index);
        E temp = elements[index];
        elements[index] = null;
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return temp;
    }

    /**
     * @param index
     * @param element
     * @Author: MachineGeek
     * @Description: 插入元素
     * @Date: 2020/12/2
     * @Return: void
     */
    public void insert(int index, E element) {
        checkRange(index);
        resize(size + 1);
        int i = size;
        while (i > index) {
            elements[i] = elements[i - 1];
            i--;
        }
        elements[index] = element;
        size++;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 返回元素数量
     * @Date: 2020/12/2
     * @Return: int
     */
    public int size() {
        return size;
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 查找元素索引
     * @Date: 2020/12/2
     * @Return: int
     */
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @param element
     * @Author: MachineGeek
     * @Description: 是否包含元素
     * @Date: 2020/12/2
     * @Return: boolean
     */
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    /**
     * @param index
     * @Author: MachineGeek
     * @Description: 查找元素
     * @Date: 2020/12/2
     * @Return: E
     */
    public E get(int index) {
        checkRange(index);
        return elements[index];
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 检查范围
     * @Date: 2020/12/2
     * @Return: void
     */
    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("范围越界");
        }
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 清空容器
     * @Date: 2020/12/2
     * @Return: void
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}
