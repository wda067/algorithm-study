import java.io.*;

public class Main {

    static int n, answer = 0;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int row) {
        if (row == n) {
            answer++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (check(row, col)) {
                board[row][col] = 1;
                dfs(row + 1);
                board[row][col] = 0;
            }
        }
    }

    static boolean check(int row, int col) {
        // 세로 체크
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }

        // 왼쪽 위 대각선 체크
        for (int i = 1; i <= row; i++) {
            int nx = row - i;
            int ny = col - i;

            if (ny < 0) break;
            if (board[nx][ny] == 1) return false;
        }

        // 오른쪽 위 대각선 체크
        for (int i = 1; i <= row; i++) {
            int nx = row - i;
            int ny = col + i;

            if (ny >= n) break;
            if (board[nx][ny] == 1) return false;
        }

        return true;
    }
}