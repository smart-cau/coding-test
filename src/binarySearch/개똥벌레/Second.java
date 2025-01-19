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

public class Second {
    static int N, H;
    static int[] up, down;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new int[N / 2];
        down = new int[N / 2];

        for (int i = 0; i < N / 2; i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            up[i] = a;
            down[i] = b;
        }
    }

    static void solution() {
        Arrays.sort(up);
        Arrays.sort(down);

        int minBroken = Integer.MAX_VALUE, count = 0;
        for (int height = 1; height < H + 1; height++) {
            int tempBroken = search(up, height) + search(down, H - height + 1);
            if (tempBroken < minBroken) {
                minBroken = tempBroken;
                count = 1;
            } else if (tempBroken == minBroken) {
                count++;
            }
        }
        System.out.println(minBroken + " " + count);
    }

    static int search(int arr[], int target) {
        int l = 0, r = N / 2;

        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return arr.length - r;
    }
}
