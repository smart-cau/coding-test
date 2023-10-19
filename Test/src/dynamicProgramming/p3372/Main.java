// 백준 3372번. 보드 점프
// https://www.acmicpc.net/problem/3372
// silver 1
package dynamicProgramming.p3372;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();
    static int N;
    static int[][] board;
    static int[][] dy;
    static int count = 0;

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
        rec(N - 1, N - 1);
        System.out.println(count);
    }

    static void rec(int row, int column) {
        if (row == 0 && column == 0) {
            ++count;
            return;
        }
        // row 탐색
        if (row != 0)
            for (int r = row - 1; r >= 0; r--) {
                if (r + board[r][column] == row)
                    rec(r, column);
                else
                    continue;
            }
        // column 탐색
        if (column != 0)
            for (int c = column - 1; c >= 0; c--) {
                if (c + board[row][c] == column)
                    rec(row, c);
                else
                    continue;
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
