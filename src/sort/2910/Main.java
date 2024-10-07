// 백준 2910. 빈도정렬
// https://www.acmicpc.net/problem/2910
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        int[] rowNumbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int numbOfUniqueNumbers = new HashSet<>(Arrays.stream(rowNumbers).boxed().collect(Collectors.toList())).size();

        Number[] numbers = new Number[N];
        for (int index = 0; index < rowNumbers.length; index++) {
            numbers[index] = new Number(rowNumbers[index], index);
        }

        Arrays.sort(numbers, (num1, num2) -> {
            return num1.num - num2.num;
        });

        Frequency[] frequencies = new Frequency[numbOfUniqueNumbers];
        frequencies[0] = new Frequency(numbers[0].num, 1, numbers[0].index);

        int frequencyIdx = 0;
        for (int index = 1; index < numbers.length; index++) {
            int num1 = numbers[index - 1].num;
            int num2 = numbers[index].num;
            if (num1 != num2)
                frequencies[++frequencyIdx] = new Frequency(numbers[index].num, 0, numbers[index].index);
            frequencies[frequencyIdx].count += 1;
        }

        Arrays.sort(frequencies, (fr1, fr2) -> {
            if (fr1.count == fr2.count)
                return fr1.firstIndex - fr2.firstIndex;
            return fr2.count - fr1.count;
        });

        int[] results = new int[N];
        int resultIdx = 0;
        for (int index = 0; index < frequencies.length; index++) {
            for (int count = 0; count < frequencies[index].count; count++) {
                results[resultIdx++] = frequencies[index].num;
            }
        }

        String result = Arrays.stream(results)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}

class Number {
    int num;
    int index;

    Number(int num, int index) {
        this.num = num;
        this.index = index;
    }
}

class Frequency {
    int num;
    int count;
    int firstIndex;

    Frequency(int num, int count, int firstIndex) {
        this.num = num;
        this.count = count;
        this.firstIndex = firstIndex;
    }
}
