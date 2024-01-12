// 백준 1874번. 스택 수열
// https://www.acmicpc.net/problem/1874
// silver 2

package dataStructure.stack.p1874;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int max;
    static int[] sequence;
    static Deque<Integer> stack = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        max = scan.nextInt();
        sequence = new int[max];
        for (int i = 0; i < max; i++) {
            sequence[i] = scan.nextInt();
        }
    }

    static void solution() {
        stackSequence();

        System.out.println(sb.toString());
    }

    static void stackSequence() {
        // sequence iterator
        int number = 1;
        for (int index = 0; index < max; index++) {
            int element = sequence[index];
            // stack push & pop iterator
            while (element >= number) {
                stack.push(number);
                sb.append("+\n");
                ++number;
            }
            if (element == stack.peek()) {
                stack.pop();
                sb.append("-\n");
                continue;
            }
        }
        if (!stack.isEmpty()) {
            sb = new StringBuilder();
            sb.append("NO");
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
