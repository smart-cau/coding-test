package dataStructure.tree.회사문화1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 백준 14267번. 회사 문화1. gold 4
 * https://www.acmicpc.net/problem/14267
 * */


public class Main {
    static int n, m;
    static List<List<Integer>> tree = new ArrayList<>();

    static int[] counts;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        counts = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int staff = 1; staff <= n; staff++) {
            int senior = Integer.parseInt(st.nextToken());
            if (senior == -1) continue;
            tree.get(senior).add(staff);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int staff = Integer.parseInt(st.nextToken()),
                count = Integer.parseInt(st.nextToken());
            counts[staff] += count;
        }

        dfs(1, 0);

        for (int i = 1; i <= n; i++) {
            System.out.print(counts[i] + " ");
        }
    }

    static void dfs(int staff, int count) {
        counts[staff] += count;
        int newCount = counts[staff];
        for (int child : tree.get(staff)) {
            dfs(child, newCount);
        }
    }
}
