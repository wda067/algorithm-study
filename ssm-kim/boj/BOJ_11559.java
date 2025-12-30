import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int r = 12, c = 6;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static ArrayList<int[]> startPuyo = new ArrayList<>();
    static char[][] board = new char[r][c];
    static int[][] destroys = new int[r][c];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] != '.') {
                    startPuyo.add(new int[] {i, j});
                }
            }
        }

        int cascadeCnt = 0;
        while (true) {
            for (int[] row : destroys) Arrays.fill(row, 0);

            int groupCnt = searchPuyo();  // 1. BFS로 4개 이상 연결된 뿌요 그룹 찾기
            if (groupCnt <= 0) break;

            cascadeCnt++;
            destroyPuyo();  // 2. 찾은 그룹 터뜨리기
            downPuyo();     // 3. 중력 적용
        }
        System.out.println(cascadeCnt);
    }

    static void downPuyo() {
        for (int i = 0; i < c; i++) {
            for (int j = r - 1; j >= 0; j--) {
                // 빈 칸 발견 시, 위에서 뿌요 찾아서 아래로 채우기
                if (board[j][i] == '.') {
                    int upX = j;
                    int moveX = j;

                    while (true) {
                        upX--;
                        if (upX < 0) break;

                        if (board[upX][i] != '.') {
                            board[moveX--][i] = board[upX][i];
                            board[upX][i] = '.';
                        }
                    }
                }
            }
        }
    }

    static void destroyPuyo() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (destroys[i][j] == 1) board[i][j] = '.';
            }
        }
    }

    static int searchPuyo() {
        boolean[][] visited = new boolean[r][c];
        int count = 0;

        for (int[] current : startPuyo) {
            if (bfs(current[0], current[1], visited)) count++;
        }

        return count;
    }

    static boolean bfs(int sx, int sy, boolean[][] visited) {
        ArrayList<int[]> target = new ArrayList<>();
        target.add(new int[] {sx, sy});

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sx, sy});
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c ||  visited[nx][ny]) continue;
                // 같은 색 뿌요만 연결
                if (board[sx][sy] != board[nx][ny] || board[nx][ny] == '.') continue;

                visited[nx][ny] = true;
                target.add(new int[] {nx, ny});
                queue.offer(new int[] {nx, ny});
            }
        }

        // 4개 이상이면 터뜨릴 대상으로 마킹
        if (target.size() >= 4) {
            for (int[] path : target) {
                int ex = path[0];
                int ey = path[1];
                destroys[ex][ey] = 1;
            }
            return true;
        }
        return false;
    }
}