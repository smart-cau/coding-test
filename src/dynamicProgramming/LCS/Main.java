package dynamicProgramming.LCS;

/*
 * 백준 9251번. LCS. gold 5
 * https://www.acmicpc.net/problem/9251
 * */

import java.util.Scanner;

public class Main {
    static String str1, str2;
    static int[][] lcs;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str1 = sc.nextLine();
        str2 = sc.nextLine();

        lcs = new int[str1.length() + 1][str2.length() + 1];

        solution();
    }

    static void solution () {
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                else
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            }
        }

        System.out.println(lcs[str1.length()][str2.length()]);
    }
}
