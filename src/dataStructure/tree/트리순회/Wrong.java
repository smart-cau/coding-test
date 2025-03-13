package dataStructure.tree.트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 22856번. 트리 순회. gold 4
 * https://www.acmicpc.net/problem/22856
 * */

public class Wrong {
    static int n, count = 0, result = 0;
    static Main.Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new Main.Node[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int self = Integer.parseInt(st.nextToken()),
                    left = Integer.parseInt(st.nextToken()),
                    right = Integer.parseInt(st.nextToken());
            tree[self] = new Main.Node(left, right);
        }
        pseudoInorder(1);
        System.out.println(result);
    }

    static void pseudoInorder(int nodeIdx) {
        Main.Node node = tree[nodeIdx];

        if (node.left != -1) {
            ++count;
            pseudoInorder(node.left);
        }
        if (node.right != -1) {
            ++count;
            pseudoInorder(node.right);
        }
        // 틀린이유 2: 순회의 끝 노드 == 중위 순회 시 마지막 node 였음(이건 문제에서 이해하기 어렵긴 하다)
        if (nodeIdx == n) {
            result = count;
            return;
        }
        ++count; // 틀린이유 1: 부모로 돌아올 때 ++count 위치가 잘못됨
    }

    static class Node {
        public int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
