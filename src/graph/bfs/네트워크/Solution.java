package graph.bfs.네트워크;

import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int computerCnt = computers.length;
        boolean[] visited = new boolean[computerCnt];

        for (int i = 0; i < computerCnt; i++) {
            if (!visited[i]) {
                visited[i] = true;
                bfs(computers, visited, i);
                ++answer;
            }
        }
        return answer;
    }

    public void bfs(int[][] computers, boolean[] visited, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for (int node = 0; node < computers.length; node++) {
                int connected = computers[current][node];
                if (connected == 1 && current != node && !visited[node]) {
                    queue.add(node);
                    visited[node] = true;
                }
            }
        }
    }
}
