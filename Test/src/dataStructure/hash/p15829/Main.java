// 백준 15829번. Hashing
// https://www.acmicpc.net/problem/15829
// bronze 2
package dataStructure.hash.p15829;

import java.math.BigInteger;
import java.util.Scanner;

class Main {

    static Scanner sc = new Scanner(System.in);
    static int n;
    static final int M = 1234567891;
    static String str;
    static BigInteger result = new BigInteger("0");
    static int MAGIC_NUMBER = 31;

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        n = sc.nextInt();
        str = sc.next();
        sc.close();
    }

    static void solution() {
        for (int i = 0; i < n; i++) {
            int charValue = str.charAt(i) - 'a' + 1;

            result = result.add(BigInteger.valueOf(charValue).multiply(BigInteger.valueOf(31).pow(i)));
        }

        System.out.println(result.remainder(BigInteger.valueOf(M)));
    }

    static long powerMagicNumber(long times) {
        long pow = 1;
        for (int i = 1; i <= times; i++) {
            pow *= MAGIC_NUMBER;
        }
        return pow;
    }

}