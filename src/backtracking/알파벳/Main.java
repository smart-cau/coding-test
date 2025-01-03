package backtracking.알파벳;

/*
 * 백준 1987번. 알파벳. gold 4
 * https://www.acmicpc.net/problem/1987
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, count = 0;
    static char[][] board;
    static boolean[] visited = new boolean[26];
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        solution();
    }

    static void solution() {
        visited[board[0][0] - 'A'] = true;
        backtrack(0, 0, 1);
        System.out.println(count);
    }

    static void backtrack(int row, int column, int depth) {
        boolean notMoved = true;
        for (int i = 0; i < 4; i++) {
            int ny = row + dy[i];
            int nx = column + dx[i];
            if (isMovable(ny, nx)) {
                char c = board[ny][nx];
                visited[c - 'A'] = true;
                notMoved = false;
                backtrack(ny, nx, depth + 1);
                visited[c - 'A'] = false;
            }
        }

        if (notMoved)
            count = Math.max(count, depth);
    }

    static boolean isMovable(int ny, int nx) {
        if (ny < 0 || ny >= R || nx < 0 || nx >= C) return false;
        return !visited[board[ny][nx] - 'A'];
    }


}
