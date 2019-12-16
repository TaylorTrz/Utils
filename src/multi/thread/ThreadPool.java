package multi.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private static ThreadLocal<Integer> VAR;

    // FixedThreadPool / ScheduledThreadPool / SingleThreadExecutor
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private static ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            executorService.submit(()
                    ->  {
                for (int j = 0; j < 10; j++) {
                    System.out.print(j + " ");
                }
            });
        }
        executorService.shutdown();

        Thread thread1 = new Thread(()
                -> {
            for (int i = 0; i < 5; i++) {
            arrayBlockingQueue.add(i);}
        });
        thread1.start();
        try {
            thread1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread( () -> {
            if (!arrayBlockingQueue.offer(11)) {
                System.out.println("insert 11 failed");
            }
            try {
                arrayBlockingQueue.put(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread thread2 = new Thread(() -> {
            Integer element;
            System.out.println("out...");
            while ((element = arrayBlockingQueue.poll()) != null) {
                System.out.println(element);
            }
        });
        thread2.start();
    }
}
