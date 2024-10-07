// https://www.acmicpc.net/problem/2470
// two pointers가 아닌 binary search로도 풀 수 있음

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] traits = new int[N];
        traits = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(traits);

        int l = 0;
        int r = traits.length - 1;
        int min = (1 << 31) - 1;
        int lIndex = -1;
        int rIndex = -1;

        while (l < r) {
            int left = traits[l];
            int right = traits[r];
            int sum = Math.abs(left + right);
            if (sum <= min) {
                min = sum;
                lIndex = l;
                rIndex = r;
            }
            int moveRight = Math.abs(left + traits[r - 1]);
            int moveLeft = Math.abs(traits[l + 1] + right);
            if (moveLeft <= moveRight)
                l++;
            if (moveLeft > moveRight)
                r--;
        }
        System.out.println(traits[lIndex] + " " + traits[rIndex]);
        br.close();
    }
}