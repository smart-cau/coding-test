package slidingWindow.수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 2259번. 수열. silver 3
 * https://www.acmicpc.net/problem/2259
 * #누적합 #투포인터 #슬라이딩윈도우
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // get first window size
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += numbers[i];
        }

        int answer = sum;

        for (int i = k; i < n; i++) {
            sum = sum + numbers[i] - numbers[i - k];
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
