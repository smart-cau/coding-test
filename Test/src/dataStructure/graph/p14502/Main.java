// 백준 14502번. 연구소
// https://www.acmicpc.net/problem/14502
// gold 4
package dataStructure.graph.p14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[][] lab;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        lab = new int[n][m];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < m; column++) {
                lab[row][column] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solution() {
        // 1. 모든 경우에 대해 벽을 3개 세운다
        // 2. 벽이 다 세워지면 바이러스를 퍼트린다
        // 3. 안전 구역을 count 해 max 값을 갱신한다. 이후 1번으로 돌아간다
        setWall(0);
    }

    static void setWall(int count) {
        if (count == 3) {
            bfs();
            return;
        }

        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                if (lab[row][column] == 0) {
                    lab[row][column] = 1;
                    setWall(++count);
                    lab[row][column] = 0;
                }
            }
        }
    }

    static void bfs() {
        int[][] copied = copyLab();
        Queue<int[]> queue = new LinkedList<>(); // {row, column}
        int[] dx = new int[] { 0, 0, 1, -1 };
        int[] dy = new int[] { 1, -1, 0, 0 };

        // init queue
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                if (copied[row][column] == 2)
                    queue.add(new int[] { row, column });
            }
        }

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int row = pair[0];
            int column = pair[1];

        }
    }

    static int[][] copyLab() {
        int[][] copied = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                copied[row][column] = lab[row][column];
            }
        }
        return copied;
    }

}
