package iterator;

/**
 * @Date: 20190729
 * @Author: Taylor Tao
 * @Notes: 链表、顺序表等表结构中，迭代器遍历与get遍历的消耗时间对比
 */

import java.util.*;

public class iteratorTime {
    public static void main(String[] args) {

        try {
            List<String> list = new LinkedList<String>(); //接口回调
            for (int i = 0; i <= 60096; i++) {
                list.add("speed" + i);
            }

            Iterator<String> iter = list.iterator();
            long startTime = System.currentTimeMillis();
            while (iter.hasNext()) {
                String te = iter.next();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("使用迭代器遍历：" + (endTime - startTime) + "ms");

            startTime = System.currentTimeMillis();
            for (int i = 0; i < list.size(); i++) {
                String te = list.get(i);
            }
            endTime = System.currentTimeMillis();
            System.out.println("使用get方法遍历：" + (endTime - startTime) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}




