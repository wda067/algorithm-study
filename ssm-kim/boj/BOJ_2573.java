import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static ArrayList<int[]> coordinate = new ArrayList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
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
                if (board[i][j] != 0) {
                    coordinate.add(new int[] {i, j});
                }
            }
        }

        int yearCnt = 0;
        while (true) {
            // BFS로 빙산 덩어리 개수 체크
            int iceCnt = iceCheck();

            if (iceCnt >= 2) {  // 두 덩어리 이상 분리되면 종료
                System.out.println(yearCnt);
                break;
            }
            else if (iceCnt == 0) {  // 빙산이 모두 녹으면 0 출력
                System.out.println(0);
                break;
            }

            yearCnt++;
            meltingIce();  // 빙산 녹이기
        }
    }

    static void meltingIce() {
        ArrayList<int[]> target = new ArrayList<>();

        // 각 빙산 위치에서 인접한 바다(0)의 개수 계산
        for (int[] path : coordinate) {
            int cx = path[0];
            int cy = path[1];

            int meltCnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (board[nx][ny] == 0) meltCnt++;
            }
            target.add(new int[] {cx, cy, meltCnt});
        }

        // 동시에 녹이고 좌표 갱신
        coordinate.clear();
        for (int[] info : target) {
            int x = info[0];
            int y = info[1];
            int cnt = info[2];
            int iceSize = board[x][y] - cnt;

            board[x][y] = Math.max(iceSize, 0);
            if (iceSize > 0) coordinate.add(new int[] {x, y});
        }
    }

    static int iceCheck() {
        visited = new boolean[n][m];
        int iceCnt = 0;

        // BFS로 연결된 빙산 덩어리 카운트
        for (int[] path : coordinate) {
            int sx = path[0];
            int sy = path[1];

            if (!visited[sx][sy]) {
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] {sx, sy});
                visited[sx][sy] = true;
                iceCnt++;

                while (!queue.isEmpty()) {
                    int[] pos = queue.poll();
                    int cx = pos[0];
                    int cy = pos[1];

                    for (int i = 0; i < 4; i++) {
                        int nx = cx + dx[i];
                        int ny = cy + dy[i];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                        if (board[nx][ny] == 0 || visited[nx][ny]) continue;

                        visited[nx][ny] = true;
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
        }
        return iceCnt;
    }
}