// 백준 5397번. 키로거
// https://www.acmicpc.net/problem/5397
// silver 2
package dataStructure.linkedList.p5397;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {

    static int count;
    static LinkedList<Character> password;
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        count = scan.nextInt();
    }

    static void solution() {
        for (int index = 0; index < count; index++) {
            password = new LinkedList<>();
            decode(scan.nextLine());
        }

        System.out.println(sb.toString());
    }

    static void decode(String input) {
        charHandler(input);
        setResult();
    }

    static void charHandler(String input) {
        int cursor = 0;
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);

            switch (character) {
                case '<':
                    if (cursor > 0)
                        --cursor;
                    break;
                case '>':
                    if (cursor < password.size())
                        ++cursor;
                    break;
                case '-':
                    if (cursor - 1 >= 0) {
                        password.remove(cursor - 1);
                        --cursor;
                    }
                    break;
                default:
                    password.add(cursor, character);
                    ++cursor;
                    break;
            }
        }
    }

    static void setResult() {
        ListIterator<Character> it = password.listIterator();

        while (it.hasNext()) {
            sb.append(it.next());
        }
        sb.append("\n");
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