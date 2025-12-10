import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 감시 / 골드3
https://www.acmicpc.net/problem/15683
 */
public class BOJ_15683 {

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int N;
    private static int M;
    private static int min = Integer.MAX_VALUE;
    private static int[][] office;
    private static final List<Integer> cctvs = new ArrayList<>();
    private static final List<int[]> pos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        // 0: 빈칸 / 1~5: CCTV / 6: 벽
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                office[i][j] = num;

                if (num <= 5 && num >= 1) {  // CCTV일 경우
                    cctvs.add(num);
                    pos.add(new int[]{i, j});
                } else if (num == 6) {  // 벽일 경우
                    visited[i][j] = true;
                }
            }
        }

        dfs(0, visited);

        System.out.println(min);
    }

    private static void dfs(int index, boolean[][] visited) {
        if (cctvs.size() == index) {
            int total = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j]) {
                        total++;
                    }
                }
            }

            min = Math.min(min, total);
            return;
        }

        int cctv = cctvs.get(index);
        for (int d = 0; d < 4; d++) {
            boolean[][] clone = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                clone[i] = visited[i].clone();
            }

            check(cctv, d, clone, index);
            dfs(index + 1, clone);
        }
    }

    // 해당 방향의 해당 CCTV로 방문 진행
    private static void check(int cctv, int dir, boolean[][] visited, int index) {
        int[] cur = pos.get(index);
        int r = cur[0];
        int c = cur[1];
        visited[r][c] = true;

        if (cctv == 1) {
            processChecking(dir, visited, r, c);
        } else if (cctv == 2) {
            processChecking(dir, visited, r, c);
            processChecking((dir + 2) % 4, visited, r, c);
        } else if (cctv == 3) {
            processChecking(dir, visited, r, c);
            processChecking((dir + 1) % 4, visited, r, c);
        } else if (cctv == 4) {
            processChecking(dir, visited, r, c);
            processChecking((dir + 1) % 4, visited, r, c);
            processChecking((dir + 2) % 4, visited, r, c);
        } else if (cctv == 5) {
            processChecking(dir, visited, r, c);
            processChecking((dir + 1) % 4, visited, r, c);
            processChecking((dir + 3) % 4, visited, r, c);
            processChecking((dir + 2) % 4, visited, r, c);
        }
    }

    // 해당 방향으로 계속 방문 체크
    private static void processChecking(int dir, boolean[][] visited, int nr, int nc) {
        while (true) {
            nr += dr[dir];
            nc += dc[dir];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                break;
            }

            if (office[nr][nc] == 6) {  // 벽일 경우
                break;
            }

            visited[nr][nc] = true;
        }
    }
}
