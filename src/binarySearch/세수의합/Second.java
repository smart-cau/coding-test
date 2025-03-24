package binarySearch.세수의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;;

/*
 * 백준 2295번. 세 수의 합. gold 4
 * https://www.acmicpc.net/problem/2295
 * */

// 시공간 복잡도, 문제를 수학적으로 접근하면 풀이 방법의 가짓수는 매우 좁아진다. 이런 확신이 있어야 함.
// 변수 사용이 꼼꼼하지 못해 몇번 틀렸으나 방법은 맞았음
public class Second {
    static int n, answer = 0;
    static int[] input, nums1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        input = new int[n];
        nums1 = new int[n * (n + 1) / 2];
        for (int i = 0; i < n; i++)
            input[i] = Integer.parseInt(br.readLine());

        Arrays.sort(input);

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int a = input[i];
                int b = input[j];
                nums1[idx++] = a + b;
            }
        }

        Arrays.sort(nums1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int d = input[i];
                int c = input[j];
                int find = d - c;
                if (find >= 0 && isExist(find)) answer = Math.max(answer, d);
            }
        }

        System.out.println(answer);
    }

    static boolean isExist(int find) {
        int l = 0, r = nums1.length - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (nums1[m] == find) return true;
            else if (nums1[m] < find) l = m + 1;
            else r = m - 1;
        }
        return false;
    }
}
