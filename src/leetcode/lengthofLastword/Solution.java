package leetcode.lengthofLastword;

/**
 * @description 最后一个单词的长度
 * @since v1.0
 * @author taoruizhe
 */

public class Solution {
    /**
     * @description 简单方法，trim然后split然后再转换字符数组获取长度，创建4个新的Object
     * @param s
     * @return length of last word
     */
    public int lengthOfLastWord1(String s) {
        // last word
        String newS = s.trim();
        String[] strList =  newS.split(" ");
        String lastStr = strList[strList.length - 1];
        // split
        char[] charList = lastStr.toCharArray();
        return charList.length;
    }

    /**
     * @descprtion StringBuilder方法处理，从末尾循环，count到空格字符
     * @param s
     * @return lenth of last word
     */
    public int lengthOfLastWord2(String s) {
        StringBuilder stb = new StringBuilder(s);
        int end = 0;
        int start = 0;
        for (int i = stb.length() - 1; i >= 0; i--) {
            if (end == 0 && stb.charAt(i) != ' ') {
                end =  i;
            }
            if (end != 0 && stb.charAt(i) == ' ') {
                start = i - 1;
                break;
            }
            if (i == 0 && stb.charAt(i) != ' ') {
                start = 0;
            }
        }
        return end - start + 1;
    }
}
