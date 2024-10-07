// 백준 10845번. 큐
// https://www.acmicpc.net/problem/10845
// silver 4
package dataStructure.queue.p10845;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    static int count;
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        CustomQueue queue = new CustomQueue();
        count = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < count; i++) {
            String[] input = sc.nextLine().split(" ");
            String instruction = input[0];
            switch (instruction) {
                case "push":
                    int n = Integer.parseInt(input[1]);
                    queue.push(n);
                    break;
                case "pop":
                    sb.append(queue.pop() + "\n");
                    break;
                case "size":
                    sb.append(queue.size() + "\n");
                    break;
                case "empty":
                    sb.append(queue.empty() + "\n");
                    break;
                case "front":
                    sb.append(queue.front() + "\n");
                    break;
                case "back":
                    sb.append(queue.back() + "\n");
                    break;
                default:
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    static class CustomQueue {
        private List<Integer> queue;

        public CustomQueue() {
            queue = new ArrayList<>();
        }

        public void push(int n) {
            queue.add(n);
        }

        public int pop() {
            if (queue.isEmpty())
                return -1;
            int popped = queue.get(0);
            queue.remove(0);
            return popped;
        }

        public int size() {
            return queue.size();
        }

        public int empty() {
            return queue.isEmpty() ? 1 : 0;
        }

        public int front() {
            return queue.isEmpty() ? -1 : queue.get(0);
        }

        public int back() {
            return queue.isEmpty() ? -1 : queue.get(queue.size() - 1);
        }
    }
}