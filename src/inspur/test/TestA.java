package inspur.test;

import org.junit.Test;

import java.util.*;

/**
 * 浪潮考试题目1：绳子长度N>5，考虑如何进行分段，从而分段各长度的积MAX
 *
 * @link 参考链接： https://blog.csdn.net/csdn_zjp/article/details/100714202
 */

public class TestA {

    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<>();
        StringBuilder lenArray = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        cutRope(lists, lenArray, scanner.nextInt());

        int key = 1;
        Iterator<Integer> iterator = lists.iterator();
        while (iterator.hasNext()) {
            int compare = iterator.next();
            if (compare > key) {
                key = compare;
            }
        }
        System.out.println(key);
    }

    /**
     * 解法1：时间空间消耗极大，超过8就错误
     *
     * @param lists
     * @param lenArray
     * @param len
     */

    public static void cutRope(List<Integer> lists, StringBuilder lenArray, int len) {
        if (len < 5) {
            System.out.println("wrong input");
            return;
        }

        int mylen = currentLength(lenArray);
        if (mylen >= len) {
            return;
        }

        // cut
        int i = 1;
        while (i <= (len - mylen)) {
            lenArray.append(i);
            cutRope(lists, lenArray, len);

            // record array of sub rope length
            int mylen2 = currentLength(lenArray);
            if (mylen2 == len) {
                lists.add(calculate(lenArray));
            }

            // keep lenArray steady
            lenArray.delete(lenArray.length() - 1, lenArray.length());
            i++;
        }
    }

    public static int currentLength(StringBuilder lenArray) {
        int len = 0;
        for (int i = 0; i < lenArray.length(); i++) {
            len += Integer.valueOf(lenArray.charAt(i)) - 48;
        }
        System.out.println("长度计算：" + len);
        return len;
    }

    public static int calculate(StringBuilder lenArray) {
        int result = 1;
        for (int i = 0; i < lenArray.length(); i++) {
            result *= Integer.valueOf(lenArray.charAt(i)) - 48;
        }
        System.out.println("乘积计算：" + result);
        return result;
    }
}
