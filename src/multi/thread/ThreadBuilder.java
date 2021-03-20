package multi.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadBuilder {
    private static ReentrantLock lock = new ReentrantLock();
    private static Object sync = new Object();

    /**
     * @description Runnable
     * @date 20191017
     */

    public static class ThreadRunnable implements Runnable {
        public synchronized void run() {

            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + this.getClass().getName() + " : " + i);
            }
        }

        // to synchronized run method
        public synchronized void dummyRun() {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + this.getClass().getName() + " : " + i);
            }
        }
    }

    /**
     * @description Thread
     * @date 20191017
     */
    public static class ThreadOriginal extends Thread {
        public void run() {
            synchronized (sync) {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + this.getClass().getName() + " : " + i);
                }
            }
        }
    }

    /**
     * @description Callable
     * @date 20191017
     */
    public static class ThreadCallable implements Callable {
        public Integer call() {
            synchronized (sync) {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + this.getClass().getName() + " : " + i);
                }

                return 0;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " : start...");

        ThreadRunnable threadRunnable = new ThreadRunnable();
        Thread thread1 = new Thread(threadRunnable);

        ThreadOriginal threadOriginal = new ThreadOriginal();
        Thread thread2 = new Thread(threadOriginal);

        ThreadCallable threadCallable = new ThreadCallable();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(threadCallable);
        Thread thread3 = new Thread(futureTask);

        thread1.start();
        threadRunnable.dummyRun();
        threadRunnable.run();
//        thread2.start();
//        threadOriginal.start();
//        thread3.start();

        try {
            System.out.println("******** " + futureTask.get() + " ********");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        }
    }
}
