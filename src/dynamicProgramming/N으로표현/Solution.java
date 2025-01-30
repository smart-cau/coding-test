package dynamicProgramming.N으로표현;

/*
 * 프로그래머스. DP. Level 3. N으로 표현
 * https://school.programmers.co.kr/learn/courses/30/lessons/42895
 * DP로 완전탐색하기! 이런 생각은 어떻게 하나...
 * */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        for (int i = 1; i <=8; i++) { // i == 최대 자리수
            // 숫자 N이 N개 있는 case
            // N, NN, NNN, NNNN...
            int repeatN = Integer.parseInt(String.valueOf(N).repeat(i));
            dp.get(i).add(repeatN);

            // N개의 숫자로 사칙연산
            for (int j = 1; j < i; j++) {
                for (int a: dp.get(j)) { // 최대 자리수보다 작은 자리 수
                    for (int b: dp.get(i - j)) { // 남은 자리 수
                        // +
                        dp.get(i).add(a + b);
                        // -
                        dp.get(i).add(a - b);
                        dp.get(i).add(b - a);
                        // *
                        dp.get(i).add(a * b);
                        // /
                        if (b != 0) dp.get(i).add(a / b);
                        if (a != 0) dp.get(i).add(b / a);
                    }
                }
            }
            if (dp.get(i).contains(number))
                return i;
        }
        return -1;
    }
}
