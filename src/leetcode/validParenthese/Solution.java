package leetcode.validParenthese;


import java.util.Stack;

/**
 * @author taoruizhe
 * @detail 有效括号
 * 警示：对于科班而言，这题是非常简单的。这是因为我仅仅是对于语法结构有了解，而对于算法结构是个外行。
 * 思路：栈，三种括号类型。如果是开括号，则放入栈，如果是闭括号，与栈顶元素进行对比，如果对应则弹出，否则无效。
 * @date 2020/01/23
 */
public class Solution {
    private static final Stack<Character> stack = new Stack<>();
    private static final String leftParentheses = "([{";

    public boolean isValid(String s) {
        if (null == s) {
            return false;
        }
        if ("".equals(s)) {
            return true;
        }

        int i = 0;
        for (; i < s.length(); i++) {
            if (leftParentheses.contains(String.valueOf(s.charAt(i)))) {
                stack.push(s.charAt(i));
            } else {
                char c = s.charAt(i);
                Character pop = stack.pop();
                if (c == ')') {
                    if (pop == null || !pop.equals('('))
                        break;
                }
                if (c == ']') {
                    if (pop == null || !pop.equals('['))
                        break;
                }
                if (c == '}') {
                    if (pop == null || !pop.equals('{'))
                        break;
                }
            }
        }

        return i == s.length() && stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("{[]}"));
    }
}

