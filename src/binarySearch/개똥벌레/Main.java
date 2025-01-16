package binarySearch.개똥벌레;

/*
 * 백준 3020번. 개똥벌레. gold 5
 * https://www.acmicpc.net/problem/3020
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, H, min = Integer.MAX_VALUE, count; // 길이 N은 짝수. H는 높이
    static int[] down, up;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        down = new int[N / 2];
        up = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            down[i] = a;
            up[i] = b;
        }

        Arrays.sort(down);
        Arrays.sort(up);

        min = N;
        count = 0;

        for (int i = 1; i < H + 1; i++) {
            int conflict = binarySearch(i, down) + binarySearch(H - i + 1, up);
            if (min == conflict) {
                count++;
            } else if (min > conflict) {
                min = conflict;
                count = 1;
            }
        }

        System.out.println(min + " " + count);
    }

    // 이분 탐색 중, lower bound, upper bound에 대한 이해 필요
    static int binarySearch(int target, int[] arr) {
        int l = 0, r = N / 2;

        while (l < r) { // 언제 부등호 들어가고, 안들어가는지 알아야 함
            int mid = (l + r) / 2;
            if (arr[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }

        return arr.length - r;
    }
}
