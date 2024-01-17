// 백준 2493번. 탑(Monotonic stack)
// https://www.acmicpc.net/problem/2493
// gold 5
package dataStructure.stack.p2493;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] inputs;
    static Deque<Tower> stack = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = scan.nextInt();
        inputs = new int[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = scan.nextInt();
        }
    }

    static void solution() {
        for (int i = 0; i < N; i++) {
            int height = inputs[i];

            while (!stack.isEmpty() && stack.peek().height < height) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                sb.append("0 ");
            }
            if (!stack.isEmpty() && stack.peek().height > height) {
                sb.append(stack.peek().index + " ");
            }
            stack.push(new Tower(i + 1, height));
        }

        // print

        System.out.println(sb.toString());
    }

    static class Tower {
        int index;
        int height;

        public Tower(int index, int height) {
            this.index = index;
            this.height = height;
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
