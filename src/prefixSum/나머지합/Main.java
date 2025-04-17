package prefixSum.나머지합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 백준 10986번. 나머지 합. gold 3
 * https://www.acmicpc.net/problem/10986
 * #누적합 #수학
 * */

public class Main {
    static int n, target;
    static int[] numbers;
    static long[] prefixSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        prefixSum = new long[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        // set prefixSum
        for (int i = 0; i < n; i++)
            prefixSum[i + 1] = prefixSum[i] + numbers[i];

        long result = solution();

        System.out.println(result);
    }

    private static long solution() {
        long result = 0;
        HashMap<Integer, Integer> modCounts = getModCounts();

        for(Map.Entry<Integer, Integer> entry :modCounts.entrySet()) {
            long mod = entry.getKey();
            int count = entry.getValue();

            result += ((long) count * (count - 1)) / 2;

            if (mod == 0)
                result += count;
        }

        return result;
    }

    static HashMap<Integer, Integer> getModCounts(){
        HashMap<Integer, Integer> modCounts = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int mod = (int)(prefixSum[i] % target);
            int count = modCounts.getOrDefault(mod, 0);
            modCounts.put(mod, count + 1);
        }
        return modCounts;
    }
}
