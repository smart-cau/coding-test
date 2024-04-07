// 혼자 놀기의 달인
// https://school.programmers.co.kr/learn/courses/30/lessons/131130
// level 2
package recursion.programmers.playAlone;

import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        /*
            1. cards의 모든 원소를 탐색할 때까지, discovered[i] = false인 첫 번째 상자를 고른다
            2. 해당 상자의 숫자부터 시작해서 다음 상자가 discovered[j] = true가 나올 때까지
                상자의 갯수를 count를 한다
                true가 나오면 
                    count를 배열에 저장한다
                    1번 과정으로 돌아간다
            3. 탐색이 끝나면 count가 들어가 있는 리스트를 내림차순 정렬 해 앞의 2개 원소끼리 곱한다
        */
        int cardsCount = cards.length;
        boolean[] discovered = new boolean[cardsCount];
        List<Integer> groupsCount = new ArrayList<>();

        for (int i = 0; i < cardsCount; i++) {
            if (!discovered[i])
                setGroup(i, cards, discovered, groupsCount, 0);
        }
        if (groupsCount.size() == 1) 
            answer = 0;
        else {
            groupsCount.sort((a, b) -> b.compareTo(a));
            answer = groupsCount.get(0) * groupsCount.get(1);
        }
        return answer;
    }
    
    public void setGroup(int i, int[] cards, boolean[] discovered, List<Integer> groupsCount, int count) {
        if (discovered[i]) {
            groupsCount.add(count);
            return;
        }
        discovered[i] = true;
        int number = cards[i] - 1;
        setGroup(number, cards, discovered, groupsCount, count + 1);
    }
}