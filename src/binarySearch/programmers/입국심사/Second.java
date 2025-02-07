package binarySearch.programmers.입국심사;

/*
 * 프로그래머스. 입국심사 . Level 3
 * https://school.programmers.co.kr/learn/courses/30/lessons/43238?language=java
 * */

public class Second {
    public long solution(int n, int[] times) {
        long answer = 0;
        int size = times.length;
        long l = 0, r = times[size - 1] * (long)n;

        while (l <= r) {
            long mid = (l + r) / 2;
            long acc = 0;
            for (int time: times)
                acc += (mid / time);
            if (acc >= n) {
                r = mid - 1;
                answer = mid;
            } else
                l = mid + 1;
        }

        return answer;
    }
}
