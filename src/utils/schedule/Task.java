package utils.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @link [枚举类型替换If/Switch结构] https://www.jdon.com/51857
 * @link [定时任务] https://www.cnblogs.com/zhangbin1989/p/9294114.html
 */

public enum Task {
    TIMER(1, "Timer") {
        @Override
        public void action() {
            System.out.println("------Timer---------");

            // 延迟1s，周期2s
            new Timer("testTimer").schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("task executed");
                }
            }, 1000, 2000);

            // 开始时间date，周期2s
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            try {
                Date date = dateFormat.parse("2018-07-11 12:00:00.000");
                new Timer("testTimer1").scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("task executed periodically");
                    }
                }, date, 2000);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    },
    SCHEDULED(2, "Scheduled") {
        @Override
        public void action() {
            System.out.println("------ScheduledExecutorService---------");

            // 延迟1s
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("scheduled executor service is running");
                }
            }, 1, TimeUnit.SECONDS);

            // 延迟1s，周期1s
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println("scheduled executor service is running...");
                }
            }, 1, 1, TimeUnit.SECONDS);
        }
    },
    SPRING(3, "Spring"){
        @Override
        public void action() {
            System.out.println("------SpringTask---------");
        }
    },
    QUARTZ(4, "Quartz"){
        @Override
        public void action() {
            System.out.println("------Quartz---------");
        }
    };


    Task(Integer id, String type) {
    }

    public abstract void action();


}
