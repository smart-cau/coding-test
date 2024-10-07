import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

class Number {
    int index;
    int num;
    int frq;

    Number(int index, int num) {
        this.index = index;
        this.num = num;
        this.frq = 0;
    }
}

public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cards = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int M = Integer.parseInt(br.readLine());

        String[] inputs = br.readLine().split(" ");
        Number[] numbers = new Number[M];
        for (int i = 0; i < inputs.length; i++) {
            numbers[i] = new Number(i, Integer.parseInt(inputs[i]));
        }
        Arrays.sort(numbers, (number1, number2) -> number1.num - number2.num);

        for (int i = 0; i < N; i++) {
            findSameCard(numbers, cards[i]);
        }

        int[] answers = new int[M];
        for (int i = 0; i < numbers.length; i++) {
            Number number = numbers[i];
            answers[number.index] = number.frq;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String result = Arrays.stream(answers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        bw.write(result);
        bw.newLine();
        bw.flush();
    }

    private static void findSameCard(Number[] numbers, int card) {
        int left = 0;
        int right = numbers.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            Number number = numbers[middle];
            if (number.num < card)
                left = middle + 1;
            if (number.num > card)
                right = middle - 1;
            if (number.num == card) {
                number.frq += 1;
                return;
            }
        }
    }
}
