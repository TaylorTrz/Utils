package singleton20190826;

/**
 * 饿汉式
 * 常用，效率高，但是容易产生垃圾对象
 */

public class Singleton3 {
    private static Singleton3 instance = new Singleton3();
    private Singleton3 (){}
    public static Singleton3 getInstance(){
        return instance;
    }
}
