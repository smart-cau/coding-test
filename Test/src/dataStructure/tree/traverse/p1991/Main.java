// 백준 1991번. 트리 순회
// https://www.acmicpc.net/problem/1991
// silver 1
package dataStructure.tree.traverse.p1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.solve();
    }
}

class Solution {
    private int n;
    private TreeNode root;

    public void solve() throws IOException {
        getRoot();

        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        System.out.println(sb.toString());
        sb = new StringBuilder();
        inOrder(root, sb);
        System.out.println(sb.toString());
        sb = new StringBuilder();
        postOrder(root, sb);
        System.out.println(sb.toString());
    }

    private void getRoot() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        root = new TreeNode("A", null, null);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();
            buildTreeNodeHelper(root, parent, left, right);
        }
    }

    private void buildTreeNodeHelper(TreeNode tmp, String parent, String left, String right) {
        if (tmp.val.equals(parent)) {
            tmp.left = left.equals(".") ? null : new TreeNode(left, null, null);
            tmp.right = right.equals(".") ? null : new TreeNode(right, null, null);
            return;
        }

        if (tmp.left != null)
            buildTreeNodeHelper(tmp.left, parent, left, right);
        if (tmp.right != null)
            buildTreeNodeHelper(tmp.right, parent, left, right);

    }

    private void preOrder(TreeNode node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.val);
            preOrder(node.left, sb);
            preOrder(node.right, sb);
        }
    }

    private void inOrder(TreeNode node, StringBuilder sb) {
        if (node != null) {
            inOrder(node.left, sb);
            sb.append(node.val);
            inOrder(node.right, sb);
        }
    }

    private void postOrder(TreeNode node, StringBuilder sb) {
        if (node != null) {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.val);
        }
    }
}

class TreeNode {
    String val;
    TreeNode left;
    TreeNode right;

    public TreeNode(String val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}