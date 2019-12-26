package utils.time;

import org.junit.Test;
import utils.stream.StreamAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

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
        return formatter.format(localDateTime);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CUSTOMED_PATTERN);
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
        Date date = Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
        return date;
    }

    public static LocalDateTime UTC2CST(LocalDateTime UTCLocal) {
        return date2Local(local2Date(UTCLocal));
    }

    // convert time zone test
    @Test
    public void UTC2CSTTest() {
        System.out.println(local2Str(UTC2CST(LocalDateTime.now())));
    }


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
        System.out.println(getCurrent());
    }
}
