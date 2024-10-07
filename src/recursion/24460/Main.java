
// 백준 24460. 특별상이라도 받고 싶어!
// https://www.acmicpc.net/problem/24460
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();

    static int N;
    static int[][] chairs;
    static int[] lastChairs = new int[4];

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = scan.nextInt();
        chairs = new int[N][N];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                chairs[row][column] = scan.nextInt();
            }
        }
    }

    static void pro() {
        if (N == 1) {
            System.out.println(chairs[0][0]);
            return;
        }
        System.out.println(findSpecial(N, 0, 0));
    }

    static int findSpecial(int size, int startRow, int startColumn) {
        int[] winners = new int[4];

        // base case
        if (size == 2) {
            int index = 0;
            for (int row = startRow; row < size + startRow; row++) {
                for (int column = startColumn; column < size + startColumn; column++) {
                    winners[index++] = chairs[row][column];
                }
            }
        }

        // recursive case
        else {
            int halfSize = size / 2;

            int localIndex = 0;
            for (int row = startRow; row < size + startRow; row += halfSize) {
                for (int column = startColumn; column < size + startColumn; column += halfSize) {
                    winners[localIndex++] = findSpecial(halfSize, row, column);
                }
            }
        }

        return getWinner(winners);
    }

    static int getWinner(int[] lastNumbers) {
        Arrays.sort(lastNumbers);
        return lastNumbers[1];
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
