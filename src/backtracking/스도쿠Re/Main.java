package backtracking.스도쿠Re;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 코드가 너무 길고 복잡해진다 싶으면 뭔가 잘못된거다...
* 최적화도 좋지만 우선 통과가 먼저다. 최적화하려다 보면 문제풀이가 산으로 가네
* 그리고 역시 일단 코드 박아!!는 알고리즘에서는 손해가 막심하다... 이 산이 아닌가벼가 너무 자주 나온다
* 패턴을 익히지
* */
public class Main {
    static int SIZE = 9;
    static int[][] sudoku = new int[SIZE][SIZE];
    static int[][] copy = new int[SIZE][SIZE];
    static List<Position> blanks = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                int cell = Integer.parseInt(st.nextToken());
                sudoku[i][j] = cell;
                copy[i][j] = cell;
                if (cell == 0)
                    blanks.add(new Position(i, j));
            }
        }

        solution();
    }

    static void solution() {
        // 수정이 필요한 위치들을 보관할 리스트
//        List<Position> positionsToRemove = new ArrayList<>();
//
//        for (Position position : blanks) {
//            List<Integer> rowPossibles = search(position, "row");
//            List<Integer> colPossibles = search(position, "col");
//            List<Integer> sectionPossibles = search(position);
//            if (rowPossibles.size() == 1) {
//                sudoku[position.row][position.col] = rowPossibles.get(0);
//                positionsToRemove.add(position); // 삭제 예정
//            } else if (colPossibles.size() == 1) {
//                sudoku[position.row][position.col] = colPossibles.get(0);
//                positionsToRemove.add(position); // 삭제 예정
//            } else if (sectionPossibles.size() == 1) {
//                sudoku[position.row][position.col] = sectionPossibles.get(0);
//                positionsToRemove.add(position); // 삭제 예정
//            }
//        }
//
//        // blanks 리스트에서 수정된 위치를 전체 순회 후 제거
//        blanks.removeAll(positionsToRemove);
//        print();
        visited = new boolean[blanks.size()];
        backtrack(blanks.size(), 0);
    }

    static void backtrack(int depth, int idx) {
        if (depth == 0) {
            print();
            return;
        }

        for (int i = idx; i < blanks.size(); i++) {
            Position position = blanks.get(i);
            Set<Integer> possibleNums = getPossibleNums(position);
            if (possibleNums.size() == 1) {
                sudoku[position.row][position.col] = possibleNums.iterator().next();
                backtrack(depth - 1, i + 1);
            } else if (possibleNums.isEmpty()) return;
            else {
                for (int num : possibleNums) {
                    sudoku[position.row][position.col] = num;
                    backtrack(depth - 1, i + 1);
                    sudoku[position.row][position.col] = 0;
                }
            }
        }
    }

    static Set<Integer> getPossibleNums(Position position) {
        Set<Integer> possiblities = new HashSet<>(search(position, "row"));
        possiblities.retainAll(search(position, "col"));
        possiblities.retainAll(search(position));

        return possiblities;
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static List<Integer> search(Position position, String direction) {
        List<Integer> possibleNums = new ArrayList<>();
        boolean[] nums = new boolean[SIZE];

        for (int i = 0; i < SIZE; i++) {
            int value = direction.equals("row") ?  sudoku[i][position.col] : sudoku[position.row][i];
            if (value != 0) {
                nums[value - 1] = true;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (!nums[i]) {
                possibleNums.add(i + 1);
            }
        }

        return possibleNums;
    }

    static List<Integer> search(Position position) {
        List<Integer> possibleNums = new ArrayList<>();
        boolean[] nums = new boolean[SIZE];
        int sectionRow = position.row - position.row % 3;
        int sectionCol = position.col - position.col % 3;
        for (int i = sectionRow; i < sectionRow + 3; i++) {
            for (int j = sectionCol; j < sectionCol + 3; j++) {
                int value = sudoku[i][j];
                if (value != 0) {
                    nums[value - 1] = true;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (!nums[i]) {
                possibleNums.add(i + 1);
            }
        }
        return possibleNums;
    }

    static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
