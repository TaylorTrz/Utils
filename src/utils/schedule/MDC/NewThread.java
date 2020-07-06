package utils.schedule.MDC;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class NewThread<T> extends Thread implements Runnable, Callable<T>, Executor {

    private T thread;

    public NewThread(T thread) {
        this.thread = thread;
    }

    @Override
    public void run() {

    }

    @Override
    public void start() {

    }

    @Override
    public T call() {
        return null;
    }


    @Override
    public void execute(Runnable command) {

    }
}
