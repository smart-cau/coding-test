// 백준 1351번. 무한 수열
// https://www.acmicpc.net/problem/1351
// gold 5
package dataStructure.hash.p1351;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static long n, p, q;
    static Map<Long, Long> dy = new HashMap<>() {
        {
            put(0l, 1l);
        }
    }; // init value A(0) = 1
    static long result;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        n = sc.nextLong();
        p = sc.nextLong();
        q = sc.nextLong();
    }

    static void solution() {
        System.out.println(sequence(n));
    }

    static long sequence(long i) {

        if (dy.get(i) == null) {
            long term = sequence(i / p) + sequence(i / q);
            dy.put(i, term);
        }

        return dy.get(i);
    }
}
