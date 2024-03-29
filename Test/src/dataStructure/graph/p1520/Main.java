// 백준 1520번. 내리막길 - Re
// https://www.acmicpc.net/problem/1520
// gold 3
package dataStructure.graph.p1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] memo;
    static int count = 0;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    };

    static void solution() {
        /*
         * 사고의 흐름
         * 배열의 탐색 -> dfs || bfs 적용
         * m, n <= 500이면 최대 500 * 500 size의 array를 stack으로 dfs를 돌리면 timeout이 날 수 있음
         * 따라서 효율적인 탐색을 위해 dynamic programming 적용(memoization)
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 방문했는데 경로가 없는 곳과 방문하지 않은 곳을 구분하기 위해 -1로 memo 배열 초기화
                memo[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int row, int column) {
        if (row == m - 1 && column == n - 1) {
            return 1;
        }

        // 한 지점에 대해 모든 경우의 수가 count 되었기 때문에 바로 memo return
        if (memo[row][column] != -1)
            return memo[row][column];

        // 방문한 곳은 0으로 초기화
        memo[row][column] = 0;

        // 4 ways search
        for (int i = 0; i < 4; i++) {
            int x = row + dx[i];
            int y = column + dy[i];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }

            // 내리막길 check. 한 방향으로밖에 갈 수 없기에 재방문 여부 확인은 무의미
            if (map[row][column] > map[x][y])
                // (call) stack으로 한 칸이 목표점까지 갈 수 있는 모든 경우의 수 count 가능
                memo[row][column] += dfs(x, y);
        }

        return memo[row][column];
    };
}
