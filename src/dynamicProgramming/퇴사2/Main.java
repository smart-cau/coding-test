package dynamicProgramming.퇴사2;

/*
 * 백준 15486번. 퇴사2. gold 5
 * https://www.acmicpc.net/problem/15486
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Counsel[] counsels;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        counsels = new Counsel[N + 1];
        dp = new long[N + 2];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            counsels[i] = new Counsel(day, cost);
        }

        solution();
    }

    static void solution() {
        for (int i = N; i >= 1; i--) {
            Counsel counsel = counsels[i];
            if (i + counsel.days - 1 <= N) {
                dp[i] = Math.max(dp[i + 1], dp[i + counsel.days] + counsel.cost);
            } else
                dp[i] = dp[i + 1]; // 이 조건을 생각 못해서 못풀 뻔 함 ㅠㅜㅠ 그래도 역방향 발상 좋았다!! 역방향역방향역방향!!
        }

        System.out.println(dp[1]);
    }

    static class Counsel {
        int days;
        int cost;

        public Counsel(int days, int cost) {
            this.days = days;
            this.cost = cost;
        }
    }
}
