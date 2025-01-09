package implementation.도넛과막대그래프;

/*
* 프로그래머스. 카카오. 구현. Level 2. 도넛과 막대 그래프
* https://school.programmers.co.kr/learn/courses/30/lessons/258711
* */

import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        // 각 node 별 [in, out] edges count
        Map<Integer, int[]> nodes = new HashMap<>();

        int abnormalNode = -1;
        int type1 = 0;
        int type2 = 0;
        int type3 = 0;

        // 1. node 별 in, out edge 계산
        for (int[] edge: edges) {
            int from = edge[0], to = edge[1];

            if (!nodes.containsKey(from))
                nodes.put(from, new int[]{0, 0});
            if (!nodes.containsKey(to))
                nodes.put(to, new int[]{0, 0});
            nodes.get(from)[0]++; // increase 'out' counter
            nodes.get(to)[1]++; // increase 'in' counter
        }

        // 2. 특이 node 탐색
        for (int node: nodes.keySet()) {
            int[] counts = nodes.get(node);
            int outs = counts[0], ins = counts[1];

            // abnormal node
            if (outs >= 2 && ins == 0)
                abnormalNode = node;
            else if (outs == 0 && ins >= 0)
                ++type2;
            else if (outs >= 2 && ins >= 2)
                ++type3;
        }

        type1 = nodes.get(abnormalNode)[0] - (type2 + type3);

        return new int[]{ abnormalNode, type1, type2, type3 };
    }
}
