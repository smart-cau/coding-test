package graph.dfs.단어변환;

class Solution {

    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];

        dfs(begin, target, words, 0);

        if (answer == Integer.MAX_VALUE)
            answer = 0;

        return answer;
    }

    void dfs(String begin, String target, String[] words, int depth) {
        if (begin.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }
        int nextDepth = depth + 1; // 이 위치 선정 때문에 한참 해맴 ㅜㅠ
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && movable(begin, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, nextDepth);
                visited[i] = false;
            }
        }
    }

    boolean movable(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                ++diff;
        }
        return diff == 1;
    }
}
