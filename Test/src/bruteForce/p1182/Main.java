// 백준 18111번. 마인크래프트
// https://www.acmicpc.net/problem/18111
// silver 2
package bruteForce.p1182;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int B;
    static int[][] heights;
    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        B = scan.nextInt();
        heights = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                heights[N][M] = scan.nextInt();
            }
        }
    }

    static void solution() {
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
