import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백준 / N-Queen / 골드4
https://www.acmicpc.net/problem/9663
 */
public class BOJ_9663 {

    private static int N, count;
    private static final boolean[] colCheck = new boolean[14];
    private static final boolean[] plusDiagonalCheck = new boolean[27];
    private static final boolean[] minusDiagonalCheck = new boolean[27];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        recur(0);
        System.out.println(count);
    }

    static void recur(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (canPlaceQueen(row, col)) {
                updateQueen(row, col, true);
                recur(row + 1);
                updateQueen(row, col, false);
            }
        }
    }

    //(row, col)에 퀸 배치 가능 여부 반환
    private static boolean canPlaceQueen(int row, int col) {
        return !colCheck[col] &&
                !plusDiagonalCheck[row + col] &&
                !minusDiagonalCheck[row - col + N - 1];
    }

    //(row, col)에 퀸 배치 또는 제거
    private static void updateQueen(int row, int col, boolean state) {
        colCheck[col] = state;
        plusDiagonalCheck[row + col] = state;
        minusDiagonalCheck[row - col + N - 1] = state;
    }
}
