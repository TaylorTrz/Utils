package leetcode.print.FooBar.Alternately;

/**
 * @description 交替打印FooBar：给定整数N，交替打印N个FooBar。
 * @details 难点：如何进行有效阻塞
 * @details 奇怪：wait()总是死锁，而wait(1)提交成功？
 * @date 20191022
 */

class FooBar {
    private int n;
    private int keyFoo = 0;
    private int keyBar = 0;
    private static Object lockA = new Object();
    private static Object lockB = new Object();

    public FooBar(int n) {
        this.n = n;
    }

    private void setKeyFoo(int i) {
        this.keyFoo = i;
    }

    private void setKeyBar(int i) {
        this.keyBar = i;
    }

    // printFoo.run() outputs "foo". Do not change or remove this line.
    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized (lockA) {
            for (int i = 0; i < n; i++) {
                try {

                    while (i != keyBar) {
                        lockA.wait(1);
                    }
                    printFoo.run();
                    setKeyFoo(i + 1);
                    lockB.notifyAll();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    // printBar.run() outputs "bar". Do not change or remove this line.
    public void bar(Runnable printBar) throws InterruptedException {
        synchronized (lockB) {
            for (int i = 0; i < n; i++) {
                // 如果i >= key，则不能运行，B一直处于wait状态，但是也不释放A的锁
                try {

                    while (i != keyFoo - 1) {
                        lockB.wait(1);
                    }
                    printBar.run();
                    setKeyBar(i + 1);
                    lockA.notifyAll();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @resolve wait()方法，让当前线程交出对象的锁，然后进入等待状态，并且会释放当前对象的锁，所以会出现上述错误
     * @correction 两个实例共用一把锁，然后wait、notify都对应一把锁
     * @correction 为什么wait(1)就可以呢？实际上wait(1)过程中，两个线程相互竞争，给了另外一个线程一个机会
     */

}
