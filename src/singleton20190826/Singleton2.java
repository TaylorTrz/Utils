package singleton20190826;

/**
 * 懒汉式，线程安全
 * 第一次调用才初始化，避免内存浪费
 * 加锁synchronized保证单例，会影响效率
 */

public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2 (){}

    public static synchronized Singleton2 getInstance(){
        if (instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
