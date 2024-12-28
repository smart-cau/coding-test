package implementation.치킨배달Re;

/*
 * 백준 15686번. 치킨배달. gold 5
 * https://www.acmicpc.net/problem/15686
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] city;
    static List<House> houses = new ArrayList<>();
    static List<Chicken> chickens = new ArrayList<>();
    static boolean[] survived;

    static int RESULT = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cell = Integer.parseInt(st.nextToken());
                city[i][j] = cell;
                if (cell == 1)
                    houses.add(new House(i, j));
                if (cell == 2)
                    chickens.add(new Chicken(i, j));
            }
        }

        survived = new boolean[chickens.size()];

        // backtrack
        backtrack(0, 0);

        System.out.println(RESULT);
    }

    static void backtrack(int depth, int index) {
        if (depth == M) {
            int res = 0;

            for (House house : houses) {
                int temp = Integer.MAX_VALUE;

                // 어떤 집과 치킨집 중 open한 치킨집의 모든 거리를 비교한다.
                // 그 중, 최소 거리를 구한다.
                for (int j = 0; j < chickens.size(); j++) {
                    if (survived[j]) {
                        int distance = Math.abs(house.row - chickens.get(j).row)
                                + Math.abs(house.column - chickens.get(j).column);

                        temp = Math.min(temp, distance);
                    }
                }
                res += temp;
            }
            RESULT = Math.min(RESULT, res);
            return;
        }

        for (int i = index; i < chickens.size(); i++) {
            survived[i] = true;
            backtrack(depth + 1, i + 1);
            survived[i] = false;
        }
    }

    /*
    * 우선, 맵을 입력 받는다. 입력 받으면서, house list에 house를 추가. chicken도 마찬가지로 list 저장. house의 크기만큼 meanDistance 배열을 가짐
    * 그리고 치킨 list를 기준으로 평균 거리를 계산함. 그리고 평균거리를 기준으로 작은 순으로 정렬해서 치킨집을 남김. 남은 치킨집 기준으로 거리 계산하면 끝!
    * */

    static class House {
        int row;
        int column;
        int distance = 999999999;

        public House(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    static class Chicken {
        int row;
        int column;

        public Chicken(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
