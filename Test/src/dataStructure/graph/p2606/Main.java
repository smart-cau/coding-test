// 백준 2606번. 바이러스
// https://www.acmicpc.net/problem/2606
// silver 3
package dataStructure.graph.p2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static int computers;
    static int pairs;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();
        solution();
    }

    static void input() throws NumberFormatException, IOException {
        computers = Integer.parseInt(br.readLine());
        pairs = Integer.parseInt(br.readLine());

        for (int i = 1; i <= computers; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < pairs; i++) {
            String[] nodes = br.readLine().split(" ");
            Integer firstNode = Integer.parseInt(nodes[0]);
            Integer secondNode = Integer.parseInt(nodes[1]);

            graph.get(firstNode).add(secondNode);
            graph.get(secondNode).add(firstNode);
        }
    }

    static void solution() {
        List<Integer> discovered = new ArrayList<>();
        // int result = graph.get(1).size() == 0 ? 0 : dfs(1, discovered).size() - 1; // 1번 컴퓨터는 이미 감염되었으므로 제외
        int result = graph.get(1).size() == 0 ? 0 : stackDFS(1).size() - 1;

        System.out.println(result);
    }

    static List<Integer> dfs(int vertex, List<Integer> discovered) {
        // mark as discovered
        discovered.add(vertex);

        for (Integer arrival : graph.get(vertex)) {
            // search what hasn't been discovered
            if (!discovered.contains(arrival))
                discovered = dfs(arrival, discovered);
        }

        // return the result when every depth is discovered
        return discovered;
    }

    static List<Integer> stackDFS(int vertex) {
        List<Integer> discovered = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(vertex);

        while (!stack.isEmpty()) {
            int departNode = stack.pop();

            if (!discovered.contains(departNode)) {
                discovered.add(departNode);
                for (Integer arrivalNode : graph.get(departNode))
                    stack.push(arrivalNode);
            }
        }
        return discovered;
    }
}
