package inspur.test;

import java.util.*;

/**
 * 浪潮考试2：超级素数
 * @link 连接 https://blog.csdn.net/weixin_39326965/article/details/78442191
 */

public class TestB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> lists = new ArrayList<>();
        StringBuffer num = new StringBuffer();
        long start = System.currentTimeMillis();
        printAll(lists, num, scanner.nextInt());
        long end = System.currentTimeMillis();
        Iterator<Integer> iterator = lists.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("消耗的总时间：" + (end - start) / 1000 + "s");
    }


    public static void printAll(List<Integer> lists, StringBuffer num, int len) {
        if (len < 1 || len > 8) {
            System.out.println("wrong input!");
            return;
        }

        if (num.length() == len) {
            return;
        }

        for (int i = 0; i <= 9; i++) {
            num.append(i);

            // if true...
            if (judge(Integer.valueOf(num.toString()))) {
                if (num.length() == len) {
                    lists.add(Integer.valueOf(num.toString()));
                }
                printAll(lists, num, len);
            }

            // keep num steady
            num.delete(num.length() - 1, num.length());
        }
    }

    public static boolean judge(int num) {
        if (num == 0) {
            return false;
        }
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                count++;
            }
        }
        return (count == 2) ? true : false;
    }

}
