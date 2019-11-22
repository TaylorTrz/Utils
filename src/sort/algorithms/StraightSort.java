package sort.algorithms;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @details 直接插入排序
 * @description 将无序序列不断插入到新的有序序列中，就是最简单的直接插入排序
 */

public class StraightSort {
    public StraightSort() {
    }


    /**
     * @details 小数据量，用顺序表
     * @param arrayList
     * @return
     */
    public ArrayList<Integer> sort(ArrayList arrayList) {
        int point, pivot=0;
        ArrayList<Integer> newArray = new ArrayList<>();
        Iterator<Integer> iteratorNewArray = newArray.iterator();
        Iterator iterator = arrayList.iterator();

        while (iterator.hasNext()) {
            // 获取当前要插入的点
            point = Integer.valueOf(String.valueOf(iterator.next()));

            // 找有序序列中合适插入位置
            while (iteratorNewArray.hasNext()) {
                pivot = iteratorNewArray.next();
//                System.out.println("当前指针位置： " + newArray.indexOf(pivot) + " 当前指针值： " + pivot);
                if (point < pivot) {
                    break;
                }
            }
            // 是否插尾部
            if (point > pivot) {
                newArray.add(point);
            } else {
                newArray.add(newArray.indexOf(pivot), point);
            }

            iteratorNewArray = newArray.iterator();
        }
        return newArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{20, 40, 30, 10, 60, 50};
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : array) {
            arrayList.add(i);
        }
        StraightSort ss = new StraightSort();
        ArrayList<Integer> newArray = ss.sort(arrayList);
        for (Integer i : newArray) {
            System.out.print(i + " " );
        }
    }
}
