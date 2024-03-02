// 백준 7576번. 토마토
// https://www.acmicpc.net/problem/7576
// gold 5
package dataStructure.graph.p7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
    static int m, n;
    static int[][] box;

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();
        solution();
    }

    static void input() throws NumberFormatException, IOException {
        m = Integer.parseInt(bw.readLine());
        n = Integer.parseInt(bw.readLine());

        box = new int[m][n];

        for (int row = 0; row < m; row++) {
            String[] line = bw.readLine().split(" ");
            for (int column = 0; column < n; column++) {
                box[row][column] = Integer.parseInt(line[column]);
            }
        }
    }

    static void solution() {
        int count = 0;
        while (true) { // 종료조건???
            for (int row = 0; row < m; row++) {
                for (int column = 0; column < n; column++) {
                    int status = box[row][column];
                    if (status == 1)
                        bfs(row, column);
                }
            }
            ++count;
        }

    }
    // 2606번 bfs 참고
    static void bfs(int row, int column) {
        if (box[row + 1][column] == 0)
            box[row + 1][column] = 1;
        if (box[row - 1][column] == 0)
            box[row + 1][column] = 1;
        if (box[row][column + 1] == 0)
            box[row][column + 1] = 1;
        if (box[row][column - 1] == 0)
            box[row][column - 1] = 1;
    }
}
