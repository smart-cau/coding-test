// 백준 2839. 설탕 배달
// https://www.acmicpc.net/problem/2839
package dynamicProgramming.p2839;

import java.util.Scanner;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int maxCountOf3, maxCountOf5;

    public static void main(String[] args) {
        N = sc.nextInt();
        pro();
    }

    static void pro() {
        maxCountOf3 = (N / 3) + 1;
        maxCountOf5 = (N / 5) + 1;

        int min = Integer.MAX_VALUE;

        for (int countOf3 = 0; countOf3 <= maxCountOf3; countOf3++) {
            for (int countOf5 = 0; countOf5 <= maxCountOf5; countOf5++) {
                if (N - (countOf3 * 3 + countOf5 * 5) == 0) {
                    int totalCount = countOf3 + countOf5;
                    if (min > totalCount)
                        min = totalCount;
                }
            }
        }

        System.out.println(min != Integer.MAX_VALUE ? min : -1);
    }
}