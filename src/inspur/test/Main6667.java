package inspur.test;

import java.math.BigInteger;
import java.util.Scanner;

public class Main6667 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        long result = 1;
        BigInteger mul = new BigInteger("3");
        if (num > 4) {
            while (num > 4) {
                num = num - 3;
                result *= 3;
            }
            if (num > 0) {
                result *= num;
            }
        } else {
            return;
        }
        System.out.println(result);

    }
}
