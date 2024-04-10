// 백준 1753번. 최단경로
// https://www.acmicpc.net/problem/1753
// gold 4
package dataStructure.graph.shortestPath.dijkstra.p1753;

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
        Dijkstra dj = new Dijkstra();
        dj.solution();
    }
}

class Dijkstra {
    private int v;
    private int e;
    private int start;
    private int INF = Integer.MAX_VALUE;
    private List<List<Node>> graph = new ArrayList<>();
    private int[] dist;

    public void solution() throws IOException {
        getInputs();
        dijkstra();
        print();
    }

    private void getInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());

        // init graph
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(u).add(Node.of(to, weight));
        }
    }

    private void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[v + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.offer(Node.of(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex; 
            int currentDistance = current.weight; // 이전 vertex에서 current vertex까지 바로 오는 cost

            if (currentDistance > dist[currentVertex])
                continue;

            for (Node neighbor : graph.get(currentVertex)) {
                int newDistance = dist[currentVertex] + neighbor.weight;

                if (newDistance < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDistance;
                    pq.offer(new Node(neighbor.vertex, newDistance));
                }
            }
        }
    }

    private void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            String d = Integer.toString(dist[i]);
            if (dist[i] == INF)
                d = "INF";
            sb.append(d + "\n");
        }
        System.out.println(sb.toString());
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