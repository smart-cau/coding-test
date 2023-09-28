// 백준 6236번. Parametric Search
// https://www.acmicpc.net/problem/6236

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numbers = br.readLine().split(" ");

        int N = Integer.parseInt(numbers[0]);
        int M = Integer.parseInt(numbers[1]);

        int[] dailyBudget = new int[N];
        int l = 0, r = N * 10000;

        for (int i = 0; i < N; i++) {
            dailyBudget[i] = Integer.parseInt(br.readLine());
            r += dailyBudget[i];
        }

        int result = -1;

        while (l <= r) {
            int k = (l + r) / 2;
            if (isPossible(dailyBudget, k, M)) {
                result = k;
                r = k - 1;
            } else
                l = k + 1;
        }
        System.out.println(result);
        br.close();
    }

    static boolean isPossible(int[] dailyBudget, int amount, int maxCount) {
        int origin = amount;
        int count = 1;
        for (int budget : dailyBudget) {
            amount -= budget;
            if (amount < 0) {
                amount = origin - budget;
                count++;
            }
            if (budget > origin)
                return false;
            if (count > maxCount)
                return false;
        }
        return true;
    }
}