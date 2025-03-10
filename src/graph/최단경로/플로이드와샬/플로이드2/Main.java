package graph.최단경로.플로이드와샬.플로이드2;

/*
 * 백준 11780번. 플로이드2. gold 2
 * https://www.acmicpc.net/problem/11780
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 플로이드 알고리즘은 DP를 근간으로 하기 때문에 경로를 찾을 수 있다!!! -> 알고리즘의 근간을 이해하냐? 이게 핵심!
public class Main {
    static int n, m, INF = Integer.MAX_VALUE / 3;
    static int[][] map, next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        next = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                map[i][j] = i == j ? 0 : INF;


        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()),
                to = Integer.parseInt(st.nextToken()),
                cost = Integer.parseInt(st.nextToken());
            map[from][to] = Math.min(map[from][to] ,cost);
            next[from][to] = to;
        }

        for (int stopOver = 1; stopOver <= n; stopOver++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (map[from][to] > map[from][stopOver] + map[stopOver][to]) {
                        map[from][to] = map[from][stopOver] + map[stopOver][to];
                        next[from][to] = next[from][stopOver];
                    }
                }
            }
        }

        // 최단경로 배열 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = map[i][j] == INF ? 0 : map[i][j];
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        // path 출력
        for (int from = 1; from <= n; from++) {
            for (int to = 1; to <= n; to++) {
                if (map[from][to] == 0)
                    sb.append(0);
                else {
                    List<Integer> path = new ArrayList<>();
                    path.add(from);
                    int nextNode = next[from][to];
                    while (nextNode != to) {
                        path.add(nextNode);
                        nextNode = next[nextNode][to];
                    }
                    path.add(nextNode);

                    sb.append(path.size()).append(" ");
                    for (int p: path)
                        sb.append(p).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
