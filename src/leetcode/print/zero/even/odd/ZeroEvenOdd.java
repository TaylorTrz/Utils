package leetcode.print.zero.even.odd;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @details 打印零和奇偶数。ZeroEvenOdd示例会传递给3个不同的线程，线程A调用zero，以此类推，最后打印01020304...
 * @problems 总是出现逻辑正确，多出一位数的结果。应该是多线程调用过程的问题，用If来进行控制...
 * @date 20191024
 */

class ZeroEvenOdd {
    private int n;
    private final static Object lock = new Object();
    private static boolean key = false;
    private int i = 1;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // i have passed invalid value
    public void zero(IntConsumer printNumber) throws InterruptedException {

        while (i <= n) {
            synchronized (lock) {
                while (key) {
//                    System.out.println(" 输出0时第一次： " + i);
                    lock.wait();
//                    System.out.println(" 输出0时第二次： " + i);
                }
                if (i <= n) {
                    printNumber.accept(0);
                }
                key = true;
                lock.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        while (i <= n) {
            synchronized (lock) {
                while (i % 2 != 0 || !key) {
                    lock.wait();
                }
                if (i <= n) {
                    printNumber.accept(i);
                    i++;
                }
                key = false;
                lock.notifyAll();
            }

        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

        while (i <= n) {
            synchronized (lock) {
                while (i % 2 == 0 || !key) {
                    lock.wait();
                }
                if (i <= n) {
                    printNumber.accept(i);
                    i++;
                }
                key = false;
                lock.notifyAll();
            }
        }
    }

    /**
     * @details 信号量解决打印奇偶数问题
     */
    private Semaphore semaphore = new Semaphore(1);

    private void zero1(IntConsumer printNumber) throws InterruptedException {
        while (i <= n) {
            if (i <= n && !key) {
                semaphore.tryAcquire(1, TimeUnit.MILLISECONDS);
                printNumber.accept(0);
                key = true;
                semaphore.release();
            }
        }
    }

    private void odd1(IntConsumer printNumber) throws InterruptedException {
        while (i <= n) {
            if (i % 2 != 0  && key) {
                semaphore.tryAcquire(1, TimeUnit.MILLISECONDS);
                printNumber.accept(i);
                i++;
                key = false;
                semaphore.release();
            }
        }
    }

    private void even1(IntConsumer printNumber) throws InterruptedException {
        while (i <= n) {
            if (i % 2 == 0 && key) {
                semaphore.tryAcquire(1, TimeUnit.MILLISECONDS);
                printNumber.accept(i);
                i++;
                key = false;
                semaphore.release();
            }
        }
    }


    /**
     * @details 定义类与编写的主函数
     */
    // printNumber.accept(x) outputs "x", where x is an integer.
    static class IntConsumer {
        void accept(int i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeo = new ZeroEvenOdd(100);
        IntConsumer printNumber = new IntConsumer();

        Thread thread1 = new Thread(() -> {
            try {
                zeo.zero1(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                zeo.even1(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                zeo.odd1(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
