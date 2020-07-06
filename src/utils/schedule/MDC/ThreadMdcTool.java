package utils.schedule.MDC;


import org.slf4j.MDC;

import java.util.UUID;

/**
 * MdcTool的实现类，指定泛型类型为Thread
 */
public class ThreadMdcTool implements MdcTool<Thread>{
    private ThreadLocal<MDC> mdc = new ThreadLocal<>();


    @Override
    public NewThread<Thread> getThread(Thread thread) {
        return new NewThread<>(thread);
    }
}
