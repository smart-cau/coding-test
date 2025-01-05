package dataStructure.tree.완전이진트리;

/*
 * 백준 9934번. 완전이진트리. silver 1
 * https://www.acmicpc.net/problem/9934
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int height, nodeCount;
    static int[] tree;
    static int[] input; // 완전이진트리는 1차원 배열로 쉽게 표현 가능
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        nodeCount = (int) Math.pow(2, height) - 1;
        input = new int[nodeCount + 1];
        tree = new int[nodeCount + 1];
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 1; i < nodeCount + 1; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        solution();
        br.close();
    }

    static void solution() {
        toTree(1, nodeCount, 1);
        printTree();
    }

    static void toTree(int start, int end, int idx) {
        int mid = (start + end) / 2;

        // root
        tree[idx] = input[mid];

        // terminate
        if (start == end) return;

        // left
        toTree(start, mid - 1, idx  * 2);

        // right
        toTree(mid + 1, end, idx  * 2 + 1);
    }

    static void printTree() {
        StringBuilder sb = new StringBuilder();
        int change = 1;
        for (int i = 1; i < nodeCount + 1; i++) {
            sb.append(tree[i]).append(" ");
            if (i == change) {
                sb.append("\n");
                change = (change + 1) * 2 - 1;
            }
        }
        System.out.println(sb);
    }
}
