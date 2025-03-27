package binarySearch.parametricSearch.나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 2805번. 나무 자르기. silver 2
 * https://www.acmicpc.net/problem/2805
 * */

public class Wrong {
    static int n, target, MAX_HEIGHT = 0;
    static int[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        trees = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tree = Integer.parseInt(st.nextToken());
            trees[i] = tree;
            if (tree > MAX_HEIGHT) MAX_HEIGHT = tree;
        }

        // parametric search
        long l = 0;
        long r = MAX_HEIGHT;
        while (l < r) {
            long mid = (l + r) / 2;
            if (notEnough(mid))
                r = mid;
            else
                l = mid + 1;
        }

        System.out.println(r);
    }

    static boolean notEnough(long height) {
        long earned = 0;
        for (int tree : trees) {
            if (tree > height)
                earned += (tree - height);
        }
        return earned <= target;
    }
}

