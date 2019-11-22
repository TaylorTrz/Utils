package sort.algorithms;


/**
@Date:    20190729
@Author:  Taylor Tao
@Notes:   冒泡排序
 */

public class BubbleSort {
    public static String version = "20190729 by Taylor Tao";
    public int[] start(int[] array){
        for(int i = 0; i < array.length; i++)
            for(int j = i; j > 0; j--){
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        return array;
    }

}
