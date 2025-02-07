package dynamicProgramming.가장큰정사각형;

/*
 * 백준 1915번. 가장 큰 정사각형. gold 4
 * https://www.acmicpc.net/problem/1915
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Second {
    static int n, m, answer = 0;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                int cell = line.charAt(j - 1) - '0';
                if (cell == 1) {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }
        System.out.println(answer * answer);
    }
}
