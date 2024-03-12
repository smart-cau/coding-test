// 백준 7576번. 토마토 - Re
// https://www.acmicpc.net/problem/7576
// gold 5
package dataStructure.graph.p7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, ripped = 0, notRipped = 0, empty = 0, result = 0;
    static int[][] box;
    static int[][] days;
    static Queue<Pair> queue = new LinkedList<>();
    static int[] dx = new int[] { 1, -1, 0, 0 };
    static int[] dy = new int[] { 0, 0, 1, -1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();
        solution();
    }

    static void input() throws NumberFormatException, IOException {
        String[] sizes = bw.readLine().split(" ");
        m = Integer.parseInt(sizes[0]);
        n = Integer.parseInt(sizes[1]);

        box = new int[n][m];
        days = new int[n][m];

        for (int row = 0; row < n; row++) {

            String[] line = bw.readLine().split(" ");
            for (int column = 0; column < m; column++) {
                int apple = Integer.parseInt(line[column]);
                box[row][column] = apple;
                if (apple == 1) {
                    ++ripped;
                    queue.add(Pair.of(row, column));
                }
                if (apple == 0)
                    days[row][column] = -1;

                if (apple == -1) {
                    ++notRipped;
                }
            }
        }
    }

    static void solution() {
        bfs();
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                if (days[row][column] > result)
                    result = days[row][column];
            }
        }

        if (ripped + notRipped != n * m)
            result = -1;
        System.out.println(result);
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            for (int K = 0; K < 4; K++) {
                int x = dx[K];
                int y = dy[K];
                if (isInBoundary(pair, x, y)) {
                    int closeX = pair.x + x;
                    int closeY = pair.y + y;
                    int closeApple = box[closeX][closeY];
                    if (closeApple == 0) {
                        box[closeX][closeY] = 1;
                        queue.add(Pair.of(closeX, closeY));
                        days[closeX][closeY] = days[pair.x][pair.y] + 1;
                        ++ripped;
                    }
                }
            }
        }
    }

    static boolean isInBoundary(Pair pair, int x, int y) {
        return pair.x + x >= 0 && pair.x + x < n && pair.y + y >= 0 && pair.y + y < m;
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static public Pair of(int x, int y) {
        return new Pair(x, y);
    }
}
