package backtracking.스도쿠Re;

/*
 * 백준 2580번. 스도쿠. gold 4
 * https://www.acmicpc.net/problem/2580
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Answer {
    static int SIZE = 9;
    static int[][] sudoku = new int[SIZE][SIZE];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                int cell = Integer.parseInt(st.nextToken());
                sudoku[i][j] = cell;
            }
        }

        solution();
    }

    static void solution() {
        backtrack(0, 0);
    }

    static void backtrack(int row, int col) {
        if (col == SIZE) {
            backtrack(row + 1, 0);
            return;
        }

        if (row == SIZE) {
            print();
            System.exit(0);
        }

        if (sudoku[row][col] == 0) {
            for (int i = 1; i <= SIZE; i++) {
                if (isValid(row, col, i)) {
                    sudoku[row][col] = i;
                    backtrack(row, col + 1);
                    sudoku[row][col] = 0;
                }
            }
        } else
            backtrack(row, col + 1);
    }

    static boolean isValid(int row, int col, int num) {
        // row
        for (int i = 0; i < SIZE; i++) {
            if (sudoku[i][col] == num) return false;
        }

        // col
        for (int i = 0; i < SIZE; i++) {
            if (sudoku[row][i] == num) return false;
        }

        // cel
        int sectionRow = (row / 3) * 3;
        int sectionCol = (col / 3) * 3;
        for (int i = sectionRow; i < sectionRow + 3; i++) {
            for (int j = sectionCol; j < sectionCol + 3; j++) {
                if (sudoku[i][j] == num) return false;
            }
        }

        return true;
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
