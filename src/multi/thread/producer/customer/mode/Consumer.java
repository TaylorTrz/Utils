package multi.thread.producer.customer.mode;

import java.util.List;

/**
 * notify/wait方法
 */
public class Consumer implements Runnable{
    private List<PCData> queue;

    public Consumer(List<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                PCData data = null;
                synchronized(queue) {
                    if (queue.size() == 0) {
                        queue.wait();
                        queue.notifyAll();
                    }
                    data = queue.remove(0);
                }
                System.out.println(Thread.currentThread().getName() + " consumes: " + data.get() + " and result: " + (data.get() * data.get()));
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }


}
