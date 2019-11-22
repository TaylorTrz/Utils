package leetcode.longestPlalindrome;


import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class LongestPlalindrome {
    public void LongestPlalindrome() {
    }

    /**
     * 暴力法
     *
     * @param s
     * @return subString
     */
    public String longestPlalindrome1(String s) {
        if (s.equals("")) {
            return "";
        }
        StringBuilder stb = new StringBuilder(s);
        Map<Integer, String> map = new HashMap<Integer, String>();

        // n2循环，获得回文字符串大小并放入hashmap
        for (int i = 0; i < stb.length(); i++)
            for (int j = i; j < stb.length(); j++) {
                StringBuilder newStb = new StringBuilder(stb.substring(i, j + 1));
                if (newStb.reverse().toString().equals(stb.substring(i, j + 1))) {
                    map.put(j - i + 1, newStb.toString());
                }
            }

        // 循环器迭代，获取最多字符串并取出
        Iterator iterator = map.entrySet().iterator();
        Object compare = 0;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();
            if ((Integer) key > (Integer) compare) {
                compare = key;
            }
        }
        return map.get(compare);
    }

    /**
     * 中心拓展法
     *
     * @param s
     * @return subString
     */
    public String longestPlalindrome2(String s) {
        if (s.equals("") || s.length() <= 1) {
            return s;
        }
        int start = 0;
        int end = 0;
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = getMaxLength(s, i, i);
            int len2 = getMaxLength(s, i, i + 1);
            if (len < len1 && len1 > len2) {
                start = i - (len1 -1) / 2;
                end = i + (len1 - 1) / 2;
                len = len1;
            }
            if (len < len2 && len2 > len1) {
                start = i - (len2 / 2 - 1);
                end = i + 1 + (len2 / 2 - 1);
                len = len2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int getMaxLength(String s, int start, int end) {
        if (start < 0 || end >= s.length()) {
            return -1;
        }
        if (s.charAt(start) != s.charAt(end)) {
            return 1;
        }
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return end - start - 1;
    }

    public static void main(String[] args) {

        String s = "cbbd";
        String[] key = {"a", "b"};
        System.out.print(key);
        long start = System.currentTimeMillis();
        LongestPlalindrome lp = new LongestPlalindrome();
        System.out.println(lp.longestPlalindrome2(s));
        long end = System.currentTimeMillis();
        System.out.println("Elapse time = " + (end - start));
    }
}
