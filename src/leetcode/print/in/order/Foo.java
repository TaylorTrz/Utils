package leetcode.print.in.order;

import java.util.concurrent.locks.ReentrantLock;

public class Foo {
    private  boolean firstLock = false;
    private  boolean secondLock = false;
    private static ReentrantLock lock = new ReentrantLock();

    public Foo() {

    }

    /**
     * @author taylor
     * @date 20190925
     * @description 执行屏障问题，构造2道屏障，second线程等待first屏障，third线程等待second屏障。然后first线程释放first屏障，second线程会释放second屏障
     * @description 使用线程等待的方式执行屏障，使用释放线程等待的方式实现屏障消除
     */

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            firstLock = true;
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (!firstLock) {
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondLock = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (!secondLock) {
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printThird.run();
            lock.notifyAll();
        }
    }

    static class PrintFirst implements Runnable {
        public void run() {
            System.out.print("one");
        }
    }

    static class PrintSecond implements Runnable {
        public void run() {
            System.out.print("Two");
        }
    }

    static class PrintThird implements Runnable {
        public void run() {
            System.out.print("Three");
        }
    }


    /**
     * date 20190925
     * description 多线程基础实现1
     */
    static class FirstToHead extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("key is running " + i + " times");
            }
        }
    }

    /**
     * date 20190925
     * description 多线程基础实现2
     */
    static class SecondToHead implements Runnable {
        public void run() {
            for (int j = 0; j < 50; j++) {
                System.out.println("mount is running " + j + " times");
            }
        }
    }

    public static void main(String[] args) {
//        Foo f = new Foo();
//        FirstToHead fth = new FirstToHead();
//        fth.start();
//        SecondToHead sth = new SecondToHead();
//        Thread thread = new Thread(sth);
//        thread.start();
        PrintFirst printFirst = new PrintFirst();
        PrintSecond printSecond = new PrintSecond();
        PrintThird printThird = new PrintThird();
        Foo foo = new Foo();
        try {
            foo.first(printFirst);
            foo.third(printThird);
            foo.second(printSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
