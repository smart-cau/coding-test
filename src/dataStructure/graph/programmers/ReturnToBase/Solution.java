// 부대 복귀
// https://school.programmers.co.kr/learn/courses/30/lessons/132266
// level 3
package dataStructure.graph.programmers.ReturnToBase;

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {        
        int[] answer = new int[sources.length];
        List<List<Integer>> graph = setGraph(n, roads);

        int[] distances = bfs(graph, destination);
        for (int i = 0; i < sources.length; i++) {
            answer[i] = distances[sources[i]];
        }
        
        return answer;
    }
    
    public int[] bfs(List<List<Integer>> graph, int destination) {
        int n = graph.size();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] distances = new int[n + 1];
        Arrays.fill(distances, -1);
        
        queue.add(destination);
        visited[destination] = true;
        distances[destination] = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();            

            for (int v: graph.get(node)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                    distances[v] = distances[node] + 1;
                }    
            }
        }

        return distances;
    }
    
    public List<List<Integer>> setGraph(int n, int[][] roads) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) 
            graph.add(new ArrayList<>());
        
        for (int[] vertexes: roads) {
            int v1 = vertexes[0];
            int v2 = vertexes[1];
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);            
        }
        return graph;
    }
}

