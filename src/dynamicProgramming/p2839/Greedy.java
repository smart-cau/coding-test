// 백준 2839. 설탕 배달
// https://www.acmicpc.net/problem/2839
package dynamicProgramming.p2839;

import java.util.Scanner;

public class Greedy {
    static Scanner sc = new Scanner(System.in);
    static int N;

    public static void main(String[] args) {
        N = sc.nextInt();
        pro();
    }

    static void pro() {
        System.out.println(minCount());
    }

    static int minCount() {
        int count = 0;

        while (N >= 0) {
            if (N % 5 == 0) {
                count += N / 5;
                return count;
            }
            if (N >= 3) {
                ++count;

            }
            N -= 3;
            if (N < 0)
                count = -1;
        }
        return count;
    }
}
