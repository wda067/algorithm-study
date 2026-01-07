import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int n = 5, answer = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] seq;
    static char[][] board = new char[n][n];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        seq = new int[7];
        combinations(0, 0);

        System.out.println(answer);
    }

    static void combinations(int depth, int start) {
        if (depth == 7) {
            boolean[][] check = new boolean[n][n];
            int sCnt = 0;
            int sx = -1, sy = -1;

            // 선택된 7개 좌표를 check 배열에 표시하고 S 개수 카운트
            for (int idx : seq) {
                int x = idx / 5;
                int y = idx % 5;

                if (sx == -1) {  // 첫 좌표 저장 (BFS 시작점)
                    sx = x;
                    sy = y;
                }
                if (board[x][y] == 'S') sCnt++;
                check[x][y] = true;
            }

            // S가 4개 이상이고 7개가 모두 인접하면 카운트
            if (sCnt >= 4 && isConnect(sx, sy, check)) {
                answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            seq[depth] = i;
            combinations(depth + 1, i + 1);
        }
    }

    static boolean isConnect(int sx, int sy, boolean[][] check) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy});

        boolean[][] visited = new boolean[n][n];
        visited[sx][sy] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

                // 선택된 좌표(check==true)만 방문
                if (check[nx][ny]) {
                    count++;
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }

        return count == 7;  // 7개 모두 방문했으면 연결된 것
    }
}