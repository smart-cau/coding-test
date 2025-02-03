package dynamicProgramming.타일채우기;

/*
 * 백준 2133번. 타일 채우기. gold 4
 * https://www.acmicpc.net/problem/2133
 * 참고링크: https://yabmoons.tistory.com/536
 * */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[N + 1];
        dp[0] = 1;

        for (int i = 2; i <= N; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);
    }
}
