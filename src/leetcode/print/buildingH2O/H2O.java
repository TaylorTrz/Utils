package leetcode.print.buildingH2O;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author taoruizhe
 * @leetcode building H2O
 * 描述：
 * 输入一系列氧氢元素字符串，然后按照指定序列输出H2O字符串
 * <p>
 * 思考：
 * 一个氧线程，在没有氢线程等待的情况下，必须等待两个氢线程
 * 一个氢线程，在没有线程等待的情况下，必须等待一个氢和一个氧线程，不分先后
 * @date 2020/03/18
 */
public class H2O {
    private int hydrogenCount;
    public static  int count;

    public static final Object LOCK1 = new Object();

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (LOCK1) {

            if (hydrogenCount < 2 && count < 6) {
                hydrogenCount++;
                count++;
                // releaseHydrogen.run() outputs "H"
                releaseHydrogen.run();
                LOCK1.notifyAll();
            } else {
                LOCK1.wait(1);
            }
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (LOCK1) {

            if (hydrogenCount == 2 && count < 6) {
                hydrogenCount = 0;
                count++;
                // releaseOxygen.run() outputs "O"
                releaseOxygen.run();
                LOCK1.notifyAll();
            } else {
                LOCK1.wait(1);
            }
        }
    }



    //----------------------------
    // 信号量
    //----------------------------
    private Semaphore h = new Semaphore(2);
    private Semaphore o = new Semaphore(0);

    public void hydrogen1(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen1(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        releaseOxygen.run();
        h.release(2);
    }


    public static void main(String[] args) {

        H2O h2o = new H2O();
        while (count < 6) {
            new Thread(() -> {
                try {
                    h2o.hydrogen(new Thread(() -> {
                        System.out.println("H");
                    }));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    h2o.oxygen(new Thread(() -> {
                        System.out.println("O");
                    }));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
