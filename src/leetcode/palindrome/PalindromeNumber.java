package leetcode.palindrome;

import java.math.BigInteger;

/**
 * @author taoruizhe
 * @leetcode 回文数
 * @date 2020/04.13
 */
public class PalindromeNumber {
    // 直接用Long硬上
    public boolean isPalindrome(int x) {
        if (x >= 0) {
            StringBuilder stb = new StringBuilder(String.valueOf(x));
            if (Long.parseLong(stb.reverse().toString()) == (long) x) {
                return true;
            }
        }
        return false;
    }

    // 转换成字符数组挨个比较
    public boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        } else {
            char[] chars = String.valueOf(x).toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != chars[chars.length - i - 1]) {
                    return false;
                }
            }

        }
        return true;
    }

    // 官方解法：整型要考虑溢出问题，拆分成一半，不断进行除余操作求取末端数字进行比较，循环终止条件是left=right或left/10<right*10
    public boolean isPalindrome2(int x) {
        int y = 0;
        if (x >= 0 && x % 10 != 0 || x == 0) {
            for (; ; ) {
                if (x == y || x / 10 == y) {
                    return true;
                }
                y = y * 10 + x % 10;
                x = x / 10;

                if (x < y) {
                    return false;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome2(1));
    }

}
