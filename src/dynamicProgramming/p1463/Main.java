// 백준 1463. 1로 만들기
// https://www.acmicpc.net/problem/1463
package dynamicProgramming.p1463;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int[] dy;

    public static void main(String[] args) {
        N = sc.nextInt();
        pro();
    }

    static void pro() {
        dy = new int[(int) Math.pow(10, 6) + 1];
        dy[1] = 0;
        dy[2] = 1;
        dy[3] = 1;
        for (int i = 4; i <= N; i++) {
            dy[i] = count(i);
        }

        System.out.println(dy[N]);
    }

    static int count(int n) {
        if (n % 6 == 0)
            return Math.min(dy[n / 3], Math.min(dy[n / 2], dy[n - 1])) + 1;
        if (n % 3 == 0)
            return Math.min(dy[n - 1], dy[n / 3]) + 1;
        if (n % 2 == 0)
            return Math.min(dy[n - 1], dy[n / 2]) + 1;
        return dy[n - 1] + 1;
    }
}
