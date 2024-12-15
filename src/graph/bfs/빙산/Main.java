/*
 * 백준 2573번. 빙산. gold 4
 * https://www.acmicpc.net/problem/2573
 * */
package graph.bfs.빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static List<Glacier> glaciers = new ArrayList<>();
    static boolean[][] visited;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int years = 0;
    static boolean isOnePiece = true;

    // 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int glacierHeight = Integer.parseInt(st.nextToken());
                map[i][j] = glacierHeight;
            }
        }

        while (true) {
            // init
            int count = 0;
            boolean hasGlaciers = false;
            glaciers.clear();
            visited = new boolean[N][M];
            // iter
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && map[i][j] != 0) {
                        bfs(i, j);
                        ++count;
                        isOnePiece = count == 1;
                        hasGlaciers = true;
                        // 높이 낮추기
                        decreaseHeight();
                    }
                }
            }
            if (!isOnePiece || !hasGlaciers) break;
            ++years;
        }
        years = !isOnePiece ? years : 0;
        System.out.printf("%d\n",  years);
    }

    static void bfs(int x, int y) {
        Queue<Glacier> queue = new LinkedList<>();
        Glacier firstGlacier = new Glacier(x, y, map[x][y]);
        visited[x][y] = true;
        queue.offer(firstGlacier);
        glaciers.add(firstGlacier);

        while (!queue.isEmpty()) {
            Glacier currentGlacier = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = currentGlacier.x + dx[i];
                int ny = currentGlacier.y + dy[i];
                if (isInRange(nx, ny)) {
                    if (map[nx][ny] == 0)
                        ++currentGlacier.surroundCount;
                    else if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        Glacier newGlacier = new Glacier(nx, ny, map[nx][ny]);
                        queue.offer(newGlacier);
                        glaciers.add(newGlacier);
                    }
                }
            }
        }
    }

    static boolean isInRange(int nx, int ny) {
        return nx >= 0 && nx < N && ny >= 0 && ny < M;
    }

    static void decreaseHeight() {
        for (Glacier glacier : glaciers) {
            int x = glacier.x;
            int y = glacier.y;
            int newHeight = Math.max(0, map[x][y] - glacier.surroundCount);
            map[x][y] = newHeight;
        }
    }

    static class Glacier {
        int x;
        int y;
        int height;
        int surroundCount = 0;

        public Glacier(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
