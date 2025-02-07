package binarySearch.히오스프로게이머;

/*
 * 백준 16564번. 히오스 프로게이머. silver 1
 * https://www.acmicpc.net/problem/16564
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Second {
    static int N, K, answer = 0;
    static int[] characters;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        characters = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            characters[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(characters);

        solution();

        System.out.println(answer);
    }

    static void solution() {
        int l = characters[0], r = characters[N - 1] + K; // 핵심 아이디어
        while (l <= r) {
            int mid = (l + r) / 2;
            long gave = 0; // 반드시 챙겨야 하는 디테일
            int diff;
            for (int i = 0; i < N; i++) {
                diff = mid - characters[i];
                if (diff > 0) gave += diff;
                else break;
            }
            // gave & diff의 활용 -> 수학적 사고
            if (gave <= K) {
                answer = Math.max(answer, mid);
                l = mid + 1;
            } else
                r = mid - 1;
        }
    }
}
