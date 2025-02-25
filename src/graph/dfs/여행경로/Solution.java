package graph.dfs.여행경로;

import java.util.*;

class Solution {
    static List<String> answer;
    static boolean[] visited;

    public String[] solution(String[][] tickets) {
        answer = new ArrayList<>();
        visited = new boolean[tickets.length];

        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            } else {
                return a[0].compareTo(b[0]);
            }
        });

        dfs("ICN", "ICN", tickets, 0);

        return answer.get(0).split(" ");
    }

    public void dfs(String start, String route, String[][] tickets, int depth) {
        if (depth == tickets.length) {
            answer.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (start.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, depth + 1);
                visited[i] = false;
            }
        }
    }
}


