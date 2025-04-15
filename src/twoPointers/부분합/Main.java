package twoPointers.부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 1806번. 수 고르기. gold 4
 * https://www.acmicpc.net/problem/1806
 * #이분탐색 #누적합
 * */

public class Main {
    static int n, target;
    static int[] numbers;
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        int result = solution();
        System.out.println(result);
    }

    public static int solution() throws IOException {
        numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, total = numbers[0], result = Integer.MAX_VALUE;

        while (start <= end && end < n) {
            if (total < target) {
                if (end + 1 == n) break;
                ++end;
                total += numbers[end];
            }
            else {
                result = Math.min(result, end - start + 1);
                total -= numbers[start];
                ++start;
            }
        }

        if (result == Integer.MAX_VALUE)
            result = 0;

        return result;
    }


    // 누적합 배열과 이분탐색을 함께 활용
    public static int mySolution() throws IOException {
        numbers = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + numbers[i];;
        }

        int start = 0, end = 1, min = Integer.MAX_VALUE;
        while (start <= end && end <= n) {
            int sum = sums[end] - sums[start];
            if (sum < target)
                ++end;
            else {
                min = Math.min(min, end - start);
                ++start;
            }
        }
        if (min == Integer.MAX_VALUE)
            min = 0;

        return min;
    }
}
