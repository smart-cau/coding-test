package slidingWindow.내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 2096번. 내려가기. gold 5 #RE
 * https://www.acmicpc.net/problem/2096
 * #DP #슬라이딩윈도우
 * */

public class Main {
    static int N, COL_NUM = 3;
    static int[][] maxDp, minDp, map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][COL_NUM];
        maxDp = new int[2][COL_NUM]; // Sliding Window 개념을 적용해 row의 크기를 2로 최적화
        minDp = new int[2][COL_NUM];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < COL_NUM; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < COL_NUM; i++) {
            maxDp[0][i] = map[0][i];
            minDp[0][i] = map[0][i];
        }

        for (int row = 1; row < N; row++) {
            int currRow = row % 2;
            int prevRow = (row - 1) % 2;
            /* 아래 로직이 잘못됐음... */
//            for (int col = 0; col < COL_NUM; col++) {
//                int prevMax = getMax(row, col);
//                maxDp[dpRow][col] = Math.max(maxDp[dpRow][col], prevMax); -> 현재 DP 값은 이제 정해지는 것이기 때문에 기준 값으로 삼으면 안됨..
//
//                int prevMin = getMin(row, col);
//                minDp[dpRow][col] = Math.min(minDp[dpRow][col], prevMin);
//            }
            // 첫 번째 열 (0)
            maxDp[currRow][0] = Math.max(maxDp[prevRow][0], maxDp[prevRow][1]) + map[row][0];
            minDp[currRow][0] = Math.min(minDp[prevRow][0], minDp[prevRow][1]) + map[row][0];

            // 두 번째 열 (1)
            maxDp[currRow][1] = Math.max(Math.max(maxDp[prevRow][0], maxDp[prevRow][1]), maxDp[prevRow][2]) + map[row][1];
            minDp[currRow][1] = Math.min(Math.min(minDp[prevRow][0], minDp[prevRow][1]), minDp[prevRow][2]) + map[row][1];

            // 세 번째 열 (2)
            maxDp[currRow][2] = Math.max(maxDp[prevRow][1], maxDp[prevRow][2]) + map[row][2];
            minDp[currRow][2] = Math.min(minDp[prevRow][1], minDp[prevRow][2]) + map[row][2];
        }

        int resultMax = 0;
        int resultMin = Integer.MAX_VALUE;
        for (int i = 0; i < COL_NUM; i++) {
            resultMax = Math.max(resultMax, maxDp[(N+ 1) % 2][i]);
            resultMin = Math.min(resultMin, minDp[(N+ 1) % 2][i]);
        }

        System.out.println(resultMax + " " + resultMin);
    }

    private static int getMin(int row, int col) {
        int min = Integer.MAX_VALUE;
        int dpRow = (row - 1) % 2;
        if (col == 0) {
            for (int i = 0; i < COL_NUM - 1; i++)
                min = Math.min(min, minDp[dpRow][i] + map[row][col]);
        }

        if (col == 1) {
            for (int i = 0; i < COL_NUM; i++)
                min = Math.min(min, minDp[dpRow][i] + map[row][col]);
        }

        if (col == 2) {
            for (int i = 1; i < COL_NUM; i++)
                min = Math.min(min, minDp[dpRow][i] + map[row][col]);
        }

        return min;

    }

    static int getMax(int row, int col) {
        int max = 0;
        int dpRow = (row - 1) % 2;
        if (col == 0) {
            for (int i = 0; i < COL_NUM - 1; i++)
                max = Math.max(max, maxDp[dpRow][i] + map[row][col]);
        }

        if (col == 1) {
            for (int i = 0; i < COL_NUM; i++)
                max = Math.max(max, maxDp[dpRow][i] + map[row][col]);
        }

        if (col == 2) {
            for (int i = 1; i < COL_NUM; i++)
                max = Math.max(max, maxDp[dpRow][i] + map[row][col]);
        }

        return max;
    }
}
