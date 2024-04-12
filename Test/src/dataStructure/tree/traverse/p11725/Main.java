// 백준 11725번. 트리의 부모 찾기
// https://www.acmicpc.net/problem/11725
// silver 2
package dataStructure.tree.traverse.p11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.solve();
    }
}

class Solution {
    private int n;
    private int[] parents;
    private boolean[] visited;
    private List<List<Integer>> tree = new ArrayList<>();

    public void solve() throws IOException {
        getTree();
        bfs();
        printResult();
    }

    private void getTree() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i <= parents.length; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            tree.get(v1).add(v2);
            tree.get(v2).add(v1);
        }
    }

    private void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int parent = queue.poll();

            for (Integer child : tree.get(parent)) {
                if (!visited[child]) {
                    visited[child] = true;
                    parents[child] = parent;
                    queue.offer(child);
                }
            }
        }
    }

    private void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            // findParent(root, i, sb);
            sb.append(parents[i] + "\n");
        }
        System.out.println(sb.toString());
    }
}
