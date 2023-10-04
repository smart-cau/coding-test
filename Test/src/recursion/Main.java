// 백준 18511. 큰 수 구성하기
// https://www.acmicpc.net/problem/18511
package recursion;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int number;
    static int[] K;

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = sc.nextInt();
        number = sc.nextInt();
        K = new int[number];
        for (int i = 0; i < number; i++) {
            K[i] = sc.nextInt();
        }
    }

    static void pro() {
        Arrays.sort(K);
        solution(N);
        System.out.println(sb.toString());
    }

    static void solution(int n) {

        if (n <= 10) {
            sb.append(getProperK(n));
            return;
        }
        int Nlength = getLength(n);
        int frontN = n / (int) Math.pow(10, Nlength - 1);
        int frontK = getProperK(frontN);
        if (frontK == -1) {
            for (int i = 0; i < Nlength - 1; i++) {
                sb.append(K[number - 1]);
            }
            return;
        }
        sb.append(frontK);
        for (int i = 0; i < Nlength - 1; i++) {
            sb.append(K[number - 1]);
        }
    }

    static int getLength(int n) {
        return Integer.toString(n).length();
    }

    static int getProperK(int n) {
        int notFound = -1;
        for (int i = number - 1; i >= 0; i--) {
            if (K[i] <= n)
                return K[i];
        }
        return notFound;
    }

}
