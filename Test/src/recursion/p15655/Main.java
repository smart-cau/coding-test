// 백준 15655번. N과 M(6)
// https://www.acmicpc.net/problem/15655
package recursion.p15655;

import java.util.Arrays;
import java.util.Scanner;

class Main {

    static Scanner sc = new Scanner(System.in);
    static int N;
    static int M;
    static int[] numbers;
    static int[] comp;
    static boolean[] tempVisited;
    static boolean[] visited;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = sc.nextInt();
        M = sc.nextInt();
        numbers = new int[N];
        comp = new int[M];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
    }

    static void pro() {
        Arrays.sort(numbers);
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            comp[0] = numbers[i];
            composition(i, 0);
        }

        System.out.print(sb.toString());
    }

    static void composition(int index, int count) {

        if (count == M - 1) {
            for (int i = 0; i < M; i++) {
                sb.append(comp[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        count += 1;

        // 기존 코드에서 i == 0으로 둬서 해맸음...
        for (int i = index; i < N; i++) {
            if (!visited[i] && i != index) {
                visited[i] = true;
                comp[count] = numbers[i];
                composition(i, count);
                visited[i] = false;
            }
        }
    }
}