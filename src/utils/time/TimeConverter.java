package utils.time;

import org.junit.Test;
import utils.stream.StreamAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author taoruizhe
 * @date 2019/12/26
 * @description utilities to  convert  the format of time
 */
public class TimeConverter {
    private static final Long SerialVersionUID = 20191226001L;

    private static final String SEQUENTIAL_PATTERN = "yyyyMMddHHmmssSSS";
    private static final String STANDARD_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String CUSTOMED_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * @return formatted current time
     */
    public static String getCurrent() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STANDARD_PATTERN);
        return localDateTime.format(formatter);
    }


    /**
     * @param localDateTime
     * @return formatted date string
     */
    public static String local2Str(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CUSTOMED_PATTERN);
        return formatter.format(localDateTime);
    }


    /**
     * @param dateSequence
     * @return formatted date string
     */
    public static String str2Str(String dateSequence) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STANDARD_PATTERN);
        LocalDateTime localDateTime = LocalDateTime.parse(dateSequence, formatter);
        return formatter.format(localDateTime);
    }

    /**
     *
     * @param date
     * @return  LocalDateTime
     */
    public static LocalDateTime date2Local(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime();
        return localDateTime;
    }

    /**
     *
     * @param localDateTime
     * @return   Date
     */
    public static Date local2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
    }

    /**
     * 改变时区   UTC(GMT)  CST(Asia/Shanghai)
     * @param date  时间
     * @param UTC2CST 从UTC时间转换为CST时间，或正好相反
     * @return
     */
    public static String zoneConverter(LocalDateTime date, boolean UTC2CST) {
        if (!UTC2CST) {
            String before = "Asia/Shanghai";
            String after = "UTC";

        }
        String before = "UTC";
        String after = "Asia/Shanghai";

        ZonedDateTime UTC = date.atZone(ZoneId.of(before));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CUSTOMED_PATTERN)
                .withZone(ZoneId.of(after));
        return formatter.format(UTC);
    }


    /**
     *
     * @param text
     * @param UTC2CST
     * @return
     */
    public static String zoneConverter(String text, boolean UTC2CST) throws ParseException{
        return zoneConverter(LocalDateTime.parse(text), UTC2CST);
    }


    /** --------------------------------
     * @detail LocalDateTime类的常用方法
     * @date 2020/01/06
     * ---------------------------------
     */
    public static LocalDateTime toInstance(int i, Map<String, Integer> timeMap) {
        switch(i) {
            case 0 :
                return LocalDateTime.parse("");
            case 1:
                return LocalDateTime.of(timeMap.get("year"),timeMap.get("month"), timeMap.get("day"), timeMap.get("hour"), timeMap.get("minute"), timeMap.get("second"));
            case 2:
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
            default:
                return LocalDateTime.now();
        }
    }


    // -----------------------------------
    // 测试
    // -----------------------------------

    @Test
    public void CST2UTCTest() {
        Date now = new Date(System.currentTimeMillis());
        System.out.println("CST time is: " + now);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            String UTCDate = formatter.format(now);
            System.out.println("UTC time is: " + UTCDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void getCurrentTest() {
        // 关于时间转换
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime UTC = now.atZone(ZoneId.of("UTC"));
        ZonedDateTime CTT = now.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("UTC时间：" + UTC.toString());
        System.out.println("CTT时间：" + CTT.toString());

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(CUSTOMED_PATTERN)
                                                        .withZone(ZoneId.of("UTC"));
        DateTimeFormatter formatter2 = formatter1.withZone(ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));

        System.out.println(formatter1.format(UTC));
        System.out.println(formatter1.format(CTT));
        System.out.println(formatter2.format(UTC));
        System.out.println(formatter2.format(CTT));
    }

    @Test
    public void str2StrTest() {
        System.out.println(str2Str(String.valueOf(System.currentTimeMillis())));
    }

}
