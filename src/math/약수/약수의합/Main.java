package math.약수.약수의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 17425번. 약수의 합. gold 4 #RE
 * https://www.acmicpc.net/problem/17425
 * #수학 #에라토스테네스의체 #누적합
 * */

public class Main {
    static final int MAX = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int cases = Integer.parseInt(st.nextToken());
        long[] cSum = getDivisorSum();

        while (cases-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            sb.append(cSum[num]).append("\n");
        }
        System.out.println(sb);
    }

    private static long[] getDivisorSum() {
        long[] fValues = new long[MAX];

        // 아래 방법 같은 '약수의 합'을 구하는 방식을 못떠올려서 못품.
        for (int i = 1; i < MAX; i++)
            // 약수의 합 구하기
            // 에라토스테네스의 체와 유사한 방식. 자신을 약수로 가지는 수에게, 자신을 더함
            for (int j  = i; j < MAX; j += i)
                fValues[j] += i;

        long[] cSum = new long[MAX];
        for (int i = 1; i < MAX; i++)
            cSum[i] = cSum[i - 1] + fValues[i];

        return cSum;
    }
}
