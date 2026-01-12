import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 빙산 / 골드4
https://www.acmicpc.net/problem/2573
 */
public class BOJ_2573 {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int years = 0;
        while (true) {
            int components = countComponents();  // 빙산의 덩어리 수

            if (components >= 2) {
                System.out.println(years);
                return;
            } else if (components == 0) {
                System.out.println(0);
                return;
            }

            meltOnce();
            years++;
        }
    }

    private static int countComponents() {
        boolean[][] visited = new boolean[N][M];
        int components = 0;

        Queue<int[]> q = new LinkedList<>();

        // 빙산 탐색
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                // 빙산이 아니거나 방문한 빙산은 패스
                if (map[r][c] <= 0 || visited[r][c]) {
                    continue;
                }

                components++;
                if (components >= 2) {
                    return components;
                }

                visited[r][c] = true;
                q.add(new int[]{r, c});

                // 선택한 빙산에서 BFS
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int cr = cur[0], cc = cur[1];

                    for (int d = 0; d < 4; d++) {
                        int nr = cr + dr[d];
                        int nc = cc + dc[d];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                            continue;
                        }

                        if (map[nr][nc] > 0 && !visited[nr][nc]) {
                            visited[nr][nc] = true;
                            q.add(new int[]{nr, nc});
                        }

                    }
                }
            }
        }

        return components;
    }

    private static void meltOnce() {
        int[][] melt = new int[N][M];  // 인접한 바다 면의 수

        // 빙산 탐색
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] <= 0) {
                    continue;
                }

                int sea = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }

                    if (map[nr][nc] == 0) {  // 바다 카운트
                        sea++;
                    }
                }

                melt[r][c] = sea;
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] > 0) {
                    map[r][c] = Math.max(0, map[r][c] - melt[r][c]);
                }
            }
        }
    }
}
