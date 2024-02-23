// 백준 2002번. 추월
// https://www.acmicpc.net/problem/2002
// silver 1
package dataStructure.hash.p2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static LinkedHashMap<String, Integer> ins = new LinkedHashMap<>();
    static LinkedHashMap<String, Integer> outs = new LinkedHashMap<>();
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
        String[] keys = outs.keySet().toArray(new String[0]);
        for (int i = 0; i < n; i++) {
            String licensePlate = keys[i];
            int inOrder = ins.get(licensePlate);
            for (int j = i + 1; j < n; j++) {
                String isOverTakeCarPlate = keys[j];
                int isOverTakeOrder = ins.get(isOverTakeCarPlate);
                if (inOrder > isOverTakeOrder) {
                    ++result;
                    break;
                }

            }
        }

        System.out.println(result);
    }
}
