package permutation.string.array;

import java.util.*;

/**
 * @author taorz
 * @version 1.0
 * @details 输入一个字符串, 按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba
 * @since 20191024
 */

public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> lists = permutate(input.nextLine());
        Iterator<String> iterator = lists.iterator();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            System.out.println(temp + temp.getClass().getName());
        }
    }


    /**
     * @details 字符串的排列 @引用牛客网
     * @details 1.递归法解决permutation 2.字典序排序
     * @details 递归的循环过程，就是从每个子串的第二个字符开始依次与第一个字符交换，然后继续处理子串。
     */

    // 1. 递归法
    public static ArrayList<String> permutation(String str) {
        List<String> res = new ArrayList<>();
        if (str != null && str.length() > 0) {
            permutationHelper(str.toCharArray(), 0, res);
            Collections.sort(res);
        }
        return (ArrayList) res;
    }

    // 字符串排列
    public static void permutationHelper(char[] cs, int i, List<String> lists) {
        if (i == cs.length - 1 && !lists.contains(String.valueOf(cs))) {
            lists.add(String.valueOf(cs));
        }

        for (int j = i; j < cs.length; j++) {
            swap(cs, i, j);
            permutationHelper(cs, i + 1, lists);
            swap(cs, i, j);
        }

    }

    public static void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    /**
     * @details 字典序全排列方法
     * @see [1] https://www.cnblogs.com/pmars/archive/2013/12/04/3458289.html
     */
    public static ArrayList<String> permutate(String str) {
        ArrayList<String> list = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            return list;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        list.add(String.valueOf(chars));
        int len = chars.length;
        while (true) {
            int lIndex = len - 1;
            int rIndex;
            while (lIndex >= 1 && chars[lIndex - 1] >= chars[lIndex]) {
                lIndex--;
            }
            if (lIndex == 0)
                break;
            rIndex = lIndex;
            while (rIndex < len && chars[rIndex] > chars[lIndex - 1]) {
                rIndex++;
            }
            swap(chars, lIndex - 1, rIndex - 1);
            reverse(chars, lIndex);
            list.add(String.valueOf(chars));
        }

        return list;
    }

    public static void reverse(char[] chars, int k) {
        if (chars == null || chars.length <= k) {
            return;
        }
        int len = chars.length;
        for (int i = 0; i < (len - k) / 2; i++) {
            int m = k + i;
            int n = len - 1 - i;
            if (m <= n) {
                swap(chars, m, n);
            }
        }
    }

}
