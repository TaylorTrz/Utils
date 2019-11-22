package utils.string;

import org.junit.Test;

/**
 * @details 工具类：字符串反转
 * @description  造轮子...
 * @author taoruizhe@inspur.com
 * @date 20191115 15:00
 * @since v1.0.0
 */

public class Reverse {
    public String reverse(String str) {
        StringBuilder strBuilder = new StringBuilder(str);
        return strBuilder.reverse().toString();
    }

    @Test
    public void main() {
        System.out.println(reverse("chinese"));
    }
}
