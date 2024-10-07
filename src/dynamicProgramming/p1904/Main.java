// 백준 1904. 01타일
// https://www.acmicpc.net/problem/1904
package dynamicProgramming.p1904;

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
        int length = N + 1;
        dy = new int[length];
        dy[1] = 1;
        if (N >= 2)
            dy[2] = 2;
        if (N > 2)
            for (int i = 3; i < length; i++) {
                dy[i] = (dy[i - 2] + dy[i - 1]) % 15746;
            }

        System.out.println(dy[N]);
    }
}
