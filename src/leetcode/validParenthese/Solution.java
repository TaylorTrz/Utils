package leetcode.validParenthese;


/**
 * @detail 有效括号
 *          思考：对于一个可行的字符串，如果成立那么必须是偶数个字符，然后从中间开始是完全对称的。
 *
 * @author taoruizhe
 * @date 2020/01/23
 */
public class Solution {
    public boolean isValid(String s) {
        if (null == s) {
            return false;
        }
        if ("".equals(s)) {
            return true;
        }

        int i = 0;
        while(true) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i) || i > s.length() - 1 - i) {
                break;
            }
            i++;
        }

        if (i > s.length() - 1 - i) {
            return true;
        }
        return false;
    }
}
