// 백준 1504번. 특정한 최단경로
// https://www.acmicpc.net/problem/1504
// gold 4
package dataStructure.graph.shortestPath.dijkstra.p1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.solution();
    }
}

class Dijkstra {
    private int n, e, v1, v2;
    private List<List<Node>> graph = new ArrayList<>();

    public void solution() throws IOException {
        input();

        int oneToV1 = dijkstra(1, v1);
        int oneToV2 = dijkstra(1, v2);
        int v1ToV2 = dijkstra(v1, v2);
        int v1ToEnd = dijkstra(v1, n);
        int v2ToEnd = dijkstra(v2, n);
        int path1 = getPath(oneToV1, v1ToV2, v2ToEnd),
                path2 = getPath(oneToV2, v1ToV2, v1ToEnd);

        int answer = getAnswer(path1, path2);

        System.out.println(answer);
    }

    private void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(u).add(Node.of(to, weight));
            graph.get(to).add(Node.of(u, weight));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }

    private int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(Node.of(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentWeight = current.weight;

            if (currentVertex == end && dist[currentVertex] != Integer.MAX_VALUE)
                break;

            if (currentWeight > dist[currentVertex])
                continue;

            for (Node neighbor : graph.get(currentVertex)) {
                int newDistance = dist[currentVertex] + neighbor.weight;
                if (newDistance < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDistance;
                    pq.offer(Node.of(neighbor.vertex, newDistance));
                }
            }
        }

        if (dist[end] == Integer.MAX_VALUE)
            return -1;

        return dist[end];
    }

    private int getPath(int toV, int vToV, int vToEnd) {
        int path = toV + vToV + vToEnd;
        if (toV == -1 || vToEnd == -1)
            path = -1;

        if (vToV == -1)
            path = -1;

        return path;
    }

    private int getAnswer(int path1, int path2) {
        int answer = Math.min(path1, path2);
        if (path1 == -1 && path2 != -1)
            answer = path2;
        if (path1 != -1 && path2 == -1)
            answer = path2;
        return answer;
    }
}

class Node implements Comparable<Node> {
    public int vertex;
    public int weight;

    public Node(int i, int c) {
        this.vertex = i;
        this.weight = c;
    }

    static Node of(int i, int c) {
        return new Node(i, c);
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.weight, o.weight);
    }

}