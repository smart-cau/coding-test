// 백준 2747번 - 피보나치 수
// https://www.acmicpc.net/problem/2747

import java.util.Scanner;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int MAX_N = 45 + 1;

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = sc.nextInt();
    }

    static void pro() {
        int[] dy = new int[MAX_N];
        dy[0] = 0;
        dy[1] = 1;
        for (int fibo = 2; fibo < MAX_N; fibo++) {
            dy[fibo] = dy[fibo - 1] + dy[fibo - 2];
        }
        System.out.println(dy[N]);
    }
}