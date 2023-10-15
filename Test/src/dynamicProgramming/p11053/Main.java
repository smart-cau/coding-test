// 백준 11053. 가장 긴 증가하는 부분 수열
// https://www.acmicpc.net/problem/11053
package dynamicProgramming.p11053;

import java.util.Scanner;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int[] numbers;
    static int[] dy;
    static int max = 1;

    // static int[] sequence;
    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = sc.nextInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
    }

    static void pro() {
        dy = new int[N];
        dy[N - 1] = 1;
        for (int index = N - 1; index >= 0; index--) {
            dy[index] = 1; // init value as 1
            for (int j = index + 1; j < N; j++) {
                if (j == N)
                    continue;
                if (numbers[index] < numbers[index + 1]) {
                    dy[index] = dy[index + 1] + 1;
                    continue;
                }
                
            }
            if (max < dy[index])
                max = dy[index];
        }
        System.out.println(max);
    }

    // static int maxSequence(int index, int baseNumber) {
    // if (index == N - 1)
    // return 1;

    // if (numbers[index] < numbers[index + 1])
    // dy[index] = dy[index + 1] + 1;

    // if (baseNumber < numbers[index])
    // return dy[index] + 1;

    // return maxSequence(++index, baseNumber);
    // }
}