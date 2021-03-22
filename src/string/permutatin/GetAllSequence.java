package string.permutatin;

import java.util.*;

/**
 * @Test 练习如何求取字符串的所有排列
 * @Test comparator的实现
 * @Test sort()方法重写
 */

public class GetAllSequence {

    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        getAllSequence("abcb".toCharArray(), 0, lists);

//        Comparator<String> comparator = (String a, String b) -> {
//            if (a.toCharArray()[1] > b.toCharArray()[1]) {
//                return 1;
//            }
//            else if (a.toCharArray()[1] < b.toCharArray()[1]) {
//                return -1;
//            }
//            else
//                return 0;
//        };

        Compare comparator = new Compare();
        lists.sort(comparator);

        Iterator<String> iterator = lists.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void getAllSequence(char[] chars, int i, List<String> lists) {
        if (i == chars.length - 1) {
            lists.add(String.valueOf(chars));
        }

        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            getAllSequence(chars, i + 1, lists);
            swap(chars, i, j);
        }

    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[j];
        chars[j] = chars[i];
        chars[i] = temp;
    }

    // implement of interface Comparator<String>
    static class Compare implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            if (a.toCharArray()[3] > b.toCharArray()[3]) {
                return 1;
            } else if (a.toCharArray()[3] < b.toCharArray()[3]) {
                return -1;
            } else
                return 0;
        }
    }
}
