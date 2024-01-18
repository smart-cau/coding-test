// 백준 6198번. 옥상정원 꾸미기(Monotonic stack)
// https://www.acmicpc.net/problem/6198
// gold 5
package dataStructure.stack.p6198;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {

    static int N;
    static int[] heights;
    static Deque<Integer> stack = new ArrayDeque<>();
    static Scanner scan = new Scanner(System.in);
    static int result = 0;

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = scan.nextInt();
        heights = new int[N];

        for (int i = 0; i < N; i++) {
            heights[i] = scan.nextInt();
        }
    }

    static void solution() {
        for (int i = 0; i < N; i++) {
            int height = heights[i];

            while (!stack.isEmpty() && stack.peek() <= height) {
                result += stack.size() - 1;
                stack.pop();
            }
            stack.push(height);
            if (!stack.isEmpty() && stack.peek() > height) {
                // ++result;
            }
        }
        while (!stack.isEmpty()) {
            result += stack.size() - 1;
            stack.pop();
        }
        System.out.println(result);
    }
}
