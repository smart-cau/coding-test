// 백준 5639번. 이진 검색 트리
// https://www.acmicpc.net/problem/5639
// gold 5
package dataStructure.tree.bst.p5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.solve();
    }
}

class Solution {
    private Tree tree = new Tree();

    public void solve() throws IOException {
        getTree();
        printResult();
    }

    private void getTree() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            String s = br.readLine();
            if (s == null)
                break;
            if (s.length() <= 0)
                break;
            st = new StringTokenizer(s);
            int key = Integer.parseInt(st.nextToken());
            tree.insert(key);
        }
    }

    private void printResult() {
        StringBuilder sb = new StringBuilder();
        postOrder(tree.getRoot(), sb);
        System.out.println(sb.toString());
    }

    private void postOrder(TreeNode node, StringBuilder sb) {
        if (node != null) {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.key + "\n");
        }

    }
}

class Tree {
    private TreeNode root;

    public TreeNode getRoot() {
        return this.root;
    }

    public void insert(int key) {
        root = insertHelper(root, key);
    }

    private TreeNode insertHelper(TreeNode node, int key) {
        if (node == null)
            return new TreeNode(key);

        if (node.key > key)
            node.left = insertHelper(node.left, key);
        if (node.key < key)
            node.right = insertHelper(node.right, key);

        return node;
    }
}

class TreeNode {
    int key;
    TreeNode left;
    TreeNode right;

    public TreeNode(int key) {
        this.key = key;
    }
}