// 백준 1654번
// https://www.acmicpc.net/problem/1654
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int K = Integer.parseInt(inputs[0]);
        int N = Integer.parseInt(inputs[1]);

        int[] cables = new int[K];
        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
        }

        long l = 1L;
        long r = 1L << 32;

        long answer = -1L;
        while (l <= r) {
            long m = (l + r) / 2L;
            if (isPossible(cables, m, N)) {
                answer = m;
                l = m + 1;
            } else
                r = m - 1;
        }

        System.out.println(answer);
        br.close();
    }

    static boolean isPossible(int[] cables, long length, int N) {
        long count = 0;
        for (int cable : cables) {
            /*
             * 아래 부분 때문에 오답이었음. 자르는 길이가 특정 케이블보다 길 수는 있어도, 결국 count에는 포함될 수도 있음
             * if (length > cable)
             * return false;
             */
            count += cable / length;
        }
        if (count < N)
            return false;

        return true;
    }

}
