package backtracking.암호만들기;

/*
 * 백준 1759번. 세 수의 합. gold 5
 * https://www.acmicpc.net/problem/1759
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int passwordLength, candidateCount;
    static char[] candidates, answer;
    static String vowels = "aeiou";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        passwordLength = Integer.parseInt(st.nextToken());
        candidateCount = Integer.parseInt(st.nextToken());

        answer = new char[passwordLength];
        candidates = new char[candidateCount];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < candidateCount; i++) {
            candidates[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(candidates);
        backtrack(0, 0);
    }

    static void backtrack(int depth, int idx) {
        if (depth == passwordLength) {
            int vowelCount = getVowelCount(answer);
            int consonantCount = passwordLength - vowelCount;
            if (vowelCount < 1 || consonantCount < 2) return;
            printArray(answer);
            return;
        }
        for (int i = idx; i < candidateCount; i++) {
            answer[depth] = candidates[i];
            backtrack(depth + 1, i + 1);
        }
    }

    static void printArray(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            sb.append(c);
        }
        System.out.println(sb);
    }

    static int getVowelCount(char[] answer) {
        int count = 0;
        for (char c : answer) {
            if (vowels.contains(String.valueOf(c)))
                count++;
        }
        return count;
    }
}
