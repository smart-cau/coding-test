package binarySearch.programmers.입국심사;

/*
* 프로그래머스. 입국심사 . Level 3
* https://school.programmers.co.kr/learn/courses/30/lessons/43238?language=java
* */

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        int length = times.length;
        Arrays.sort(times);

        long l = 0;
        long r = times[length - 1] * (long)n;

        while (l <= r) {
            long mid = (l + r) / 2;
            long availablePeople = 0;
            // 아래 for 문이 핵심 로직!! 이걸 못떠올렸다... 문제의 예시를 그대로 읽고 거기에 갇혀서는 아래와 같은 생각을 하기 어렵다.
            for (int time: times)
                availablePeople += (mid / time);

            if (availablePeople >= n) {
                r = mid - 1;
                answer = mid;
            }
            else
                l = mid + 1;
        }

        return answer;
    }
}

