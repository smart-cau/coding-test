package dynamicProgramming.가장큰정사각형;

/*
 * 백준 1915번. 가장 큰 정사각형. gold 4
 * https://www.acmicpc.net/problem/1915
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int row, column, MAX = 0;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());

        dp = new int[row + 1][column + 1];

        for (int i = 1; i <= row; i++) {
            String line = br.readLine();
            for (int j = 1; j <= column; j++) {
                int cell = line.charAt(j - 1) - '0';
                if (cell == 1) {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                    MAX = Math.max(MAX, dp[i][j]);
                }
            }
        }

        System.out.println(MAX * MAX);
    }
    /*
     * MAX = -1로 둔다
     * 우선, 0,0부터 가로로 탐색을 한다. 탐색한 곳은 visited로 표시한다
     * cell == 0 -> dp = 0
     * cell == 1
     *   처음으로 만나는 1을 포인트로 잡고, 정사각형 범위로 스캔을 함. 이 스캔을 단계적으로 해야 함.
     *   그리고 다음 길이를 1씩 확장하기 전에, 지금까지 나온 최대 길이를 dp에 저장해야 함. dp는 결국 넣냐 안넣냐의 문제
     *   근데 이렇게 하면 시간복잡도가 넘 높을 것 같은데... 1이 나올 때마다 정사각형 스캔을 한다고 하면, 최악의 경우 N^3임.
     *   N^2로 끊어야 함. 그냥 스캔하면서 답을 얻을 수 있도록.
     *   아무리 생가해도, 1을 각 정사각형의 꼭짓점이라고 가정하고 탐색하는 방법밖에 생각나지 않음...
     * 답 본 후기 =>
         * 좌상단부터 보면 N^3의 시간복잡도 풀이가 나오는 것 맞음.
         * 하나의 점을 꼭짓점으로 보고, 이차원 배열 한번 순회에 답이 나와야 하는 것도 맞음
         * 단, padding 값을 고려하고, 우하단부터 보면 쉽게 풀 수 있었음.
         * 이 문제의 핵심은, 아래 조건을 이해하는 것이었음
             *  문제는 부분 문제로 나눌 수 있어야 합니다.
                부분 문제는 중복되어야 합니다.
                부분 문제의 해결 결과를 저장하고 재사용할 수 있어야 합니다.
     * */
}
