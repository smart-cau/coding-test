package graph.union_find.소셜네트워킹어플리케이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 7511번. 소셜 네트워킹 어플리케이션. gold 5
 * https://www.acmicpc.net/problem/7511
 * */

public class Main {
    static int testCaseCount, nodes, edges, proofCases;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        testCaseCount = Integer.parseInt(st.nextToken());
        for (int currentCase = 1; currentCase <= testCaseCount; currentCase++) {
            sb.append("Scenario ").append(currentCase).append(":").append("\n");

            st = new StringTokenizer(br.readLine());
            nodes = Integer.parseInt(st.nextToken());

            // init parents
            parents = new int[nodes + 1];
            for (int i = 0; i < nodes + 1; i++)
                parents[i] = -1;

            st = new StringTokenizer(br.readLine());
            edges = Integer.parseInt(st.nextToken());
            for (int i = 0; i < edges; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()),
                    v = Integer.parseInt(st.nextToken());
                union(u, v);
            }

            st = new StringTokenizer(br.readLine());
            proofCases = Integer.parseInt(st.nextToken());
            for (int i = 0; i < proofCases; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()),
                    v = Integer.parseInt(st.nextToken());
                if (find(u) == find(v))
                    sb.append("1").append("\n");
                else
                    sb.append("0").append("\n");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static int find(int node) {
        if (parents[node] == -1)
            return node;
        return parents[node] = find(parents[node]);
    }

    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return false;
        parents[v] = u;
        return true;
    }
}
