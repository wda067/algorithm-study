import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 주사위 굴리기 / 골드4
https://www.acmicpc.net/problem/14499
 */
public class BOJ_14499 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 동, 서, 북, 남
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        /*
        0: 윗면
        1: 아랫면
        2: 동쪽
        3: 서쪽
        4: 북쪽
        5: 남쪽
         */
        int[] dice = new int[6];
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /*
        0: 동
        1: 서
        2: 북
        3: 남
         */
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(st.nextToken());
            int dir = command - 1;
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                continue;
            }

            x = nextX;
            y = nextY;

            int[] temp = dice.clone();
            if (dir == 0) {  // 동쪽
                dice[0] = temp[3];  // 윗면 <- 서쪽
                dice[1] = temp[2];  // 아랫면 <- 동쪽
                dice[2] = temp[0];  // 동쪽 <- 윗면
                dice[3] = temp[1];  // 서쪽 <- 아랫면
            } else if (dir == 1) {  // 서쪽
                dice[0] = temp[2];  // 윗면 <- 동쪽
                dice[1] = temp[3];  // 아랫면 <- 서쪽
                dice[2] = temp[1];  // 동쪽 <- 아랫면
                dice[3] = temp[0];  // 서쪽 <- 윗면
            } else if (dir == 2) {  // 북쪽
                dice[0] = temp[5];  // 윗면 <- 남쪽
                dice[1] = temp[4];  // 아랫면 <- 북쪽
                dice[4] = temp[0];  // 북쪽 <- 윗면
                dice[5] = temp[1];  // 남쪽 <- 아랫면
            } else {                // 남쪽
                dice[0] = temp[4];  // 윗면 <- 북쪽
                dice[1] = temp[5];  // 아랫면 <- 남쪽
                dice[4] = temp[1];  // 북쪽 <- 아랫면
                dice[5] = temp[0];  // 남쪽 <- 윗면
            }

            if (map[x][y] == 0) {
                // 지도가 0이면 주사위 바닥면 복사
                map[x][y] = dice[1];
            } else {
                // 지도가 0이 아니면 지도 값을 주사위 바닥면에 복사 후 지도는 0으로 초기화
                dice[1] = map[x][y];
                map[x][y] = 0;
            }

            // 주사위 윗면 출력
            sb.append(dice[0]).append("\n");
        }

        System.out.println(sb);
    }
}
