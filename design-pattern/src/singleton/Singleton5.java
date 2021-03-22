package singleton;

/**
 * 登记式/静态内部类
 * 与双检锁DCL功能一样，但是对静态域实现延迟初始化
 * Singleton类被装载，instance不一定被初始化，只有显式调用getInstance方法，才会显式装载SingletonHolder类，从而实例化Instance
 */

public class Singleton5 {
    private static class SingletonHolder {
        private static final Singleton5 instance = new Singleton5();
    }

    private Singleton5() {
    }

    public static final Singleton5 getInstance() {
        return SingletonHolder.instance;
    }
}
