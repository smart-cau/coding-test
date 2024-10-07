// 백준 9184. 신나는 함수 실행
// https://www.acmicpc.net/problem/9184
package dynamicProgramming.p9184;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int[][][] dy = new int[21][21][21];

    public static void main(String[] args) {
        pro();
        System.out.print(sb.toString());
    }

    static void pro() {
        while (true) {
            int[] numbers = new int[3];
            for (int i = 0; i < 3; i++) {
                numbers[i] = sc.nextInt();
            }
            if (isInputEnd(numbers[0], numbers[1], numbers[2]))
                break;
            sb.append(String.format("w(%d, %d, %d) = ", numbers[0], numbers[1], numbers[2]))
                    .append(w(numbers[0], numbers[1], numbers[2]))
                    .append("\n");
        }
    }

    static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0)
            return 1;
        if (a > 20 || b > 20 || c > 20)
            return w(20, 20, 20);

        int memo = dy[a][b][c];

        if (memo != 0)
            return memo;

        if (a < b && b < c) {
            dy[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        } else
            dy[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
        return dy[a][b][c];

    }

    static boolean isInputEnd(int a, int b, int c) {
        return a == -1 && b == -1 && c == -1;
    }
}
