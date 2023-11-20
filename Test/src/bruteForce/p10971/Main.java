// 백준 10971번. 외판원 순회2
// https://www.acmicpc.net/problem/10971
// silver 2
package bruteForce.p10971;

import java.util.Scanner;

// 잘 모르고 한거긴 하지만 내가 한게 dfs(깊이 우선 탐색)인 것 같다. 드디어 재귀가 익숙해진 것인가...
public class Main {

    static int N;
    static int[][] costs;
    static boolean[] visited;
    static int firstCity;
    static int min = Integer.MAX_VALUE;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = sc.nextInt();
        costs = new int[N][N];
        for (int from = 0; from < N; from++) {
            for (int to = 0; to < N; to++) {
                costs[from][to] = sc.nextInt();
            }
        }
    }

    static void solution() {
        visited = new boolean[N];
        for (int from = 0; from < N; from++) {
            firstCity = from;
            visited[from] = true;
            traverse(from, 0, 0);
            visited[from] = false;
        }
        System.out.println(min);
    }

    static void traverse(int from, int count, int totalCost) {
        // terminate condition -> 도시로 가는 길이 없을 때 or 모든 도시 순회가 끝날 때
        if (count == N - 1) {
            int cost = costs[from][firstCity];
            if (cost == 0)
                return;
            totalCost += cost;
            min = Math.min(min, totalCost);
            return;
        }

        count += 1;

        // recursive condition
        for (int to = 0; to < N; to++) {
            if (!visited[to] && to != from) {
                int cost = costs[from][to];
                if (cost == 0)
                    return;
                visited[to] = true;
                traverse(to, count, totalCost + cost);
                visited[to] = false;
            }
        }
    }

}
