// 백준 9663번. N-Queen - Re
// https://www.acmicpc.net/problem/9663
// gold 4
package dataStructure.graph.p9663;

import java.util.Scanner;

public class Main {
    static int n;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        NQueens2 nQueens = new NQueens2(sc.nextInt());
        nQueens.printResult();
        sc.close();
    }
}

class NQueens {
    private int n;
    private int result = 0;
    private int[][] board;

    public NQueens(int n) {
        this.n = n;
        this.board = new int[n][n];
    }

    private void setQueenInFirstLine() {
        for (int i = 0; i < n; i++) {
            board[0][i] = 1;
            dfs(1);
            board[0][i] = -1;
        }
    }

    private void dfs(int depth) {
        if (depth == n) {
            ++result;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (possible(depth, i)) {
                board[depth][i] = 1;
                dfs(depth + 1);
                board[depth][i] = 0;
            }
        }
    }

    private boolean possible(int depth, int column) {
        // 세로 위 탐색
        for (int i = depth - 1; i >= 0; i--)
            if (board[i][column] == 1)
                return false;

        // 우측 위 대각선 탐색
        int dx = depth - 1;
        int dy = column + 1;
        while (dx >= 0 && dy < n)
            if (board[dx--][dy++] == 1)
                return false;

        // 좌측 위 대각선 탐색
        dx = depth - 1;
        dy = column - 1;
        while (dx >= 0 && dy >= 0)
            if (board[dx--][dy--] == 1)
                return false;

        return true;
    }

    public void printResult() {
        setQueenInFirstLine();
        System.out.println(result);
    }
}

class NQueens2 {
    private int n;
    private int[] board; // index = depth(row), value = column
    private int result = 0;

    public NQueens2(int n) {
        this.n = n;
        board = new int[n];
    }

    private void setQueenInFirstLine() {
        for (int column = 0; column < n; column++) {
            board[0] = column;
            dfs(1);
        }
    }

    private void dfs(int depth) {
        if (depth == n) {
            ++result;
            return;
        }

        for (int column = 0; column < n; column++) {
            board[depth] = column;
            if (possible(depth)) {
                dfs(depth + 1);
            }
        }
    }

    private boolean possible(int depth) {
        for (int i = depth - 1; i >= 0; i--) {
            // 세로 위 탐색
            if (board[i] == board[depth])
                return false;

            // 대각선 위 탐색
            if (Math.abs(board[i] - board[depth]) == Math.abs(i - depth))
                return false;
        }
        return true;
    }

    public void printResult() {
        setQueenInFirstLine();
        System.out.println(result);
    }
}