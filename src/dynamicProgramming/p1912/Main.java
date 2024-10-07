// 백준 1912. 연속합
// https://www.acmicpc.net/problem/1912
// silver 2
package dynamicProgramming.p1912;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();
    static int N;
    static int[] seq;
    static int[] dy;

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = scan.nextInt();
        seq = new int[N];
        for (int i = 0; i < N; i++) {
            seq[i] = scan.nextInt();
        }
    }

    static void pro() {
        dy = new int[N];
        int max = seq[N - 1]; // init value
        dy[N - 1] = seq[N - 1];
        if (N > 1)
            for (int i = N - 2; i >= 0; i--) {
                dy[i] = seq[i];
                if (dy[i] + dy[i + 1] > dy[i])
                    dy[i] += dy[i + 1];
                if (max < dy[i])
                    max = dy[i];
            }
        System.out.println(max);
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
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
