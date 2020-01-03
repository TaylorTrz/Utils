package utils.schedule;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static utils.schedule.Task.TIMER;

/**
 * 定时任务
 *
 * @author taoruizhe
 * @date 2019/12/30 10:30
 */

public class ScheduledJobs {
    private static final Long SerialNumberUID = 31L;
    private static ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

    public void printEvery10sec(Task task) {
        switch (task) {
            case TIMER: {
                TIMER.action();
                System.out.println(TIMER.getId() + TIMER.getType());
                break;
            }
            case SCHEDULED: {
                Task.SCHEDULED.action();
                System.out.println(TIMER.getId() + TIMER.getType());
                break;
            }
            case QUARTZ: {
                Task.QUARTZ.action();
                System.out.println(TIMER.getId() + TIMER.getType());
                break;
            }
            case SPRING:
                Task.SPRING.action();
                System.out.println(TIMER.getId() + TIMER.getType());
        }
    }


    @Test
    public void test() {
        printEvery10sec(Task.SCHEDULED);
    }

    @Test
    public void listMethods() {
        try {
            Arrays.stream(Class.forName("java.util.concurrent.ScheduledExecutorService").getDeclaredMethods()).distinct().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
