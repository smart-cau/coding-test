package graph.최단경로.다익스트라;

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
    private PriorityQueue<Node> pq = new PriorityQueue<>();
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
        dist = new int[v + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.offer(Node.of(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.index;
            int currentDistance = current.weight;

            if (currentDistance > dist[currentVertex])
                continue;

            for (Node neighbor : graph.get(currentVertex)) {
                int newDistance = dist[currentVertex] + neighbor.weight;

                if (newDistance < dist[neighbor.index]) {
                    dist[neighbor.index] = newDistance;
                    pq.offer(new Node(neighbor.index, newDistance));
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
            sb.append(d).append("\n");
        }
        System.out.println(sb);
    }
}

class Node implements Comparable<Node> {
    public int index;
    public int weight;

    public Node(int i, int c) {
        this.index = i;
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
