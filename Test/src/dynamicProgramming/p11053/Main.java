// 백준 11053. 가장 긴 증가하는 부분 수열
// https://www.acmicpc.net/problem/11053
// silver 2
package dynamicProgramming.p11053;

import java.util.Scanner;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int[] seq;
    static int[] dy;
    static int max = 1;

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = sc.nextInt();
        seq = new int[N];

        for (int i = 0; i < N; i++) {
            seq[i] = sc.nextInt();
        }
    }

    static void pro() {
        dy = new int[N];
        dy[N - 1] = 1;
        if (N > 1)
            for (int base = N - 2; base >= 0; base--) {
                for (int compare = base + 1; compare < N; compare++) {
                    if (seq[base] < seq[compare] &&
                            dy[base] <= dy[compare]) {
                        dy[base] = dy[compare] + 1;
                    }
                }
                if (dy[base] == 0)
                    dy[base] = 1;
                if (max < dy[base])
                    max = dy[base];
            }
        System.out.println(max);
    }
}