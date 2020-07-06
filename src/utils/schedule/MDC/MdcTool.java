package utils.schedule.MDC;

import org.slf4j.MDC;

/**
 * 请求ID改造，改进构造多线程中MDC的传入方式
 * @author taoruizhe
 * @date   2020/07/06
 */
public interface MdcTool<T> {
     ThreadLocal<MDC> mdc = new ThreadLocal<>();

     NewThread getThread(T thread);


}
