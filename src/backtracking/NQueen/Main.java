package backtracking.NQueen;

/*
 * 백준 9663번. N-Queen. gold 4. 2d, 1d arr 풀이
 * https://www.acmicpc.net/problem/9663
 * */

import java.util.Scanner;

public class Main {
    static int N;
    static int[][] board;
    static int[] board1d;
    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new int[N][N];
        board1d = new int[N + 1];

        solution();
    }

    static void solution() {
        // 2차원 풀이
//        backtrack2d(0);
        // 1차원 풀이
        backtrack1d(1);
        System.out.println(count);
    }

    static void backtrack1d(int row) {
        if (row == N + 1) {
            ++count;
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (isSafe1d(row, i)) {
                board1d[row] = i;
                backtrack1d(row + 1);
                board1d[row] = 0;
            }
        }
    }

    static boolean isSafe1d(int row, int col) {
        for (int prevRow = 1; prevRow < row; prevRow++) {
            int prevCol = board1d[prevRow];
            if (prevCol == col) return false;
            if (Math.abs(prevRow - row) == Math.abs(prevCol - col)) return false;
        }

        return true;
    }

    static void backtrack2d(int row) {
        if (row == N) {
            ++count;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSafe(row, i)) {
                board[row][i] = 1;
                backtrack2d(row + 1);
                board[row][i] = 0;
            }
        }
    }

    static boolean isSafe(int row, int col) {
        // 세로
        for (int i = 0; i < N; i++) {
            if (board[i][col] == 1) return false;
        }

        // 대각선 - 좌상
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // 대각선 - 좌하
        for (int i = row + 1, j = col - 1; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) return false;
        }

        // 대각선 - 우상
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) return false;
        }

        // 대각선 - 우하
        for (int i = row + 1, j = col + 1; i < N && j < N; i++, j++) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }
}
