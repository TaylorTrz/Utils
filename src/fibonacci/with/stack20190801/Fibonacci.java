package fibonacci.with.stack20190801;

/**
 * 用stack存储并计算fibonacci数列，之前用递归，现在用stack，效率大大提升
 */

import java.util.*;

public class Fibonacci {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(new Integer(1));
        stack.push(new Integer(1));
        int k = 1;
        while (k <= 10) {
            k++;
            // 出栈，弹出
            Integer F1 = stack.pop();
            int f1 = F1.intValue();
            Integer F2 = stack.pop();
            int f2 = F2.intValue();

            // 入栈，压入
            stack.push(new Integer(k));
            stack.push(new Integer(f2));
        }
    }
}
