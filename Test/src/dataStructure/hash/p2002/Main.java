// 백준 2002번. 추월
// https://www.acmicpc.net/problem/2002
// silver 1
package dataStructure.hash.p2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static HashMap<String, Integer> ins = new HashMap<>();
    static HashMap<String, Integer> outs = new HashMap<>();
    static int result = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();
        solution();
    }

    static void input() throws NumberFormatException, IOException {
        n = Integer.parseInt(br.readLine());
        for (int order = 0; order < n; order++) {
            String licensePlate = br.readLine();
            ins.put(licensePlate, order);
        }

        for (int order = 0; order < n; order++) {
            String licensePlate = br.readLine();
            outs.put(licensePlate, order);
        }
    }

    static void solution() {
        for (String licensePlate : ins.keySet()) {
            int inOrder = ins.get(licensePlate);
            int outOrder = outs.get(licensePlate);
            if (inOrder > outOrder)
                ++result;
            // inOrder == outOrder 예외처리 필요
        }

        System.out.println(result);
    }
}
