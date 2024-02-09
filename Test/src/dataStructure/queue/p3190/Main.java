// 백준 3190번. 뱀
// https://www.acmicpc.net/problem/3190
// gold 4
package dataStructure.queue.p3190;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static int appleCount;
    static Position[] appleAt;
    static int turnCount;
    static Cell[][] cells;
    static Queue<Turn> turnAt = new LinkedList<>();
    static Deque<Position> snake = new ArrayDeque<>();
    static int time = 0;
    static FastReader sc = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        N = sc.nextInt();
        board = new int[N][N];

        appleCount = sc.nextInt();
        appleAt = new Position[appleCount];
        for (int i = 0; i < appleCount; i++) {
            String[] positionStr = sc.nextLine().split(" ");
            Position position = new Position(Integer.parseInt(positionStr[0]) - 1,
                    Integer.parseInt(positionStr[1]) - 1);
            appleAt[i] = position;
        }

        turnCount = sc.nextInt();
        for (int i = 0; i < turnCount; i++) {
            String[] turnStr = sc.nextLine().split(" ");
            Direction direction = null;
            if (turnStr[1].equals("L"))
                direction = Direction.LEFT;
            if (turnStr[1].equals("D"))
                direction = Direction.RIGHT;
            Turn turn = new Turn(Integer.parseInt(turnStr[0]), direction);
            turnAt.add(turn);
        }
    }

    static void solution() {
        cells = new Cell[N][N];
        snake.add(new Position(0, 0));
        int time = 1;
        // init board
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                cells[row][column] = new Cell(row, column);
            }
        }

        for (Position applePosition : appleAt) {
            cells[applePosition.row][applePosition.column].isApple = true;
        }

        Moves moves = new Moves();
        Move move = Move.RIGHT;

        // game start
        while (true) {
            ++time;
            Position current = snake.peek();
            move = getMoveByDirection(turnAt, moves, move, time);
            Position next = getPositionByMove(current, move);
            // end 조건 검증
            if (isEnd(next))
                break;
            // 이동
            snake.addFirst(next);
            // 사과 있을 시 & 없을 시
            Cell nextCell = cells[next.row][next.column];

            if (!nextCell.isApple) {
                snake.removeLast();
            }

            if (nextCell.isApple) {
                nextCell.isApple = false;
            }

        }

        System.out.println(time);
    }

    static Move getMoveByDirection(Queue<Turn> turnAt, Moves moves, Move move, int time) {
        Direction direction = null;

        if (turnAt.peek().afterStart == time) {
            Turn turn = turnAt.poll();
            direction = turn.direction;
        }
        if (direction == Direction.RIGHT)
            move = moves.turnRight();
        if (direction == Direction.LEFT)
            move = moves.turnLeft();

        return move;
    }

    static Position getPositionByMove(Position current, Move move) {
        int nextRow = current.row;
        int nextColumn = current.column;
        // go right
        if (move == Move.RIGHT) {
            ++nextColumn;
        }
        // go down
        if (move == Move.DOWN) {
            ++nextRow;
        }
        // go left
        if (move == Move.LEFT) {
            --nextColumn;
        }
        // go up
        if (move == Move.UP) {
            --nextRow;
        }

        return new Position(nextRow, nextColumn);
    }

    static boolean isEnd(Position next) {
        // 자기 몸에 부딪히는 경우
        if (snake.contains(next))
            return true;
        // 경기장 밖을 나가는 경우

        if (next.row > N - 1 || next.column > N - 1)
            return true;
        if (next.row < 0 || next.column < 0)
            return true;
        return false;
    }
}

class Cell extends Position {
    boolean isApple;

    public Cell(int row, int column) {
        super(row, column);
        this.isApple = false;
    }
}

class Moves {
    private int MAX_MOVES = Move.values().length;
    private Move[] moves = Move.values();

    private int index = 0;

    public Move turnRight() {
        if (index + 1 == MAX_MOVES)
            index = 0;
        else
            ++index;

        return moves[index];
    }

    public Move turnLeft() {
        if (index - 1 < 0)
            index = MAX_MOVES - 1;
        else
            --index;

        return moves[index];
    }
}

enum Move {
    RIGHT,
    DOWN,
    LEFT,
    UP;
}

class Turn {
    int afterStart;
    Direction direction;

    public Turn(int afterStart, Direction direction) {
        this.afterStart = afterStart;
        this.direction = direction;
    }
}

enum Direction {
    LEFT,
    RIGHT;
}

class Position {
    int row;
    int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public FastReader(String s) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(new File(s)));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
