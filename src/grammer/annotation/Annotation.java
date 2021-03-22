package grammer.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

public class Annotation {
    public static void main(String[] args) {
        Object lock = new Object();
        People xiaoming = new People();
        xiaoming.show();

        synchronized (lock) {
            Dog Alex = new Dog();
            Alex.show();
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.notifyAll();
        }


        System.out.println(System.currentTimeMillis());
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Value {
    int value() default 0;

    String name() default "";

    String gender() default "";
}

class People {
    @Value(100)
    public int number;

    void show() {
        System.out.println(this.number);
    }
}

class Dog {
    @Value(value = 1000, name = "Alex", gender = "male")
//    int value = 10;
    String gender;
    String name;

    void show() {
        System.out.println(this.name + " is " + this.gender);
    }
}




