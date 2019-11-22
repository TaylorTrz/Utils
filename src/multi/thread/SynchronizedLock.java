package multi.thread;

import java.util.concurrent.*;

public class SynchronizedLock {
    private static byte[] lockByte = new byte[0];
    private static int i;

    public static void main(String[] args) {
        ThreadTest thread0 = new ThreadTest(20);
        ThreadTest thread1 = new ThreadTest(4);
        try {
            thread0.sleep(10000);
            new Thread(thread0).start();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {

        }

        new Thread(thread0).start();

//        thread1.start();
    }

    /**
     * @details synchronized有三种加锁的地方：普通方法、静态方法、代码块
     */
    static class ThreadTest extends Thread {
        private int i = 100;
        private static Object lock = new Object();

        ThreadTest(int i) {
            this.i = i;
        }

        public void run() {
            dummyRun(i);
        }

        // 测试在同对象实例中，调用不同的synchronized方法，看看能否调用成功
        public  synchronized void dummyRun(int i) {
//            synchronized (lock) {
            while (i > 0) {
                try {
                    Thread.sleep(500);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " : " + "method dummyRun print " + i--);
            }
//            }
        }

        public void mockRun() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " : " + "mockRun");
            }
        }

    }
}
