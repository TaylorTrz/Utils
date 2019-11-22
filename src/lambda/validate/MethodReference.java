package lambda.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * @details  当函数式接口的实现恰好是一个方法的调用，那么::
 * 静态方法的方法引用
 * 非静态方法的方法引用
 * 构造函数的方法引用
 * @date 20191104 17:00
 * @notification 引用的必须是无参方法
 */

public class MethodReference {
    public static void main(String[] args) {
        OuterInterface oi = () -> InnerClass.staticMethodReference();
        oi.accept();

        oi = InnerClass::staticMethodReference;
        oi.accept();

        InnerClass ic = new InnerClass();
        oi = ic::nonstaticMethodReference;
        oi.accept();

        oi = InnerClass::new;
        System.out.println("构造方法Lambda方法引用: " + oi.getClass().getName());

    }



}
class InnerClass {
    // 静态方法
    public static void staticMethodReference() {
        System.out.println("status: 静态方法调用");
    }

    // 实例方法
    public void nonstaticMethodReference() {
        System.out.println("status: 非静态方法调用");
    }

    // 构造方法
    public void InnerClass() {

    }


}

interface OuterInterface {
    void accept();
}
