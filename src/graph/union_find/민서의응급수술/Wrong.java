package graph.union_find.민서의응급수술;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 백준 20955번. 민서의 응급수술. gold 4
 * https://www.acmicpc.net/problem/20955
 * */

public class Wrong {
    static int n, m, answer = 0;
    static int[] parents;
    static List<int []> edges = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] =  -1;
        }

        int u, v; // u < v
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()),
                    b = Integer.parseInt(st.nextToken());
            if (a > b) {
                u = b;
                v = a;
            } else {
                u = a;
                v = b;
            }
            if (!union(u, v)) // cycle이 있는 경우
                answer++;
            else
                edges.add(new int[]{u, v});
        }

        for (int i = 0; i < edges.size() - 1; i++) {
            int u1 = edges.get(i)[0];
            int u2 = edges.get(i + 1)[0];
            if (union(u1, u2))
                answer++;
        }

        System.out.println(answer);
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
