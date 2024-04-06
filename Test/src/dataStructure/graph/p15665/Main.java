// 백준 15665번. N과 M(11)
// https://www.acmicpc.net/problem/15665
// silver 2
package dataStructure.graph.p15665;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Sequence sequence = new Sequence();
        sequence.getInputs();
        sequence.printResult();
    }
}

class Sequence {
    private int n;
    private int m;
    private int[] result;

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private List<Integer> numbers = new ArrayList<>();

    public void getInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (!numbers.contains(number))
                numbers.add(number);
        }
        numbers.sort((a, b) -> a.compareTo(b));
        br.close();
    }

    public void printResult() throws IOException {
        for (int i = 0; i < numbers.size(); i++) {
            result[0] = numbers.get(i);
            dfs(1);
        }
        bw.flush();
        bw.close();
    }

    private void dfs(int depth) throws IOException {
        if (depth == m) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(result[i] + " ");
            }

            bw.write(sb.toString());
            bw.newLine();
            return;
        }

        for (int i = 0; i < numbers.size(); i++) {
            result[depth] = numbers.get(i);
            dfs(depth + 1);
        }
    }

}
