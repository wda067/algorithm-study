import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 불! / 골드3
https://www.acmicpc.net/problem/4179
 */
public class BOJ_4179 {

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int R, C;
    private static char[][] map;
    private static int[][] jihoon, fire;
    private static boolean[][] visited;
    private static int[] start;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        jihoon = new int[R][C];
        fire = new int[R][C];
        visited = new boolean[R][C];

        // #: 벽
        // .: 지나갈 수 있는 공간
        // J: 초기위치
        // F: 불

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            Arrays.fill(fire[i], Integer.MAX_VALUE);
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'J') {
                    start = new int[]{i, j};
                }
            }
        }

        bfsFire();
        visited = new boolean[R][C];
        bfsJihoon();

        if (result == 0) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }
    }

    private static void bfsFire() {
        Queue<int[]> q = new LinkedList<>();

        // 불 삽입
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'F') {
                    q.add(new int[]{i, j});
                    fire[i][j] = 0;
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= R || nr < 0 || nc >= C || nc < 0) {
                    continue;
                }

                if (map[nr][nc] != '#' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    fire[nr][nc] = fire[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    private static void bfsJihoon() {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            if (r == 0 || r == R - 1 || c == 0 || c == C - 1) {
                result = jihoon[r][c] + 1;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= R || nr < 0 || nc >= C || nc < 0) {
                    continue;
                }

                int nextTime = jihoon[r][c] + 1;
                if (map[nr][nc] != '#' && !visited[nr][nc] && fire[nr][nc] > nextTime) {
                    visited[nr][nc] = true;
                    jihoon[nr][nc] = jihoon[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
}
