// 마법의 엘리베이터
// https://school.programmers.co.kr/learn/courses/30/lessons/148653
// level 2
package implementation.programmers.MagicalElevator;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0) {            
            int num = storey % 10;            
            storey /= 10;                        
            if (num > 5) {                
                answer += 10 - num;                
                storey++;                            
            } else if (num < 5) {                
                answer += num;            
            } else if (storey % 10 >= 5) {               
                answer += 5;                
                storey++;                            
            } else {                
                answer += 5;            
            }        
        } 

        return answer;
    }
}