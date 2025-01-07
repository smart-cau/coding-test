package dataStructure.stack.StoneWall;

/*
* Codility > Stack > Easy > StoneWall
* https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
* */

import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public int answer(int[] H) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int height: H) {
            while (!stack.isEmpty() && stack.peek() > height ) {
                stack.pop();
            }

            if (stack.isEmpty() || stack.peek() < height) {
                stack.push(height);
                ++ans;
            }
        }

        return ans;
    }

    public int myWrongAnswer(int[] H) {
        // Implement your solution here
        if (H.length == 0) return 0;

        int ans = 1;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(H[0]);

        for (int i = 1; i < H.length; i++) {
            int peek = stack.peek(); // 틀린이유: 이렇게 변수로 선언해줘서 오히려 복잡하다. 갱신이 필요할 땐 그 때마다 접근하도록 하는게 낫다
            int height = H[i];

            // 틀린이유: 갯수 증가 조건이 잘못되었다
            if (peek < height)
                stack.push(height);
            else if (peek > height) {
                while (!stack.isEmpty() && peek > height) {
                    stack.pop();
                    ++ans;
                    if (!stack.isEmpty())
                        peek = stack.peek();
                }
                stack.push(height);
            }
        }

        return ans;
    }
}
