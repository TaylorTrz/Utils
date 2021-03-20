package inspur.test;

import java.util.HashSet;
import java.util.Scanner;

public class Main2 {

    static HashSet hashSet = new HashSet();
    static HashSet hashSet2 = new HashSet();

    public static boolean isPrime(long num) {
        if (hashSet2.contains(num)) {
            return true;
        }
        if (hashSet.contains(num)) {
            return true;
        }
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            hashSet.add(num);
            return true;
        }
        for (long i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        hashSet.add(num);
        return true;
    }

    public static boolean isPrime2(long num) {
        if (isPrime(num)) {
            while (num > 9) {
                if (num % 10 % 2 == 0)
                    return false;
                num = num / 10;
                if (!isPrime(num)) {
                    return false;
                }
            }
            hashSet2.add(num);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        boolean b = Main2.isPrime2(127L);
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if (num > -1 && num <= 8) {
            long start = 1;
            long end = 1;
            for (int i = 1; i < num; i++) {
                start *= 10;
            }
            end = start * 10 - 1;
            int step = 2;
            if (num == 1) {
                step = 1;
            }
            start++;
            for (long i = start; i < end; i += step) {
                if (Main2.isPrime2(i)) {
                    System.out.println(i);
                }
            }
        } else {
            return;
        }
    }
}

