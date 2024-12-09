/*
* 백준 1012번. 유기농배추 silver 2
* */
package graph.bfs.유기농배추;

import java.util.*;
import java.io.*;

public class Main {
    static int test_case_number;
    static int row;
    static int col;
    static int cabbage_num;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] farm;
    static boolean[][] visited;
    static int result = 0;
    static LinkedList<Integer> results = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        test_case_number = Integer.parseInt(st.nextToken());

        for (int i = 0; i < test_case_number; i++) {
            result = 0;
            st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            cabbage_num = Integer.parseInt(st.nextToken());
            LinkedList<Point> cabbages = new LinkedList<>();

            farm = new int[row][col];
            visited = new boolean[row][col];

            for (int j = 0; j < cabbage_num; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                farm[a][b] = 1;
                cabbages.add(new Point(a, b));
            }

            while (!cabbages.isEmpty()) {
                Point p = cabbages.pop();
                if (visited[p.x][p.y]) continue;
                visited[p.x][p.y] = true;
                bfs(p.x, p.y);
            }
            results.add(result);
        }
        for (Integer integer : results) {
            System.out.println(integer);
        }
    }

    static void bfs(int px, int py) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(px, py));
        ++result;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && !visited[nx][ny] && farm[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
