package graph.union_find.로봇조립;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 18116번. 로봇 조립. gold 4
 * https://www.acmicpc.net/problem/18116
 * */
public class Wrong {
    static int n, MAX_NUMBER = 1000001;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parent = new int[MAX_NUMBER];
        for (int i = 0; i < MAX_NUMBER; i++)
            parent[i] = -1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("I")) {
                int u = Integer.parseInt(st.nextToken()),
                        v = Integer.parseInt(st.nextToken());
                union(u, v);
            } else {
                int node = Integer.parseInt(st.nextToken());
                sb.append(getSize(node)).append("\n");
            }
        }

        System.out.println(sb);
    }

    static int getSize(int node) {
        return -parent[find(node)];
    }

    static int find(int node) {
        if (parent[node] < 0)
            return node;
        return parent[node] = find(parent[node]);
    }

    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return false;
        if (parent[v] < parent[u]) { // v의 랭크가 더 클 경우
            int temp = u;
            u = v;
            v = temp;
        }
        parent[v] = u;
        --parent[u]; // 이 방식이 잘못되었음!! rank가 높은 두 서브트리의 결합 케이스를 고려 못함. 잘못된 예제까지 고려하는게 실력이다...
        return true;
    }
}
