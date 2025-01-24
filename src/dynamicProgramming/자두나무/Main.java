package dynamicProgramming.자두나무;

/*
 * 백준 2240번. 자두나무. gold 5
 * https://www.acmicpc.net/problem/2240
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int fallings, maxMoves, count = 0;
    static int[] fruits;
    static int[][] dp1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        fallings = Integer.parseInt(st.nextToken());
        maxMoves = Integer.parseInt(st.nextToken());

        fruits = new int[fallings + 1];

        for (int i = 1; i <= fallings; i++) {
            st = new StringTokenizer(br.readLine());
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        dp1 = new int[maxMoves + 1][fallings + 1];

        solution();
    }

    static void solution() {
        for (int moveTimes = 0; moveTimes <= maxMoves; moveTimes++) {
            for (int second = 1; second <= fallings; second++) {
                if (moveTimes == 0) {
                    if (fruits[second] == 1) {
                        dp1[0][second] = 1 + dp1[0][second - 1];
                    } else
                        dp1[0][second] = dp1[0][second - 1];
                    continue;
                }

                if (moveTimes <= second) {
                    if (moveTimes % 2 == 1) { // 현재위치 == 2
                        int move1 = fruits[second] == 2 ? dp1[moveTimes - 1][second - 1] + 1 : dp1[moveTimes - 1][second - 1];
                        int dontMove1 = fruits[second] == 2 ? dp1[moveTimes][second - 1] + 1 : dp1[moveTimes][second - 1];
                        dp1[moveTimes][second] = Math.max(dontMove1, move1);
                    } else { // 현재위치 == 1
                        int move1 = fruits[second] == 1 ? dp1[moveTimes - 1][second - 1] + 1 : dp1[moveTimes - 1][second - 1];
                        int dontMove1 = fruits[second] == 1 ? dp1[moveTimes][second - 1] + 1 : dp1[moveTimes][second - 1];
                        dp1[moveTimes][second] = Math.max(dontMove1, move1);
                    }
                }
            }
        }
        int max1 = 0;
        for (int i = 0; i <= maxMoves; i++) { // 한번도 안움직이는 경우도 max 값 계산에 포함했어야 했는데 ㅜㅠㅠㅜㅠㅜ
            max1 = Math.max(max1, dp1[i][fallings]);
        }
        System.out.println(max1);
    }
}
