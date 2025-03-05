package graph.최단경로.플로이드와샬.플로이드;

/*
 * 백준 11404번. 플로이드. gold 4
 * https://www.acmicpc.net/problem/11404
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, INF = 987654321; // Integer.MAX_VALUE / 1000을 했더니 overflow 발생
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];

        // init map
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                map[i][j] = i == j ? 0 : INF;

        // get input
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()),
                to = Integer.parseInt(st.nextToken()),
                cost = Integer.parseInt(st.nextToken());
            map[from][to] = Math.min(map[from][to], cost);
        }

        // floyd
        for (int stopOver = 1; stopOver <= n; stopOver++)
            for (int from = 1; from <= n; from++)
                for (int to = 1; to <= n; to++)
                    map[from][to] = Math.min(map[from][to], map[from][stopOver] + map[stopOver][to]);

        StringBuilder sb = new StringBuilder();

        // print
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = map[i][j] == INF ? 0 : map[i][j];
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
