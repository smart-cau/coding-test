package binarySearch.히오스프로게이머;

/*
 * 백준 16564번. 히오스 프로게이머. silver 1
 * https://www.acmicpc.net/problem/16564
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, addablePoint, result;
    static int[] characters;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        addablePoint = Integer.parseInt(st.nextToken());

        characters = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            characters[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(characters);

        solution();

        System.out.println(result);
    }

    static void solution() {
        int l = characters[0], r = characters[N - 1] + addablePoint;

        while (l <= r) {
            int mid = (l + r) / 2;

            long sum = 0; // int 형으로 하면 오답!
            int diff;
            for (int i = 0; i < N; i++) {
                diff = mid - characters[i];
                if (diff > 0) sum += diff;
                else break;
            }

            if (sum <= addablePoint) {
                result = Math.max(result, mid);
                l = mid + 1;
            } else
                r = mid - 1;
        }
    }
}

