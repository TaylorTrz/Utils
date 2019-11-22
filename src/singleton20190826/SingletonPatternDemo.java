package singleton20190826;

/**
 * 单例模式
 * 分为6种方式，其中懒汉式不推荐，一般用饿汉式
 * 最特殊情况，用第4种DCL方式
 * 明确要实现lazy loading，使用第5种
 * 反序列化创建对象，用第6种枚举方式
 */

public class SingletonPatternDemo {
    public static void main(String[] args){
        // 尝试用普通方法进行实例化，直接报错
//        SingleObject instance = new SingleObject();
        SingleObject instance = SingleObject.getInstance();
        instance.showMessage();
    }
}
