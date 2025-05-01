package prefixSum.두배열의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/*
 * 백준 2143번. 두 배열의 합. gold 3
 * https://www.acmicpc.net/problem/2143
 * #누적합 #맵
 * #투포인터 or #매개변수탐색 으로 풀 수도 있음
 * https://loosie.tistory.com/560
 * */

public class Main {
    static int target, n, m;
    static long[] prefixA, prefixB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // get input
        target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        prefixA = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            prefixA[i] = prefixA[i - 1] + Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        prefixB = new long[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            prefixB[i] = prefixB[i - 1] + Long.parseLong(st.nextToken());
        }

        System.out.println(solution());
    }

    public static long solution() {
        long result = 0;

        Map<Long, Long> mapA = getMap(prefixA);
        Map<Long, Long> mapB = getMap(prefixB);

        for(Entry<Long, Long> entrySet: mapA.entrySet()) {
            long sumA = entrySet.getKey();
            long sumCountA = entrySet.getValue();

            long left = target - sumA;
            if (mapB.containsKey(left)) {
                result += (sumCountA * mapB.get(left));
            }
        }

        return result;
    }

    public static Map<Long, Long> getMap(long[] prefixSum) {
        HashMap<Long, Long> sumCount = new HashMap<>();
        int size = prefixSum.length;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                long sumE = prefixSum[j] - prefixSum[i];
                long count = sumCount.getOrDefault(sumE, 0L);
                sumCount.put(sumE, count + 1);
            }
        }

        return sumCount;
    }
}
