/*
 * 백준 10026번. 적록색약 gold 5
 * https://www.acmicpc.net/problem/10026
 * */
package graph.dfs.적록색약;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] sections;
    static int result = 0;
    static int resultBlind = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static boolean[][] visitedBlind;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        sections = new char[N][N];
        visited = new boolean[N][N];
        visitedBlind = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                sections[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j, false);
                    ++result;
                }
                if (!visitedBlind[i][j]) {
                    visitedBlind[i][j] = true;
                    dfs(i, j, true);
                    ++resultBlind;
                }
            }
        }

        System.out.println(result + " " + resultBlind);
    }

    static void dfs(int x, int y, boolean blind) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isInRange(nx, ny) && isSameColor(x, y, nx, ny, blind)) {
                boolean isVisited = blind ? visitedBlind[nx][ny] : visited[nx][ny];
                if (isVisited) continue;

                if (blind) visitedBlind[nx][ny] = true;
                else visited[nx][ny] = true;
                dfs(nx, ny, blind);
            }
        }
    }

    static boolean isInRange(int nx, int ny) {
        return nx >= 0 && nx < N && ny >= 0 && ny < N;
    }

    static boolean isSameColor(int x, int y, int nx, int ny, boolean blind) {
        if (blind)
            return sections[x][y] == sections[nx][ny] || (sections[x][y] == 'R' && sections[nx][ny] != 'B') || (sections[x][y] == 'G' && sections[nx][ny] != 'B');
        return sections[x][y] == sections[nx][ny];
    }
}
