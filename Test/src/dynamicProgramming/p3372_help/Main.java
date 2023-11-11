// 백준 3372번. 보드 점프
// https://www.acmicpc.net/problem/3372
// silver 1
package dynamicProgramming.p3372_help;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();
    static int N;
    static int[][] board;
    static BigInteger[][] dy;

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = scan.nextInt();
        board = new int[N][N];
        for (int row = 0; row < N; row++) {
            String[] numbers = scan.nextLine().split(" ");
            for (int column = 0; column < N; column++) {
                board[row][column] = Integer.parseInt(numbers[column]);
            }
        }
    }

    static void pro() {
        System.out.println(solution());
    }

    static BigInteger solution() {
        dy = new BigInteger[N][N];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                dy[row][column] = BigInteger.ZERO;
            }
        }

        dy[0][0] = BigInteger.ONE;
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if (row == N - 1 && column == N - 1)
                    break;
                int v = board[row][column];
                if (v + row < N)
                    dy[v + row][column] = dy[v + row][column].add(dy[row][column]);
                if (v + column < N)
                    dy[row][v + column] = dy[row][v + column].add(dy[row][column]);
            }
        }
        return dy[N - 1][N - 1];
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
