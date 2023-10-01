// 백준 2747번 - 피보나치 수
// https://www.acmicpc.net/problem/2747

import java.util.Scanner;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int MAX_N = 45 + 1;
    static int[] cache = new int[MAX_N];

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = sc.nextInt();
    }

    static void pro() {
        System.out.println(fibo(N));
    }

    static int fibo(int n) {
        if (n == 1 || n == 2)
            return 1;

        if (cache[n] != 0)
            return cache[n];

        cache[n] = fibo(n - 1) + fibo(n - 2);
        return cache[n];
    }
}