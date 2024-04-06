// 백준 15663번. N과 M(9)
// https://www.acmicpc.net/problem/15663
// silver 2
package dataStructure.graph.p15663;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Sequence2 sequence = new Sequence2();
        sequence.getInput();
        sequence.printResult();
    }
}

class Sequence2 {
    private int n, m;
    private int[] numbers;
    private boolean[] discovered;
    int[] result;

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void getInput() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        numbers = new int[n];
        discovered = new boolean[n];
        result = new int[m];
        
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }
        Arrays.sort(numbers);
        sc.close();
    }

    public void printResult() throws IOException {
        dfs(0);
        bw.flush();
        bw.close();
    }

    private void dfs(int depth) throws IOException {
        if (depth == m) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(result[i] + " ");
            }
            sb.append("\n");
            bw.write(sb.toString());
            return;
        }

        int before = -1;
        for (int i = 0; i < n; i++) {
            if (discovered[i])
                continue;
            if (before != numbers[i]) {
                before = numbers[i];

                discovered[i] = true;
                result[depth] = numbers[i];
                dfs(depth + 1);
                discovered[i] = false;
            }

        }
    }
}

class Sequence {
    private int n, m;
    List<Integer> numbers = new ArrayList<>();
    int[] result;

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void getInput() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int number = sc.nextInt();
            numbers.add(number);
        }

        sc.close();

        numbers.sort((a, b) -> a.compareTo(b));
    }

    public void printResult() throws IOException {
        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0 || i > 0 && numbers.get(i) != numbers.get(i - 1)) {
                result[0] = numbers.get(i);
                numbers.remove(i);
                dfs(1, i);
                numbers.add(i, result[0]);
            }
        }
        bw.flush();
        bw.close();
    }

    private void dfs(int depth, int start) throws IOException {
        if (depth == m) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(result[i] + " ");
            }
            sb.append("\n");
            bw.write(sb.toString());
            return;
        }

        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0 || i > 0 && numbers.get(i) != numbers.get(i - 1)) {
                result[depth] = numbers.get(i);
                numbers.remove(i);
                dfs(depth + 1, i);
                numbers.add(i, result[depth]);
            }
        }
    }
}
