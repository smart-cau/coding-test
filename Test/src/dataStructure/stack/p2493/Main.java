// 백준 2493번. 탑
// https://www.acmicpc.net/problem/2493
// gold 4
package dataStructure.stack.p2493;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Deque<Tower> towers = new ArrayDeque<>();
    static Deque<String> result = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = scan.nextInt();
        for (int i = 0; i < N; i++) {
            towers.push(new Tower(i + 1, scan.nextInt()));
        }
    }

    static void solution() {
        while (towers.size() != 0) {
            if (towers.size() == 1) {
                result.push("0");
                towers.pop();
                break;
            }

            Tower receiver = findReceiver();
            if (receiver != null) {
                result.push(Integer.toString(receiver.index));
            }

            // 높은 탑을 못찾을 경우
            if (receiver == null) {
                result.push("0");
            }

            towers.pop();
        }

        // print
        Iterator<String> it = result.iterator();
        while (it.hasNext()) {
            sb.append(it.next() + " ");
        }
        System.out.println(sb.toString());
    }

    static Tower findReceiver() {
        Tower receiver = null;
        Tower head = towers.peek();
        Iterator<Tower> it = towers.iterator();
        // 높은 탑 찾기
        while (it.hasNext()) {
            Tower tower = it.next();
            if (head.height < tower.height) {
                receiver = tower;
                break;
            }
        }
        return receiver;
    }

    static class Tower {
        int index;
        int height;

        public Tower(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
