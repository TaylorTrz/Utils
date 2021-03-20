package common.classes.usage;

/**
 * 记录常用类
 */

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class RegularClass {
    public static void main(String[] args) {

        // String类
        char a[] = {'a', 'f', 'o', 'o', 'l'};
        String str = new String(a);
        System.out.println(str.equals("afool"));
        System.out.println(str.startsWith("a"));
        System.out.println(str.compareToIgnoreCase("Bdef"));
        System.out.println(Integer.parseInt("12"));
        System.out.println(String.valueOf(100.18137));
        System.out.println(str.contains("fool"));
        System.out.println(str.indexOf('a'));
        System.out.println(str.substring(0, 2));
        System.out.println(String.valueOf(System.currentTimeMillis()));
        // String类、Char[]与Byte[] 不得不说的秘密
        char newChar[] = new char[100];
        str.getChars(0, 4, newChar, 0);
        System.out.println("字符串" + str + "提取到字符数组中：" + newChar[3]);
        System.out.println("字符串" + str + "全部提取：" + new String(str.toCharArray()));
        byte[] newBytes = str.getBytes();
        System.out.println("从字符串中提取字符数组：" + Arrays.toString(newBytes));
        System.out.println("从字符串中提取字符数组（连续）：" + new String(newBytes));


        // Date类 Calendar类
        Date date = new Date();
        System.out.println("现在是北京时间：" + date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR));
        String formattedDate = sdf.format(calendar.getTime());
        System.out.println("日历时间：" + formattedDate);
        Calendar rebackTime = Calendar.getInstance();
        rebackTime.set(1997, 6, 1);
        System.out.println("香港回归时间：" + sdf.format(rebackTime.getTime()));
        // 计算两个日期之间的时间差
        long countTime1 = calendar.getTimeInMillis();
        long countTime2 = rebackTime.getTimeInMillis();
        System.out.println("距离香港回归已经过去" + (countTime1 - countTime2) / (1000 * 60 * 60 * 24) + "天");

        // Scanner类解析字符串
        String conStr = str + "1234";
        Scanner sc = new Scanner(conStr);
        try {
            System.out.println(sc.nextInt());
        } catch (Exception e) {
            System.out.println(sc.next());
        }

        // 试验...的参数传递
        System.out.println(Integer.parseInt("104"));
        int[] b = new int[]{Integer.parseInt("102"), Integer.parseInt("101")};
        apostrophe(b);

    }


    public static class key {
        static final String value = "keyvalue";
    }

    public static int[] returnSth() {
        int[] newInt = new int[]{};
        return newInt;
    }

    // ...表示任意多个参数
    public static void apostrophe(int... intArrays) {
        for (int array : intArrays) {
            System.out.println(array);
        }
    }
}
