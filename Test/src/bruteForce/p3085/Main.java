// 백준 3085번. 사탕 게임
// https://www.acmicpc.net/problem/3085
// silver 2
package bruteForce.p3085;

import java.util.Scanner;

enum Candy {
    RED("C"),
    BLUE("P"),
    GREEN("Z"),
    YELLOW("Y"),
    DEFAULT("");

    private final String label;

    Candy(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}

class Same {
    public Candy type;
    public int start;
    public int end;
    public int length;

    public Same(Candy type, int start, int end) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.length = end - start + 1;
    }

    public void setStart(int start) {
        this.start = start;
        this.length = end - start + 1;
    }

    public void setEnd(int end) {
        this.end = end;
        this.length = end - start + 1;
    }
}

public class Main {
    static int N;
    static Candy[][] candies;
    static Same longest = new Same(Candy.DEFAULT, 0, 0);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = sc.nextInt();
        candies = new Candy[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = sc.nextLine().split("");
            for (int j = 0; j < N; j++) {
                candies[i][j] = Candy.valueOf(line[j]);
            }
        }
    }

    static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Candy candy = candies[i][j];
                Same same = new Same(candy, j, j);
                /* right search till meet diff candy
                 *{ if same.type == candy
                    * setEnd(+1)
                    * if longest.length < same.length -> longest = same } -> make it to a function
                 * if same.type != candy
                    * j + 1과 swap test. 양 쪽으로 탐색 실시
                    * left search till meet diff candy
                        * if same.type == candy
                    * right search till meet diff candy
                */
            
                /* downward search till meet diff candy
                 * 
                */
            }
        }
    }
}
