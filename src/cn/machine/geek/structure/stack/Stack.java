package cn.machine.geek.structure.stack;

/**
 * @Author: MachineGeek
 * @Description: 栈
 * @Email: 794763733@qq.com
 * @Date: 2020/12/17
 */
public class Stack<E> {
    private int size;
    private E[] elements;
    private static final int CAPACITY = 10;

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 无参构造方法
     * @Date: 2020/12/17
     * @Return:
     */
    public Stack() {
        size = 0;
        elements = (E[]) new Object[CAPACITY];
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 有参构造方法
     * @Date: 2020/12/17
     * @Return:
     */
    public Stack(int size) {
        size = 0;
        if (size > CAPACITY) {
            elements = (E[]) new Object[size];
        } else {
            elements = (E[]) new Object[CAPACITY];
        }
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 扩容方法
     * @Date: 2020/12/17
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
     * @Date: 2020/12/17
     * @Return: void
     */
    public void push(E element) {
        resize(size + 1);
        elements[size] = element;
        size++;
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 是否为空
     * @Date: 2020/12/17
     * @Return: boolean
     */
    public boolean isEmpty() {
        return size == 0;
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
        if (null == element) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) {
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
     * @param
     * @Author: MachineGeek
     * @Description: 弹出元素
     * @Date: 2020/12/17
     * @Return: E
     */
    public E pop() {
        checkRange(size - 1);
        return elements[--size];
    }

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 检查范围
     * @Date: 2020/12/17
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
     * @Date: 2020/12/17
     * @Return: void
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}