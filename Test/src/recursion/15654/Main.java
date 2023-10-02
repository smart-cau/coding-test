// 백준 15654번. N과 M (5)
// https://www.acmicpc.net/problem/15654
// tree DFS로도 풀어보기

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int M;
    static int[] numbers;
    static int[] output;
    static boolean[] visited;

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = sc.nextInt();
        M = sc.nextInt();

        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
    }

    static void pro() {
        Arrays.sort(numbers);
        visited = new boolean[N];
        output = new int[M];

        visit(0);
    }

    static void visit(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = numbers[i];
                visit(depth + 1);
                visited[i] = false;
            }

        }
    }
}
