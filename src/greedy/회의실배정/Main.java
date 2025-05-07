package greedy.회의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 1931번. 회의실 배정. gold 5
 * https://www.acmicpc.net/problem/1931
 * #그리디
 * */

public class Main {
    static int N;
    static int[][] meetings;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        meetings = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    static int solution() {
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int answer = 1;
        int[] current = meetings[0];

        for (int i = 1; i < N; i++) {
            int[] next = meetings[i];
            if (current[1] > next[0]) continue;
            current = next;
            answer++;
        }

        return answer;
    }
}
