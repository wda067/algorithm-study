import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, m, answer;
    static int[] dx = {-1, 0, 1, 0};  // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static ArrayList<int[]> cctvList = new ArrayList<>();

    // CCTV별 회전 가능한 방향 조합 (북동남서 인덱스 사용)
    static int[][][] directs = new int[][][] {
        {{}},                                          // 0번 제외
        {{0}, {1}, {2}, {3}},                          // 1번: 단방향 (4가지 회전)
        {{1, 3}, {0, 2}},                              // 2번: 양방향 (2가지 회전)
        {{0, 1}, {1, 2}, {2, 3}, {3, 0}},              // 3번: 직각 (4가지 회전)
        {{3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}},  // 4번: 3방향 (4가지 회전)
        {{0, 1, 2, 3}}                                 // 5번: 4방향 (1가지)
    };

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] != 0 && board[i][j] != 6) {
                    cctvList.add(new int[] {i, j});  // CCTV 위치 저장
                }
            }
        }

        answer = Integer.MAX_VALUE;
        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int depth) {
        // 모든 CCTV 배치 완료 시 사각지대 계산
        if (depth == cctvList.size()) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 0) count++;
                }
            }
            answer = Math.min(answer, count);
            return;
        }

        int[] current = cctvList.get(depth);
        int cx = current[0];
        int cy = current[1];
        int pos = board[cx][cy];

        // 현재 CCTV의 모든 회전 케이스 시도
        for (int[] cctvNumber : directs[pos]) {
            ArrayList<int[]> path = new ArrayList<>();  // 감시 영역 경로 저장

            // 이 회전 케이스의 모든 방향으로 동시에 감시
            for (int i = 0; i < cctvNumber.length; i++) {
                int dir = cctvNumber[i];
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];

                // 벽 또는 범위 벗어날때까지 직진하며 감시 영역 표시
                while (true) {
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] == 6) break;

                    if (board[nx][ny] == 0) {
                        path.add(new int[] {nx, ny});
                        board[nx][ny] = 7;  // 감시 영역 표시
                    }

                    nx += dx[dir];
                    ny += dy[dir];
                }
            }

            dfs(depth + 1);  // 다음 CCTV 처리

            // 백트래킹: 감시 영역 복구
            for (int j = 0; j < path.size(); j++) {
                int x = path.get(j)[0];
                int y = path.get(j)[1];
                board[x][y] = 0;
            }
        }
    }
}