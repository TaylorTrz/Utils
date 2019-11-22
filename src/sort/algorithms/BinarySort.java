package sort.algorithms;

import java.util.Arrays;

/**
 * @author taoruizhe@inspur.com
 * @details 二分法插入排序
 * 利用二分查找，查到到a[i]的位置并插入，每次将查找的范围缩小一半。
 * 平均性能比直接插入排序块，并且性能稳定，因为其所需要的排序比较次数仅仅依赖于对象个数。时间o(nlogn)
 * @since 20191108 14:00
 */

public class BinarySort {

    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        binarySort(array);
        System.out.println(Arrays.toString(array));
    }

    // 对array[i]，如果i之前已经是有序序列，将array[i]插入到合适位置
    public static int[] binarySort(int[] array) {
        int pivot = 1;
        while (pivot < array.length) {
            // 二分查找
            int middle = binarySearch(array, array[pivot], 0, pivot - 1);
            if (array[pivot] < array[middle]) {
            } else {
                middle++;
            }
            int key = pivot - 1;
            while (key >= middle) {
                swap(array, key, key + 1);
                key--;
            }
            pivot++;
        }
        return array;
    }

    public static int binarySearch(int[] array, int value, int left, int right) {
        int middle = 0;
        while (left < right) {
            middle = Integer.valueOf((right + left) / 2);
            if (array[middle] > value) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return middle;
    }

    public static void swap(int[] array, int key1, int key2) {
        int temp = array[key2];
        array[key2] = array[key1];
        array[key1] = temp;
    }
}
