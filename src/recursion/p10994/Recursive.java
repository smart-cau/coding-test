// 백준 10994. 별 찍기-19. -> 별 찍는 규칙을 row 단위가 아니라 square 기준으로 봐 재귀적인 접근 가능
// https://www.acmicpc.net/problem/10994
package recursion.p10994;

import java.util.Scanner;

public class Recursive {
    static int N;
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int length;
    static String[][] stars;

    public static void main(String[] args) {
        N = sc.nextInt();
        pro();
    }

    static void pro() {
        length = 4 * N - 3;
        stars = new String[length][length];

        // 모든 배열을 공백으로 초기화
        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                stars[row][column] = " ";
            }
        }

        // 별 부분만 따로 설정
        setStar(0, length - 1);

        printResult();
    }

    static void setStar(int row, int count) {
        for (int i = row; i <= count; i++) {
            stars[row][i] = "*"; // upper side
            stars[i][count] = "*"; // right side
            stars[i][row] = "*"; // left side
            stars[count][i] = "*"; // down side
        }
        if (count == 0)
            return;
        setStar(row + 2, count - 2);
    }

    static void printResult() {
        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                sb.append(stars[row][column]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
