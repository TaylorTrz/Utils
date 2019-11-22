package switchCase20190827;

/**
 * @description 实现switch-case与enum枚举类型
 * @date 20190827
 * @details switch-case 的条件实际上在class文件中得到解释，其实按照条件从某一行开始往下执行，所以必须break进行跳出
 */

import java.util.Scanner;

public class SwitchCase {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while (true){
            Day day = Day.MONDAY;
            System.out.print(day.ordinal());
            System.out.println(day.ordinal());
            int i = sc.nextInt();
            switch (i){
                case 0: System.out.println("我是大英雄");
                case 1: {
                    System.out.println("他也是大英雄");
                    break;
                }
                case 2: {
                    System.out.println("you re a girl");
                    break;
                }
                default: System.out.println("nothing");
            }
        }

    }

    public enum Day{
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
}
