// 백준 3085번. 사탕 게임
// https://www.acmicpc.net/problem/3085
// silver 2
package bruteForce.p3085;

import java.util.Scanner;

public class Main {
    static int N;
    static String[][] candies;
    static int rowCount = 1;
    static int rowMax = 1;
    static int columnCount = 1;
    static int columnMax = 1;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = sc.nextInt();
        candies = new String[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = sc.next().split("");
            for (int j = 0; j < N; j++) {
                candies[i][j] = line[j];
            }
        }
    }

    static void solution() {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                String current = candies[row][column];
                count(row, column);
                // right
                if (column + 1 < N) {
                    String columnNext = candies[row][column + 1];
                    if (!current.equals(columnNext)) {
                        swap(row, column, row, column + 1);
                        count(row, column);
                        count(row, column + 1);
                        swap(row, column, row, column + 1);
                    }
                }
                // downward
                if (row + 1 < N) {
                    String rowNext = candies[row + 1][column];
                    if (!current.equals(rowNext)) {
                        swap(row, column, row + 1, column);
                        count(row, column);
                        count(row + 1, column);
                        swap(row, column, row + 1, column);
                    }
                }
            }
        }
        System.out.println(Math.max(rowMax, columnMax));
    }

    static void swap(int rowA, int columnA, int rowB, int columnB) {
        String temp = candies[rowA][columnA];
        candies[rowA][columnA] = candies[rowB][columnB];
        candies[rowB][columnB] = temp;
    }

    static void count(int currentRow, int currentColumn) {
        String current = candies[currentRow][currentColumn];
        rowCount = 1;
        columnCount = 1;
        // right
        if (currentColumn + 1 < N) {
            for (int column = currentColumn + 1; column < N; column++) {
                String next = candies[currentRow][column];
                ifSameCandy(current, next, "column");
                if (!current.equals(next)) break;
            }
        }
        // left
        if (currentColumn - 1 >= 0) {
            for (int column = currentColumn - 1; column >= 0; column--) {
                String next = candies[currentRow][column];
                ifSameCandy(current, next, "column");
                if (!current.equals(next)) break;
            }
        }
        // upward
        if (currentRow - 1 >= 0) {
            for (int row = currentRow - 1; row >= 0; row--) {
                String next = candies[row][currentColumn];
                ifSameCandy(current, next, "row");
                if (!current.equals(next)) break;
            }
        }
        // downward
        if (currentRow + 1 < N) {
            for (int row = currentRow + 1; row < N; row++) {
                String next = candies[row][currentColumn];
                ifSameCandy(current, next, "row");
                if (!current.equals(next)) break;
            }
        }
    }

    static void ifSameCandy(String current, String next, String direction) {
        if (current.equals(next)) {
            if (direction.equals("row")) {
                ++rowCount;
                rowMax = Math.max(rowMax, rowCount);
            }
            if (direction.equals("column")) {
                ++columnCount;
                columnMax = Math.max(columnMax, columnCount);
            }
        }
    }
}
