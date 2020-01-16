package leetcode.swapForLongestRepeatedCharacterSubstring;

import java.util.ArrayList;
import java.util.List;

/**
 * @detail 单字符重复子串的最大长度
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 *
 * @thoughts
 * 单字符重复：从一个点出发，找到跟自己不同的点，就是最长。然后再从这个点出发，找到下一个不同的点。直至字符串尾
 * 交换：交换两个相同的字符毫无意义，所以记录下所有不同的字符的位置，然后依次交换，如果字符相同则不交换。
 * @TODO 超出时间限制...
 *
 */
public class Solution {
    private List<Integer>  list = new ArrayList<>();

    public int maxRepOpt1(String text) {
        int maxLength = getMaxLength(text);

        // 先交换，然后获取重复子串最大长度
        for (int i = 0; i < text.length(); i++)
            for (int j = i; j < text.length(); j++) {
                if (text.charAt(i) != text.charAt(j)) {
                    String swapped = swapOnce(text, i, j);
                    maxLength = Math.max(maxLength, getMaxLength(swapped));
                }
            }
        return maxLength;
    }

    public int getMaxLength(String text) {
        int left = 0;
        int right = 0;
        int maxLength = 0;

        // 找到最大不重复子串的长度
        while(right != text.length() && left < text.length()) {
            if (text.charAt(left) != text.charAt(right)) {
                left = right;
            }
            right++;
            maxLength = Math.max(maxLength, (right - left));
        }
        return maxLength;
    }


    public String swapOnce(String text, int left, int right) {
        // 交换非重复字符的位置
        return text.substring(0, left) + text.charAt(right) + text.substring(left + 1, right) + text.charAt(left) + text.substring(right + 1);
    }


    public List<Integer> getLocation(String text, List<Integer> list) {
        int left = 0;
        int right = 0;
        int maxLength = 0;

        // 找非重复的字符位置
        while(right != text.length() && left < text.length()) {
            if (text.charAt(left) != text.charAt(right)) {
                left = right;
                list.add(left);
            }
            right++;
            maxLength = Math.max(maxLength, (right - left));
        }
        return list;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().maxRepOpt1("aaaa"));
        System.out.println(new Solution().maxRepOpt1("aaabaaa"));
        System.out.println(new Solution().maxRepOpt1("aaabbaaa"));
        System.out.println(new Solution().maxRepOpt1("abcdef"));
    }

}
