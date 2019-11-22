package singleton20190826;

/**
 * 枚举，实现单例模式的最优方法
 * 避免多线程同步，支持序列化，防止反序列化重新创建新对象，可惜实际用的比较少
 */

public enum Singleton6 {
    INSTANCE;
    public void whateverMethod(){}
}
