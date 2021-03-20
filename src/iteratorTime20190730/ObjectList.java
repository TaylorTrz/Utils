package iteratorTime20190730;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 实现简单ArrayList
 */
public class ObjectList implements Serializable {
    private static final long SerialVersionUID = 10240L;

    /* 本质是Object数组 */
    private Object[] objects;

    private int size;

    private int pivot = 0;

    /* 默认数组容量为16
    Q:为什么默认容量可以定位16？
    Q:如果容量使用过程超过16，应该如何扩容？
     */
    public ObjectList() {
        this.size = 1 << 4;
        this.objects = new Object[1 << 4];
    }

    /* 推荐值[4, 10]
    Q:考虑过HashMap中的扩容操作吗？
    Q:进位操作为什么是好的？
    */
    public ObjectList(int capacity) {
        this.size = 1 << capacity;
        this.objects = new Object[1 << capacity];
    }


    public void add(Object object) {
        // 查看数组长度是否足够
        if (pivot >= size) {
            System.out.println("OutOfArrayBoundaryExceptin");
            return;
        }
        objects[pivot] = object;
        pivot++;
    }

    /*
    Q:如何删除不唯一的Object呢？
     */
    public void remove(Object object) {
        int p = pivot;
        if (p == 0 || null == object) {
            System.out.println("NullPointerException");
            return;
        }

        // 从尾开始查找与object相同的元素
        while (p > 0) {
            p--;
            // 找到之后先置空
            if (object.equals(objects[p])) {
                objects[p] = null;

                // 根据置空位置，将该位置后的所有元素向前进一位
                for (int i = p; i < pivot; i++) {
                    Object o = objects[i + 1];
                    objects[i + 1] = objects[i];
                    objects[i] = o;
                }

                pivot--;
                return;
            }
        }

        // 如果未找到对应元素，则返回异常
        System.out.println("NoSuchParamException");
    }

    public Object get(int i) {
        if (i < 0 || i >= size) {
            return "OutOfArrayBoundaryException";
        }
        if (i >= pivot) {
            return "NoSuchParamException";
        }
        return this.objects[i];
    }

    @Override
    public String toString() {
        return Arrays.toString(objects);
    }


}
