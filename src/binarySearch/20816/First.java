import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

// 백준 10816. 이진탐색
// https://www.acmicpc.net/problem/10816
class Card {
    Integer num;
    boolean found;

    Card(Integer num) {
        this.num = num;
        found = false;
    }

    boolean notFound() {
        return found == false;
    }

    boolean isSmaller(Integer compare) {
        return num.compareTo(compare) < 0 && notFound();
    }

    boolean isLarger(Integer compare) {
        return num.compareTo(compare) > 0 && notFound();
    }

    boolean isSame(Integer compare) {
        return num.compareTo(compare) == 0 && notFound();
    }
}

public class First {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer N = Integer.parseInt(br.readLine());

        Number[] cards2 = Arrays.stream(br.readLine().split(" "))
                .map(s -> new Card(Integer.parseInt(s)))
                .toArray(Number[]::new);
        Arrays.sort(cards2, (card1, card2) -> card1.num - card2.num);

        // Integer[] cards = Arrays.stream(br.readLine().split(" "))
        // .map(Integer::parseInt)
        // .toArray(Integer[]::new);
        // sort to use binary search
        // Arrays.sort(cards);

        Integer M = Integer.parseInt(br.readLine());
        Integer[] compares = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        int[] answers = new int[M];

        for (int i = 0; i < M; i++) {
            Integer compare = compares[i];
            //answers[i] = isExist(cards2, compare);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String result = Arrays.stream(answers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        bw.write(result);
        bw.flush();
    }

    static boolean isExist(Integer[] cards, Integer compare) {
        int left = 0;
        int right = cards.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            Integer medium = cards[middle];
            if (medium.compareTo(compare) < 0)
                left = middle + 1;
            if (medium.compareTo(compare) > 0)
                right = middle - 1;
            if (medium.compareTo(compare) == 0)
                return true;
        }
        return false;
    }

    static int isExist(Card[] cards, Integer compare) {
        int left = 0;
        int right = cards.length - 1;
        int result = 0;

        while (left <= right) {
            int middle = (left + right) / 2;
            Card medium = cards[middle];
            if (medium.isSmaller(compare))
                left = middle + 1;
            if (medium.isLarger(compare))
                right = middle - 1;
            if (medium.isSame(compare)) {
                medium.found = true;
                ++result;
            }
        }
        return result;
    }
}