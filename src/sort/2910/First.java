// 백준 2910. 빈도정렬
// https://www.acmicpc.net/problem/2910
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// 첫 번째 시도. runtime 측면에서는 문제가 없으나 c의 범위로 메모리 초과 판정 -> 개선 필요
// Integer 등과 같은 wrapper class의 비교를 ==으로 해서 오답처리 계속 받음
public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Scanner sc = new Scanner(System.in);
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);

        String line = br.readLine();
        int[] numbers = Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] frequency = new int[c + 1]; // -> c의 범위가 10억이기에 메모리 초과 판정 받음

        // input number, frequency
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            frequency[number] += 1;
        }

        for (int number = 0; number < frequency.length; number++) {
            int value = frequency[number];
            if (value != 0)
                frequencyMap.put(number, value);
        }

        List<Integer> numberKeys = new ArrayList<>(frequencyMap.keySet());

        numberKeys.sort((num1, num2) -> {
            int val1 = frequencyMap.get(num1);
            int val2 = frequencyMap.get(num2);

            // 빈도 수가 같으면 먼저 제시된 순으로 정렬
            // Integer type의 자료 비교를 == 으로하니 백준 컴파일러에서는 주소값을 비교함... -> int 타입으로 변경
            if (val1 == val2) {
                int indexOfVal1 = getFirstIndex(numbers, num1);
                int indexOfVal2 = getFirstIndex(numbers, num2);
                return indexOfVal1 - indexOfVal2;
            }
            // 빈도 수가 다르면 내림차순 정렬
            return val2 - val1;
        });

        List<String> result = new ArrayList<>(n);

        numberKeys.forEach(number -> {
            int value = frequencyMap.get(number);
            for (int i = 0; i < value; i++) {
                result.add(number.toString());
            }
        });

        System.out.println(result.stream().collect(Collectors.joining(" ")));
    }

    static int getFirstIndex(int[] numbers, int number) {
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == number) {
                index = i;
                break;
            }
        }
        return index;
    }
}