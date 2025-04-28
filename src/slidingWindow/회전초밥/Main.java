package slidingWindow.회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 백준 15961번. 회전 초밥. gold 4 #RE
 * https://www.acmicpc.net/problem/15961
 * #투포인터 #슬라이딩윈도우
 * */

public class Main {
    static int numbers, types, windowSize, coupon;
    static int[] sushi;
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = Integer.parseInt(st.nextToken());
        types = Integer.parseInt(st.nextToken());
        windowSize = Integer.parseInt(st.nextToken());
        coupon = Integer.parseInt(st.nextToken());

        sushi = new int[numbers];
        for (int i = 0; i < numbers; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution());
    }

    public static int solution() {
        int[] counts = new int[types + 1];
        int uniqueCount = 0;

        // 초기 윈도우 설정
        for (int i = 0; i < windowSize; i++) {
            if (counts[sushi[i]]++ == 0) {
                uniqueCount++;
            }
        }

        // 쿠폰 처리
        int maxCount = counts[coupon] == 0 ? uniqueCount + 1 : uniqueCount;

        // 슬라이딩 윈도우 적용
        for (int start = 1; start < numbers; start++) {
            // 맨 앞 초밥 제거
            int removeSushiNo = sushi[start - 1];
            counts[removeSushiNo]--;
            if (counts[removeSushiNo] == 0) {
                uniqueCount--;
            }

            // 새로운 초밥 추가
            int end = (start + windowSize - 1) % numbers;
            int addSushiNo = sushi[end];
            if (counts[addSushiNo] == 0) {
                uniqueCount++;
            }
            counts[addSushiNo]++;

            // 쿠폰 고려하여 현재 고유 초밥 개수 계산
            int currentCount = counts[coupon] == 0 ? uniqueCount + 1 : uniqueCount;
            maxCount = Math.max(maxCount, currentCount);
        }

        return maxCount;
    }


    public static int wrong() {
        int[] counts = new int[types + 1];
        int result = 0;
        for (int i = 0; i < windowSize; i++) {
            int sushNo = sushi[i];
            if (counts[sushNo] == 0) {
                result++;
            }
            counts[sushNo]++;
        }

        result = counts[coupon] == 0 ? result + 1 : result;

        int count = result;
        for (int start = 1; start < numbers; start++) {
            int removeSushiNo = sushi[start - 1];
            counts[removeSushiNo]--;
            if (counts[removeSushiNo] == 0) {
                count--;
                if (removeSushiNo == coupon)
                    count++;
            }

            int end = (start + windowSize - 1) % numbers;
            int addSushiNo = sushi[end];
            counts[addSushiNo]++;
            if (counts[addSushiNo] == 1)
                ++count;

            result = Math.max(result, count);
        }

        return result;
    }
}
