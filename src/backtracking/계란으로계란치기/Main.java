package backtracking.계란으로계란치기;

/*
 * 백준 16987번. 계란으로 계란치기. gold 5
 * https://www.acmicpc.net/problem/16987
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, answer = 0;
    static Egg[] eggs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        eggs = new Egg[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int endurance = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(endurance, weight);
        }

        backtrack(0, 0);

        System.out.println(answer);
    }

    static void backtrack(int depth, int count) {
        if (depth == N) { // 이 조건을 N-1로 해놔서 틀림... 마지막 계란으로도 다른 녀석을 칠 수 있음!! 종이 노트에 적을 것
            answer = Math.max(answer, count);
            return;
        }
        Egg picked = eggs[depth];
        for (int i = 0; i < N; i++) {
            if (i == depth) continue;
            Egg another = eggs[i];

            int crashed = crash(picked, another);
            backtrack(depth + 1, count + crashed);
            repair(picked, another);
        }
    }

    static int crash(Egg picked, Egg another) {
        if (picked.endurance <= 0 || another.endurance <= 0)
            return 0;

        int count = 0;

        picked.endurance -= another.weight;
        another.endurance -= picked.weight;
        if (picked.endurance <= 0) ++count;
        if (another.endurance <= 0) ++count;

        picked.crashed = true;

        return count;
    }

    static void repair(Egg picked, Egg another) {
        if (picked.crashed) {
            picked.endurance += another.weight;
            another.endurance += picked.weight;
            picked.crashed = false;
        }
    }

    static class Egg {
        int endurance, weight;
        boolean crashed = false;
        public Egg(int endurance, int weight) {
            this.endurance = endurance;
            this.weight = weight;
        }
    }
}
