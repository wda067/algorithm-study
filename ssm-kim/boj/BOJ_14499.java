import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, cx, cy;
    static int[] dice = new int[6];
    static int[][] board;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        cx = Integer.parseInt(st.nextToken());
        cy = Integer.parseInt(st.nextToken());
        int commands = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < commands; i++) {
            int command = Integer.parseInt(st.nextToken());

            // 범위 체크 후 이동
            boolean state = move(command);
            if (!state) continue;  // 범위를 벗어나면

            // 주사위 면 회전, 바닥과 주사위 값 교환
            diceRoll(command);

            System.out.println(dice[3]);
        }
    }

    static void diceRoll(int command) {
        int[] origin = Arrays.copyOfRange(dice, 0, 6);  // 회전 전 상태 저장

        // 각 방향별로 변하는 면만 갱신
        if (command == 1) {  // 동쪽 이동
            dice[1] = origin[5];
            dice[3] = origin[4];
            dice[4] = origin[1];
            dice[5] = origin[3];
        }
        else if (command == 2) {  // 서쪽 이동
            dice[3] = origin[5];
            dice[4] = origin[3];
            dice[1] = origin[4];
            dice[5] = origin[1];
        }
        else if (command == 3) {  // 북쪽 이동
            dice[0] = origin[1];
            dice[1] = origin[2];
            dice[2] = origin[3];
            dice[3] = origin[0];
        }
        else if (command == 4) {  // 남쪽 이동
            dice[0] = origin[3];
            dice[1] = origin[0];
            dice[2] = origin[1];
            dice[3] = origin[2];
        }

        // 바닥과 주사위 아랫면 값 교환 규칙
        if (board[cx][cy] == 0) {
            board[cx][cy] = dice[1];
        }
        else {
            dice[1] = board[cx][cy];
            board[cx][cy] = 0;
        }
    }

    static boolean move(int command) {
        int nx = cx;
        int ny = cy;

        switch (command) {
            case 1: ny++; break;  // 동
            case 2: ny--; break;  // 서
            case 3: nx--; break;  // 북
            case 4: nx++; break;  // 남
        }

        // 범위 체크 후 좌표 갱신
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) return false;

        cx = nx;
        cy = ny;
        return true;
    }
}