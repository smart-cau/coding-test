// 백준 15829번. Hashing
// https://www.acmicpc.net/problem/15829
// bronze 2
package dataStructure.hash.p15829;

import java.util.HashMap;
import java.util.Scanner;

class Main {

    static Scanner sc = new Scanner(System.in);
    static int n;
    static String str;
    static HashMap<Character, Integer> charToInt = new HashMap<>();
    static long result = 0;
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
        initCharToInt();
        for (int i = 0; i < n; i++) {
            char character = str.charAt(i);
            int mappingValue = charToInt.get(character);
            result += mappingValue * powerMagicNumber(i);
        }

        System.out.println(result);
    }

    static void initCharToInt() {
        int aInASCII = 97;
        for (int mappingValue = 1; mappingValue <= 26; mappingValue++) {
            charToInt.put((char) (aInASCII + mappingValue - 1), mappingValue);
        }
    }

    static long powerMagicNumber(long times) {
        long pow = 1;
        for (int i = 1; i <= times; i++) {
            pow *= MAGIC_NUMBER;
        }
        return pow;
    }

}