// 백준 17298번. 오큰수
// https://www.acmicpc.net/problem/17298
// gold 4
package dataStructure.stack.p17298;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {

    static int N;
    static int[] sequence;
    static Deque<Integer> stack = new ArrayDeque<>();
    static int[] result;
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = sc.nextInt();
        sequence = new int[N];
        for (int i = 0; i < N; i++) {
            sequence[i] = sc.nextInt();
        }
    }

    static void solution() {
        result = new int[N];
        for (int i = 0; i < N; i++) {
            int element = sequence[i];

            while (!stack.isEmpty() && sequence[stack.peek()] < element) {
                result[stack.peek()] = element;
                stack.pop();
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            result[stack.peek()] = -1;
            stack.pop();
        }

        // print result
        for (int nge : result) {
            sb.append(nge + " ");
        }
        System.out.println(sb);
    }
}
