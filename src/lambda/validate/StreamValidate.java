package lambda.validate;

import org.junit.Test;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class StreamValidate {
    public static void main(String[] args) {
        // Java8新特性
        List<String> stringLists = Arrays.asList("efg", "", "abc", "bc", "ghij", "", "lmn");
        long count = stringLists.stream().filter(String::isEmpty).count();
        System.out.println("统计空字符串的数量：" + count);

        long length = stringLists.stream().filter(String -> String.length() == 3).count();
        System.out.println("统计长度为3的字符串个数：" + length);

        List<String> filtered = stringLists.stream().filter(String -> !String.isEmpty()).collect(Collectors.toList());
        System.out.println("消除空字符b串：" + filtered);

        String mergedFiltered = stringLists.stream().filter(String -> !String.isEmpty()).collect(Collectors.joining("..."));
        System.out.println("消除并连接剩下字符串：" + mergedFiltered);

        List<Integer> numberLists = Arrays.asList(2, 3, 3, 2, 5, 2, 7);
        count = numberLists.stream().filter(Integer -> Integer >= 5).count();
        System.out.println("大于4的所有整型：" + count);

        List<Integer> squareLists = numberLists.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("映射得到平方集合：" + squareLists);

        IntSummaryStatistics intSummaryStatistics = numberLists.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("序列最大值：" + intSummaryStatistics.getMax());
        System.out.println("序列最低值：" + intSummaryStatistics.getMin());
        System.out.println("序列值总和：" + intSummaryStatistics.getSum());
        System.out.println("序列平均值：" + intSummaryStatistics.getAverage());
        System.out.println("返回10个随机数并排序");
        new Random().ints().limit(10).sorted().forEach(System.out::print);

    }

    /*
        Stream打印类的所有方法
     */
    @Test
    public void getAllMethods() {
        try {
            Arrays.stream(Class.forName("java.time.LocalDateTime").getMethods()).map(Method::getName).distinct().forEach(System.out::println);
            ZonedDateTime zonedDateTime = ZonedDateTime.now();
            System.out.println(zonedDateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDeclareMethods() {
        try {
            Arrays.stream(Class.forName("java.time.ZonedDateTime").getDeclaredMethods()).map(Method::getName).distinct().forEach(System.out::println);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getZoneIdMethods() {
        try {
            Arrays.stream(Class.forName("java.util.Objects").getDeclaredMethods()).map(Method::getName).distinct().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
