package singleton;

/**
 * 单例模式的核心特点：private构造函数
 */

public class SingleObject {
    private static SingleObject instance = new SingleObject();

    // 单例模式的特点是只有一个实例化对象，且只能由对象自己实例化自己，并且构造函数为private
    private SingleObject() {
    }

    public static SingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("singleton is created!");
    }

}
