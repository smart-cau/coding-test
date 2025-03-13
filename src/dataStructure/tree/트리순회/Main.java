package dataStructure.tree.트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 22856번. 트리 순회. gold 4
 * https://www.acmicpc.net/problem/22856
 * */


public class Main {
    static int n, edgeCount = 0;
    static Node[] tree;
    static int lastNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int self = Integer.parseInt(st.nextToken()),
                    left = Integer.parseInt(st.nextToken()),
                    right = Integer.parseInt(st.nextToken());
            tree[self] = new Node(left, right);
        }

        // 중위 순회의 마지막 노드 찾기
        findLastInorderNode(1);

        // 유사 중위 순회 시 이동한 간선 수 계산
        calculateEdges(1);

        System.out.println(edgeCount);
    }

    // 중위 순회의 마지막 노드를 찾는 함수
    static void findLastInorderNode(int nodeIdx) {
        Node node = tree[nodeIdx];

        if (node.left != -1) {
            findLastInorderNode(node.left);
        }

        lastNode = nodeIdx; // 중위 순회 순서대로 갱신되므로 마지막에는 중위 순회의 마지막 노드가 저장됨

        if (node.right != -1) {
            findLastInorderNode(node.right);
        }
    }

    // 유사 중위 순회 시 이동한 총 간선 수를 계산
    static boolean calculateEdges(int nodeIdx) {
        Node node = tree[nodeIdx];

        if (node.left != -1) {
            edgeCount++; // 왼쪽 자식으로 이동
            if (calculateEdges(node.left)) return true;
            edgeCount++; // 부모로 돌아옴
        }

        if (nodeIdx == lastNode) return true; // 마지막 노드에 도달

        if (node.right != -1) {
            edgeCount++; // 오른쪽 자식으로 이동
            if (calculateEdges(node.right)) return true;
            edgeCount++; // 부모로 돌아옴
        }

        return false;
    }

    static class Node {
        public int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}