package lambda.validate;

import java.util.*;

public class Lambda {
    public static void main(String[] args) {
//        PrintClassMsg printClassMsg = new PrintClassMsg("test");
        PrintMsg printMsg = PrintClassMsg::print2;
        printMsg.print1();
    }


    // 定义有两个函数的接口
    interface PrintMsg {
        void print1() ;
//        void print2(String message) ;
    }

    // 定义类，仿照Thread
    static class PrintClassMsg {

        public PrintClassMsg(Object test) {
        }


        public   static void print1(String message) {System.out.println("he is here!");}

        public  static void print2() {print1("true");System.out.println("i am here...");}
    }
}

