package inspur.test;

import java.math.BigInteger;
import java.util.Scanner;

public class Main666 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        BigInteger bigInteger = new BigInteger("1");
        ///double result = 1;
        BigInteger mul = new BigInteger("3");
        if (num > 4) {
            while (num > 4) {
                num = num - 3;
                bigInteger = bigInteger.multiply(mul);
            }
            if (num > 0) {
                bigInteger = bigInteger.multiply(new BigInteger("" + num));
            }
        } else {
            return;
        }
        System.out.println(bigInteger.toString());

    }
}
