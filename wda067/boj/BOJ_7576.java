import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 토마토 / 골드5
https://www.acmicpc.net/problem/7576
 */
public class BOJ_7576 {

    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static int[][] tomatoes;
    private static int M;
    private static int N;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());  //가로
        N = Integer.parseInt(st.nextToken());  //세로

        tomatoes = new int[N][M];

        boolean flag = true;  //처음부터 모든 토마토가 익어 있는지 여부
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if (tomatoes[i][j] == 0) {
                    flag = false;
                }
            }
        }

        //이미 모두 익어있을 때
        if (flag) {
            System.out.println(0);
            return;
        }

        bfs();

        //읽지 않은 토마토가 존재할 때
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomatoes[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        //모두 읽을 때까지의 최소 날짜
        System.out.println(max - 1);  //초기 상태가 1이라 경과일은 -1 필요
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();

        //익은 토마토 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomatoes[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                if (tomatoes[nr][nc] == 0) {
                    q.add(new int[]{nr, nc});
                    tomatoes[nr][nc] = tomatoes[cr][cc] + 1;
                    if (max < tomatoes[nr][nc]) {
                        max = tomatoes[nr][nc];
                    }
                }
            }
        }
    }
}
