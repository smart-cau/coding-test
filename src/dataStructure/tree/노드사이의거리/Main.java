package dataStructure.tree.노드사이의거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 백준 1240번. 노드 사이의 거리. gold 5
 * https://www.acmicpc.net/problem/1240
 * */


/* Tree의 핵심 성질을 기억하면 쉽다
    - n개의 node <--> n - 1개의 edge
    - 두 점을 연결하는 simple path는 항상 유일하다!!!!
* */
public class Main {
    static int N, M;
    static List<List<int []>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()),
                v2 = Integer.parseInt(st.nextToken()),
                cost = Integer.parseInt(st.nextToken());
            tree.get(v1).add(new int[]{v2, cost});
            tree.get(v2).add(new int[]{v1, cost});
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()),
                v2 = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[N + 1];
            dfs(v1, v2, 0, visited);
        }
    }

    static void dfs(int current, int destination, int total, boolean[] visited) {
        if (current == destination) {
            System.out.println(total);
            return;
        }
        visited[current] = true;
        for (int [] edge : tree.get(current)) {
            int to = edge[0], cost = edge[1];
            if (visited[to]) continue;
            dfs(to, destination, total + cost, visited);
        }
    }
}
