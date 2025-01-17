package binarySearch.세수의합;

/*
 * 백준 2295번. 세 수의 합. gold 4
 * https://www.acmicpc.net/problem/2295
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 핵심 아이디어를 못떠올려서 실패
public class Fail {
    static int N, result = 0;
    static List<Integer> list = new ArrayList<>();
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            set.add(Integer.parseInt(st.nextToken()));
        }

        list = new ArrayList<>(set);
        list.sort((a, b) -> a - b);
        
        solution();

        System.out.println(result);
    }
    
    static void solution() {
        for (int i = set.size() - 1; i >= 0; i--) {
            if (result != 0)
                return;
            for (int p = i - 1; p >= 0; p--) {
                if (result != 0)
                    return;
                if (list.get(p) * 3 == list.get(i)) {
                    result = list.get(i);
                    return;
                }
                binarySearch(i, list.get(i) - list.get(p), p);
            }
        }
    }

    static void binarySearch(int idx, int target, int right) {
        int left = 0;
        while (left < right) {
            if (list.get(left) + list.get(right) == target) {
                result = list.get(idx);
                break;
            }

            int mid = (left + right) / 2;
            int medium = list.get(mid);
            if (medium * 2 == target) {
                result = list.get(idx);
                break;
            } else if (medium < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
    }
}
