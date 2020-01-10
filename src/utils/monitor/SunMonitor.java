package utils.monitor;

import com.sun.istack.NotNull;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author taoruizhe
 * @version 2020/01/07
 * <p>
 * 向日葵sunlogin remote control monitor
 * 1.监控，查看远程连接的时间
 * 2.定时，定时执行监控任务
 * 3.传输，向指定IP发送定时监控信息
 */
public class SunMonitor {
    private static final String LOG = "H:\\ForOffice\\sunflower\\SunloginClient\\log";
    private Map<String, Long> logMap = new HashMap<>();

    public void monitor() throws IOException {
        File log = new File(LOG);
        if (!log.isDirectory() && log.length() <= 0) {
            return;
        }

        File[] children = log.listFiles();
        for (File child : children) {
            this.recordLatest(child);
        }
    }


    private void recordLatest(File child) {
        if (child.getName().contains("desktop.agent")) {
            logMap.put(child.getName(), child.lastModified());
        }
    }


    @Test
    public void monitorTest() {
        try {
            monitor();
            for (Map.Entry<String, Long> entry: logMap.entrySet()
            ) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String date = new Date(entry.getValue())
                        .toInstant()
                        .atZone(ZoneId.of(ZoneId.SHORT_IDS.get("CTT")))
                        .toLocalDateTime()
                        .format(formatter);
                System.out.println(entry.getKey() + " ：： " + date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
