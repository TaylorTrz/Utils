package regex;

/**
 * @details 正则表达式练习
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.*;
import java.util.regex.Matcher;

public class RunoobTest {

    /*
    matchers()方法
     */
    public static void main2(String[] args) {
        String content = "I am noob " + "from runoob.com..";
        String pattern = ".*run.*";
        System.out.println(Pattern.matches(pattern, content));
    }

    /*
    捕获组的概念
     */
    public static void main1(String[] args) {
        String line = "This order was placed for QT4000! OK?";
        String patternString = "(\\D*)(\\d+)(.*)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            System.out.println(matcher.groupCount());
        } else {
            System.out.println("Nothing to match");
        }
    }

    /*
    start() 返回上一次由给定组所捕获的子序列的头索引
    end()   返回上一次由给定组所捕获的子序列的尾索引+1
     */
    private static final String REGEX = "\bcat\b";
    private static final String INPUT = "cat cat cat cattie cat";

    public static void main3(String[] args) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);
        int count = 0;

        while (matcher.find()) {
            count++;
            System.out.println(count);
            System.out.println(matcher.start());
            System.out.println(matcher.end());
        }
    }


    /*
    matches() 全序列匹配
    lookingAt() 从首字符，非全匹配
     */
    private static final String REGEX1 = "foo";
    private static final String INPUT1 = "fooooooooooooooooo";
    private static final String INPUT2 = "ooooofoooooooooooo";
    private static Pattern pattern;
    private static Matcher matcher;
    private static Matcher matcher2;

    public static void main4(String[] args) {
        pattern = Pattern.compile(REGEX1);
        matcher = pattern.matcher(INPUT1);
        matcher2 = pattern.matcher(INPUT2);

        System.out.println("lookingAt " + matcher.lookingAt());
        System.out.println("matches " + matcher.matches());
        System.out.println("lookingAt2 " + matcher2.lookingAt());
        System.out.println("matches2 " + matcher2.matches());
    }


    /*
    replaceFirst() 替换首个匹配项
    replaceAll()   替换所有匹配项
     */
    public static void main5(String[] args) {
        String regex = "dog";
        String input = "the dog say hello \n another dogg say hi";
        String replace = "cat";
        pattern = pattern.compile(regex);
        matcher = pattern.matcher(input);
        System.out.println(matcher.replaceAll(replace));
        System.out.println(matcher.replaceFirst(replace));
    }


    /*
    appendReplacement()
    appendTail()
     */
    public static void main6(String[] args) {
        String regex = "a*b";
        String input = "aabfooaabfooabfoobkkk";
        String replace = "-";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);
        StringBuffer stb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stb, replace);
        }
        System.out.println(stb.toString());
        matcher.appendTail(stb);
        System.out.println(stb.toString());
    }


    /*
    练习开始...
     */

    // 邮件
    public static final String REGEX_EMAIL = "^\\w+([+-.]\\w+)*@\\w+\\.\\w+$";
    // 电话号码固定开头15X/13X
    public static final String REGEX_PHONE_NUMBER = "^(15[0-9]|13\\d)\\d{8}$";
    // IP地址
    public static final String REGEX_IP_ADDRESS = "(?:(1?\\d?\\d|2[^5]\\d|25[0-5])\\.){3}(1?\\d?\\d|2[^5]\\d|25[0-5])";
    // 密码
    public static final String PASSWD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\w!\"$%()*+,-./:;<=>?@\\[\\]^_`{|}~]{8,32}$";

    public static void main7(String[] args) {
        String regex = ".*\\\\.*boy$";
        String regex1 = "\\\\*";
        String input = "tom is a \n key!007 a4fjk-abs8-efdc-keys (good) \\ boy";
        String email = "1102350032@qq.com";
        String iphone = "15764257038";
        try {
            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
            String ip = inetAddress.toString().split("/")[1];
            System.out.println(inetAddress.toString());
            String localHostname = InetAddress.getLocalHost().toString();
            Pattern pattern = Pattern.compile(REGEX_IP_ADDRESS);
            Matcher matcher = pattern.matcher(localHostname);
            while(matcher.find()) {
                System.out.println("匹配结果： " + matcher.group());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String input1 = "Lc13yfwpW()[]{}%$*?@|~-+.\"";
        pattern = Pattern.compile(PASSWD_REGEX);
        Matcher matcher = pattern.matcher(input1);
        if (matcher.matches()) {
            System.out.println(input1);
        }
    }


}
