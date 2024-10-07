// 백준 2667번. 단지 번호 붙이기
// https://www.acmicpc.net/problem/2667
// silver 1
// 책 '자바 알고리즘 인터뷰' 421p 참고한 풀이
package dataStructure.graph.p2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, count;
    static char[][] houses;

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();
        solution();
    }

    static void input() throws NumberFormatException, IOException {
        n = Integer.parseInt(br.readLine());
        houses = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                houses[i][j] = line.charAt(j);
            }
        }
    }

    static void solution() {
        List<Integer> result = new ArrayList<>();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (houses[x][y] == '1') {
                    count = 0;
                    dfs(x, y);
                    result.add(count);
                }
            }
        }

        result.sort((a, b) -> a.compareTo(b));
        System.out.println(result.size());
        for (int count : result)
            System.out.println(count);

    }

    static void dfs(int x, int y) {
        if (isStopSearching(x, y))
            return;
        // 방문한 집은 0으로 처리
        houses[x][y] = '0';
        count += 1;
        dfs(x + 1, y);
        dfs(x - 1, y);
        dfs(x, y + 1);
        dfs(x, y - 1);
    }

    static boolean isStopSearching(int x, int y) {
        return x >= n || y >= n || x < 0 || y < 0 || houses[x][y] == '0';
    }
}
