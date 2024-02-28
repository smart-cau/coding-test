// 백준 2667번. 단지 번호 붙이기
// https://www.acmicpc.net/problem/2667
// silver 1
package dataStructure.graph.p2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static int[][] houses;
    static List<House> discovered = new ArrayList<>();
    static List<List<House>> result = new ArrayList<>();
    static BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();
        solution();
    }

    static void input() throws NumberFormatException, IOException {
        n = Integer.parseInt(bw.readLine());
        houses = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = bw.readLine().split("");
            for (int j = 0; j < n; j++) {
                houses[i][j] = Integer.parseInt(line[j]);
            }
        }
    }

    static void solution() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                List<House> group = new ArrayList<>();
                dfs(x, y, group);
                if (!group.isEmpty())
                    result.add(group);
            }
        }

        result = result.stream()
                .sorted((a, b) -> Integer.compare(a.size(), b.size()))
                .collect(Collectors.toList());
        System.out.println(result.size());
        for (List<House> group : result) {
            System.out.println(group.size());
        }
    }

    static void dfs(int x, int y, List<House> group) {
        if (isStopSearching(x, y))
            return;
        House house = House.of(x, y);

        if (discovered.contains(house))
            return;

        discovered.add(house);
        group.add(house);
        dfs(x + 1, y, group);
        dfs(x - 1, y, group);
        dfs(x, y + 1, group);
        dfs(x, y - 1, group);
    }

    static boolean isStopSearching(int x, int y) {
        return x >= n || y >= n || x < 0 || y < 0 || houses[x][y] == 0;
    }
}

class House {
    public int x;
    public int y;

    public House(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static House of(int x, int y) {
        return new House(x, y);
    }

    @Override
    public boolean equals(Object o) {
        House house = (House) o;
        return this.x == house.x && this.y == house.y;
    }
}
