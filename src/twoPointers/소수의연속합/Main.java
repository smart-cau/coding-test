package twoPointers.소수의연속합;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 백준 1644번. 소수의 연속합. gold 3
 * https://www.acmicpc.net/problem/1644
 * #twoPointers #에라토스테네스의 체 #소수
 * */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        List<Integer> primes = getPrimes(N);

        int result = solution(N, primes);
        System.out.println(result);
    }

    static int solution(int target, List<Integer> primes) {
        int start = 0, end = 0,
            length = primes.size(),
            sum = 0,
            result = 0;

        while (start <= end && end < length) {
            if (sum < target) {
                sum += primes.get(end++);
            }
            else {
                if (sum == target)
                    ++result;
                sum -= primes.get(start++);
            }
        }

        return result;
    }

    // 에라토스테네스의 체
    public static List<Integer> getPrimes(int N) {
        boolean[] isNotPrime = new boolean[N + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i * i <= N; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= N; j += i)
                    isNotPrime[j] = true;
            }
        }

        for (int i = 1; i <= N; i++)
            if (!isNotPrime[i])
                primes.add(i);
        primes.add(0); // 꼭 여기에 붙여줘야 함... two pointers 풀이를 위한거임
        return primes;
    }
}
