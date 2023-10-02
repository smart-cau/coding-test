// 백준 17478번. 재귀함수가 뭔가요?
// https://www.acmicpc.net/problem/17478

import java.util.Scanner;

public class Main {
    static int N;
    static StringBuffer sb = new StringBuffer();
    static String QUESTION = "\"재귀함수가 뭔가요?\"\n";
    static String ANSWER1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
    static String ANSWER2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
    static String ANSWER3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
    static String ANSWER_LAST = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
    static String SAID = "라고 답변하였지.\n";
    static String BAR = "____";
    static String[] SENTENCES = { QUESTION, ANSWER1, ANSWER2, ANSWER3 };

    public static void main(String[] args) {
        input();
        pro();
        System.out.println(sb.toString());
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();
    }

    static void pro() {
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");

        recursion(0);
    }

    static void recursion(int n) {
        String BARS = BAR.repeat(n);
        if (n == N) {
            sb.append(BARS + QUESTION);
            sb.append(BARS + ANSWER_LAST);
            sb.append(BARS + SAID);
            return;
        }
        for (String sentence : SENTENCES) {
            sb.append(BARS + sentence);
        }
        recursion(n + 1);
        sb.append(BARS + SAID);
    }
}
