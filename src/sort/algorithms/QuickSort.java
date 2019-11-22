package sort.algorithms;

/**
 * 快速排序
 * 挖坑填数 + 分治法
 *
 * @author taoruizhe
 * @date 20190910
 */

public class QuickSort {
    public int[] start(int[] array, int left, int right) {
        // 判断两端是否满足要求
        if (left >= right) {
            return array;
        }

        // 挖坑填数，pivot基准点
        int pivotValue = array[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (array[j] > pivotValue && i < j) {
                j--;
            }
            array[i] = array[j];
            while (array[i] < pivotValue && i < j) {
                i++;
            }
            array[j] = array[i];
        }
        array[i] = pivotValue;

        // 分治法
        start(array, left, i);
        start(array, i + 1, right);
        return array;
    }

    public static void main(String[] args) {
        int[] array = {2, 9, 4, 1, 7, 5};
        int left = 0;
        int right = 5;
        QuickSort qs = new QuickSort();
        array = qs.start(array, left, right);
        for (int element : array) {
            System.out.println(element);
        }

    }
}
