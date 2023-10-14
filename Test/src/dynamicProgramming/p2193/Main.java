// 백준 2193번 - 이친수
// https://www.acmicpc.net/problem/2193
package dynamicProgramming.p2193;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static Long[] dy;

    public static void main(String[] args) {
        N = sc.nextInt();
        pro();
    }

    static void pro() {
        dy = new Long[N + 1];
        System.out.println(pinaryNumber(N));

        // pinaryNumber2(N);
        // System.out.println(dy[N]);
    }

    static Long pinaryNumber(int n) {
        if (n == 0)
            return 0L;
        if (n == 1)
            return 1L;
        if (dy[n] == null) {
            dy[n] = pinaryNumber(n - 1) + pinaryNumber(n - 2);
        }
        return dy[n];
    }

    static void pinaryNumber2(int n) {
        dy[0] = 0L;
        dy[1] = 1L;
        if (n >= 2)
            for (int i = 2; i <= n; i++) {
                dy[i] = dy[i - 1] + dy[i - 2];
            }
    }
}
