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
    static int[][] ground;
    static int totalBlocks = 0;
    static int minTime = Integer.MAX_VALUE;
    static int highest = 0;
    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        B = scan.nextInt();
        totalBlocks += B;
        ground = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int height = scan.nextInt();
                ground[i][j] = height;
                totalBlocks += height;
            }
        }
    }

    static void solution() {
        int mean = totalBlocks / (N * M);
        int sameTimeCount = 0;
        while (true) {
            int time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int blocks = ground[i][j];
                    if (blocks > mean)
                        time += (blocks - mean) * 2;
                    if (blocks < mean)
                        time += mean - blocks;
                }
            }
            // 평균이 9일 때랑 10일 때랑 걸리는 시간이 같다면??
            if (minTime == time) {
                ++sameTimeCount;
            }
            if (minTime > time) {
                minTime = time;
                sameTimeCount = 0;
            }
            if (minTime < time) {
                highest = mean + 1;
                if (sameTimeCount != 0) {
                    highest += sameTimeCount;
                }
                break;
            }
            --mean;
        }
        System.out.println(minTime + " " + highest);
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
