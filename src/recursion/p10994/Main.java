// 백준 10994. 별 찍기-19. 재귀 사용x -> 별 찍는 규칙을 row 기준으로만 봐 재귀적인 방법 사용 불가
// https://www.acmicpc.net/problem/10994
package recursion.p10994;

import java.util.Scanner;

public class Main {
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

        for (int i = 0; i <= 2 * N - 2; i++) {
            printStars(i);
        }
        printResult();
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

    static void printStars(int height) {
        String[] line = new String[length];
        int halfIndex = 2 * N - 2;
        // 가운데 줄. base case
        if (height == halfIndex) {
            for (int i = 0; i < length; i++) {
                if (i % 2 == 0)
                    line[i] = "*";
                else
                    line[i] = " ";
            }
            stars[height] = line;
            return;
        }

        if (height % 2 == 0)
            for (int i = 0; i <= halfIndex; i++) {
                if (i < halfIndex) {
                    if (isLessAndOdd(height, i))
                        line[i] = " ";
                    else
                        line[i] = "*";
                }
                if (i == halfIndex)
                    line[i] = "*";

                line[symmetricIndex(i)] = line[i];
            }

        if (height % 2 != 0)
            for (int i = 0; i <= halfIndex; i++) {
                if (i < halfIndex) {
                    if (isLessAndEven(height, i))
                        line[i] = "*";
                    else
                        line[i] = " ";
                }
                if (i == halfIndex)
                    line[i] = " ";
                line[symmetricIndex(i)] = line[i];
            }
        stars[height] = line;

        stars[symmetricIndex(height)] = line;

    }

    static boolean isLessAndOdd(int height, int i) {
        return height > i && i % 2 != 0;
    }

    static boolean isLessAndEven(int height, int i) {
        return height > i && i % 2 == 0;
    }

    static int symmetricIndex(int i) {
        return 4 * N - 4 - i;
    }
}
