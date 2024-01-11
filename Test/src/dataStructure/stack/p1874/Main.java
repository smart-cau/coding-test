// 백준 1874번. 스택 수열
// https://www.acmicpc.net/problem/1874
// silver 2

package dataStructure.stack.p1874;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {

    static int max;
    static int[] sequence;
    static Deque<Integer> stack = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        max = sc.nextInt();
        sequence = new int[max];
        for (int i = 0; i < max; i++) {
            sequence[i] = sc.nextInt();
        }
    }

    static void solution() {
        stackSequence();

        System.out.println(sb.toString());
    }

    static void stackSequence() {
        // sequence iterator
        int number = 0;
        for (int index = 0; index < max; index++) {
            int element = sequence[index];
            // stack push & pop iterator
            while (true) {
                if (element >= number) {
                    ++number;
                    stack.push(number);
                    sb.append("+\n");
                }
                if (element == stack.peek()) {
                    stack.pop();
                    sb.append("-\n");
                    break;
                }
                if (number == max && element != stack.peek()) {
                    sb = new StringBuilder();
                    sb.append("NO");
                    return;
                }
            }
        }
    }
}
