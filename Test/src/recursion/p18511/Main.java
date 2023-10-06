// 백준 18511. 큰 수 구성하기
// https://www.acmicpc.net/problem/18511

// 너무 사람의 문제풀이 방식으로만 갇혀 생각하다 결국 못풀었다... 
// Brute force하게 접근해도 Big O가 크지 않다면 해당 방법 사용을 적극 고려할 것!
package recursion.p18511;

import java.util.Scanner;

public class Main {
    static int N;
    static int kCount;
    static int[] K;
    static int max = 0;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input() {
        N = sc.nextInt();
        kCount = sc.nextInt();
        K = new int[kCount];
        for (int i = 0; i < kCount; i++) {
            K[i] = sc.nextInt();
        }
    }

    static void pro() {
        solution(0);
        System.out.println(max);
    }

    static void solution(int number) {
        if (number > N)
            return;

        number *= 10;

        // brute force + recursion
        // 각 숫자로 만들 수 있는 모든 경우의 수 탐색
        for (int i = 0; i < kCount; i++) {
            int temp = number + K[i];
            // temp가 N보다 커져버린 경우는 계속해서 무시
            if (temp > N)
                continue;
            if (temp > max)
                max = temp;
            
            solution(temp);
        }
    }

}
