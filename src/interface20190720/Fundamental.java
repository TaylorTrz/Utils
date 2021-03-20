package interface20190720;

public class Fundamental {
    public static void main(String[] args) {
        Chinese c = new Chinese();
        American a = new American();
        a.say();
        c.say();
    }

}

interface Coding {
    int MAX_LENGTH = 256;

    void printYourCodeLength();
}

class People {
    String name;

    void people() {
    }

    void say() {
        System.out.println("whoooooo");
    }
}

class American extends People implements Coding {
    public void printYourCodeLength() {
        System.out.println(MAX_LENGTH);
    }

    void say() {
        super.say();
        System.out.println("HOOOOOO");
    }
}

class Chinese extends People implements Coding {
    public void printYourCodeLength() {
        System.out.println(MAX_LENGTH + (int) 256);
    }

    public void say() {
        System.out.println("shut up!");
    }
}
