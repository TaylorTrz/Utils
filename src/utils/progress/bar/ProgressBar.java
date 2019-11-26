package utils.progress.bar;

/**
 * @details 打印进度条：类似linux的进度条打印
 * key: '\b'退格
 * key:  System.out.printf("%+8.3f\n", 3.14) 宽度8，保留3位有效数字
 * @link 1. https://www.cnblogs.com/liuwu265/p/4100092.html 2. https://www.ctolib.com/topics-61227.html
 */

public class ProgressBar extends Thread{
    private static int elapsed = 0;

    public static synchronized void print() {
        StringBuffer str1 = new StringBuffer("Starting:");
        StringBuffer str2 = new StringBuffer("\b\b\b\b\b\b\b\b\b\b\b\b\b");
        for (int i = 0; i < 100; i++) {
            str1.append("-");
            str2.append("\b");
        }
        try {
            for(int elapsed = 0; elapsed < 100; elapsed++) {
                Thread.sleep(elapsed*2);
                str1.replace(elapsed + 9, elapsed + 11, "=>");
                System.out.print(str2.toString() + str1.toString() );
                System.out.printf("%3d", elapsed + 1);
                System.out.print("%");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // to multi threads
        print();
    }

    public static void main(String... args) {
        Runnable progress1 = ProgressBar::print;
        new Thread(progress1).start();

    }
}
