package sort.algorithms;

import java.util.Arrays;

/**
 * @details 堆排序
 * 堆是完全二叉树，其中大顶堆每个节点的值都大于或等于左右子节点的值，小顶堆父节点的值小于或等于左右子节点。
 * 是一种选择排序，最好、最坏、平均时间复杂度都是o(nlogn)
 * @description 基本思路
 * 1. 将待排序序列构造为一个大顶堆（升序）
 * 2. 将堆顶元素与末尾元素交换，将最大元素沉到数组末尾
 * 3. 将剩余n-1个元素重新构造最大堆，重复步骤直到整个数组有序
 * @since 20191005 9:00
 * @see [1]:https://www.cnblogs.com/chengxiao/p/6129630.html
 */

public class HeapSort {
    private static final String ALGORITHM = "heap sort";

    public static void main(String[] args) {
        System.out.println("trying " + ALGORITHM + " ...");
        char[] array = {'9', '8', '7', '6', '5', '4', '3', '2', '1'};
        heapSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void sort(int[] array) {
        // *构造大顶堆
        for (int i = (array.length - 2) / 2; i > 0; i--) {
            buildHeap(array, i, array.length);
        }

        // 交换堆顶与末尾元素，并将剩下n-1元素维持为大顶堆(改变顶元素位置)
        for (int j = array.length - 1; j > 0; j--) {
            swap(array, 0, j);
            buildHeap(array, 0, j);
        }

    }

    // 确保start到end之间是大顶堆排列，重点关注顶点的位置
    public static void buildHeap(int[] array, int start, int end) {
        int temp = array[start];

        for (int k = start*2+1; k < end; k = k*2+1) {
            // 如果左子节点比右子节点小，则指向位置右移，在结尾如果出现单节点不考虑该情况
            if (k+1 < end && array[k] < array[k+1]) {
                k++;
            }
            // 如果子节点大于比较值，将子节点值赋给父节点然后父节点下移(直接覆盖)
            if (array[k] > temp) {
                array[start] = array[k];
                start = k;
            }
        }
        array[start] = temp;
    }


    public static void swap(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }

    /**
     * @details 字符数组的堆排序
     * @Since v2.0
     * @date 20191108 17:00
     * @param chars
     * @return
     */
    public static void heapSort(char[] chars) {
        for (int start = chars.length - 1; start < chars.length; start++) {
            rebuildHeap(chars, start, chars.length);
        }
        for (int j = chars.length - 1; j > 0; j--) {
            swap(chars, 0, j);
            rebuildHeap(chars, 0, j);
        }

    }

    public static void rebuildHeap(char[] chars, int start, int end) {
        char temp = chars[start];
        int i = 2 * start + 1;
        while (i < end) {
            if (chars[i] < chars[i + 1] && i < end - 1) {
                i++;
            }
            if (chars[i] > temp) {
                chars[start] = chars[i];
                start = i;
            }

            i = 2 * i + 1;
        }
        chars[start] = temp;
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
