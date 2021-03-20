package lambda.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * @details 当函数式接口的实现恰好是一个方法的调用，那么::
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

        TransX transX = (x) -> new X().f();
        transX = X::f;
        System.out.println(transX.transform(new X()));
    }
}

class InnerClass {
    private OuterInterface outerInterface;

    // 静态方法
    public static void staticMethodReference() {
        System.out.println("status: 静态方法调用");
    }

    // 实例方法
    public void nonstaticMethodReference() {
        System.out.println("status: 非静态方法调用");
    }

    // 构造方法(显然不是该方法)
    public void InnerClass(OuterInterface outerInterface) {
        this.outerInterface = outerInterface;
    }


}

interface OuterInterface {
    void accept();
}

// 一种特殊情况：未绑定的方法引用，直接将Object(X)作为引用参数
// 使用未绑定的引用时，也就是非静态方法的引用。函数方法的签名（接口中的单个方法）可以不与方法引用的签名完全匹配。 只要引用一个对象即可
class X {
    String f() {
        return "X::f";
    }
}

interface TransX {
    String transform(X x);
}
