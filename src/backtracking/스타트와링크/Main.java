package backtracking.스타트와링크;

/*
 * 백준 14889번. 스타트와 링크. silver 1
 * https://www.acmicpc.net/problem/14889
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, memberNum, ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[] members;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        members = new boolean[N];
        memberNum = N / 2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtrack(0, 0);
        System.out.println(ans);
    }

    static void backtrack(int depth, int idx) {
        if (depth == memberNum) {
            // 계산
            int teamA = 0, teamB = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (members[i] && members[j])
                        teamA += map[i][j];
                    else if (!members[i] && !members[j])
                        teamB += map[i][j];
                }
            }
            int diff = Math.abs(teamA - teamB);
            ans = Math.min(ans, diff);
            return;
        }

        for (int i = idx; i < N; i++) {
            if (members[i]) continue;
            members[i] = true;
            backtrack(depth + 1, i + 1);
            members[i] = false;
        }
    }
}
