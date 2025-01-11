package graph.bfs.게임맵최단거리;

/*
 * 프로그래머스. bfs. Level 2. 게임 맵 최단거리
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844
 * */
import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length; // 맵의 세로 크기
        int m = maps[0].length; // 맵의 가로 크기

        // BFS를 위한 큐와 방문 배열
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        // 방향 배열: 상, 하, 좌, 우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 시작 지점 넣기 (x, y, 거리)
        queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            // 목표 지점 도달 시 거리 반환
            if (x == n - 1 && y == m - 1) {
                return dist;
            }

            // 4방향으로 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 유효한 좌표인지 확인 (맵 안에 있고, 벽이 아니며, 방문하지 않은 곳)
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!visited[nx][ny] && maps[nx][ny] == 1) {
                        visited[nx][ny] = true; // 방문 표시
                        queue.add(new int[]{nx, ny, dist + 1}); // 다음 탐색
                    }
                }
            }
        }

        // 목표 지점에 도달하지 못한 경우
        return -1;
    }
}
