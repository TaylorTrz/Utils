package utils.monitor;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author taoruizhe
 * @version 2020/01/07
 * <p>
 * 向日葵sunlogin remote control monitor
 * 1.监控，查看远程连接的时间
 * 2.定时，定时执行监控任务
 * 3.传输，向指定IP发送定时监控信息
 */
public class SunMonitorClient {
    private static final String LOG = "H:\\ForOffice\\sunflower\\SunloginClient\\log";
    private Map<String, Long> logMap = new HashMap<>();


    public void monitor() {
        File log = new File(LOG);
        if (!log.isDirectory() && log.length() <= 0) {
            return;
        }

        File[] children = log.listFiles();
        for (File child : children) {
            this.recordLatest(child);
        }

        client(logMap);

//        for (Map.Entry<String, Long> entry: logMap.entrySet()
//        ) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            String date = new Date(entry.getValue())
//                    .toInstant()
//                    .atZone(ZoneId.of(ZoneId.SHORT_IDS.get("CTT")))
//                    .toLocalDateTime()
//                    .format(formatter);
//            System.out.println("文件名：" + entry.getKey() + " + 时间：" + date);
//        }
    }


    private void recordLatest(File child) {
        if (child.getName().contains("desktop.agent")) {
            logMap.put(child.getName(), child.lastModified());
        }
    }

    public void timer() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);
        try {
            Runnable runnable = new Thread(() -> {this.monitor();});
            executorService.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.SECONDS);
        } catch(Exception e ) {
            e.printStackTrace();
        }

    }

    public void client(Map<String, Long> logMap) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();

            Socket client = new Socket(localHost, 8002);
            PrintWriter out  = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            for (Map.Entry<String, Long> entry: logMap.entrySet()
            ) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String date = new Date(entry.getValue())
                        .toInstant()
                        .atZone(ZoneId.of(ZoneId.SHORT_IDS.get("CTT")))
                        .toLocalDateTime()
                        .format(formatter);
                out.println("文件名：" + entry.getKey() + " + 时间：" + date);
                System.out.println("服务端回传：" + in.readLine());
            }

            client.close();

        } catch (UnknownHostException UHE) {
            UHE.printStackTrace();
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }

    }


    // @Test无法启动定时任务
    public static void main(String[] args) {
        SunMonitorClient sunMonitor = new SunMonitorClient();
        sunMonitor.timer();
    }
}
