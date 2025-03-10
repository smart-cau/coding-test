package graph.dfs.순위;

/*
 * 프로그래머스. 순위 . Level 3
 * https://school.programmers.co.kr/learn/courses/30/lessons/49191?language=java
 * */

class Solution {
    private boolean[][] graph;
    private int n;
    private boolean[] visited;

    public int solution(int n, int[][] results) {
        this.n = n;
        // 1. 승패 관계를 저장할 그래프 초기화
        graph = new boolean[n+1][n+1];

        // 2. 승패 관계 기록
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            graph[winner][loser] = true;
        }

        // 3. 각 선수별로 이기는 선수와 지는 선수의 수를 계산
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;

            // 이기는 선수 수 계산
            visited = new boolean[n+1];
            count += countWin(i) - 1; // 자기 자신 제외

            // 지는 선수 수 계산
            visited = new boolean[n+1];
            count += countLose(i) - 1; // 자기 자신 제외

            // 순위를 확실히 알 수 있는 경우
            if (count == n-1) {
                answer++;
            }
        }

        return answer;
    }

    // i번 선수가 이길 수 있는 선수의 수를 반환
    private int countWin(int player) {
        visited[player] = true;
        int count = 1;

        for (int i = 1; i <= n; i++) {
            if (!visited[i] && graph[player][i]) {
                count += countWin(i);
            }
        }

        return count;
    }

    // i번 선수가 질 수 있는 선수의 수를 반환
    private int countLose(int player) {
        visited[player] = true;
        int count = 1;

        for (int i = 1; i <= n; i++) {
            if (!visited[i] && graph[i][player]) {
                count += countLose(i);
            }
        }

        return count;
    }
}