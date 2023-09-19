import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N];
        String[] cardValues = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(cardValues[i]);
        }

        Arrays.sort(cards);

        int M = Integer.parseInt(br.readLine());

        String[] queryValues = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            int number = Integer.parseInt(queryValues[i]);
            int lowerBound = findLowerBound(cards, number);
            int upperBound = findUpperBound(cards, number);
            bw.write((upperBound - lowerBound) + " ");
        }
        bw.write("\n");
        bw.flush();

        br.close();
        bw.close();
    }

    private static int findLowerBound(int[] cards, int number) {
        int lowerBound = 0;
        int left = 0;
        int right = cards.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (cards[middle] < number)
                left = middle + 1;
            else
                right = middle;
        }
        lowerBound = left;
        return lowerBound;
    }

    private static int findUpperBound(int[] cards, int number) {
        int upperBound = 0;
        int left = 0;
        int right = cards.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (cards[middle] <= number)
                left = middle + 1;
            else
                right = middle;
        }
        upperBound = left;
        return upperBound;
    }
}
