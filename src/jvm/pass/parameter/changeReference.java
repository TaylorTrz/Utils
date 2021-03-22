package jvm.pass.parameter;

/**
 * 掌握java参数传值，值类型数据在stack上，引用类型数据在heap上，而所有的函数传值都是传递的值（引用类型传递的是内存地址）
 * 简单理解：如果传递值类型，那么数据不会被修改，除非return；如果引用类型会被修改内存
 */

import java.util.*;

public class changeReference {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("im");
        int[] key = {0, 1, 2};

        changeReference(sb, key);
        System.out.println(sb.toString() + ':' + key[0]);
    }

    public static void changeReference(StringBuffer sb, int[] key) {
        sb.append("iron man");
        key[0] = 888;
    }
}
