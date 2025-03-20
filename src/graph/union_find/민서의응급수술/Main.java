package graph.union_find.민서의응급수술;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 20955번. 민서의 응급수술. gold 4
 * https://www.acmicpc.net/problem/20955
 * */

public class Main {
    static int n, m, answer = 0;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] =  -1;
        }

        // 방향성을 어떻게 해야하나 고민했었음..
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()),
                v = Integer.parseInt(st.nextToken());
            if (!union(u, v)) // cycle이 있는 경우
                answer++;
        }

        // 이 로직을 못떠올려서 틀림
        int shouldBeConnected = 0;
        for (int i = 1; i <= n; i++) {
            if (parents[i] == -1) shouldBeConnected++;
        }

        answer += (shouldBeConnected - 1);
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
