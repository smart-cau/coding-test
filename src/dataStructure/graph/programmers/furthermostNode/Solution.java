// 가장 먼 노드
// https://school.programmers.co.kr/learn/courses/30/lessons/49189
// level 3
package dataStructure.graph.programmers.furthermostNode;

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<List<Integer>> graph = new ArrayList<>();
        setGraph(n, edge, graph);
        
        int[] distances = new int[n + 1];
        answer = bfs(n, graph, distances);
        return answer;
    }
    
    public int bfs(int n, List<List<Integer>> graph, int[] distances) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int max = -1;
        int count = 0;
        
        queue.add(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int v: graph.get(node)) {
                if (!visited[v]) {
                    queue.add(v);
                    visited[v] = true;
                    // 각 node 별 길이 기록
                    distances[v] = distances[node] + 1;
                    // 길이 처리
                    if (distances[v] == max) {
                        ++count;
                    }
                    if (distances[v] > max) {
                        max = distances[v];
                        count = 1;
                    }
                }
            }
        }
        
        return count;
    }

    public void setGraph(int n, int[][] edge, List<List<Integer>> graph) {
        graph.add(new ArrayList<>());   
        for (int i = 1; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] e: edge) {
            int n1 = e[0];
            int n2 = e[1];

            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
    }
    
}