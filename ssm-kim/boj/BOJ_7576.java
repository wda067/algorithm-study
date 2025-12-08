import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x, y, day;

    public Node (int x, int y, int day) {
        this.x = x;
        this.y = y;
        this.day = day;
    }
}

public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] board;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[m][n];
        ArrayList<int[]> path = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                // 익은 토마토 위치 저장
                if (board[i][j] == 1) {
                    path.add(new int[] {i, j});
                }
            }
        }

        // 1. 토마토 퍼뜨리기
        int day = bfs(path);

        // 2. 모든 토마토가 익었는지 확인
        System.out.println(checkBlocks() ? day : -1);
    }

    static boolean checkBlocks() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static int bfs(ArrayList<int[]> startPath) {
        Queue<Node> queue = new LinkedList<>();

        // 출발 지점: 좌표 넣기 + 방문 체크
        for (int[] path : startPath) {
            int sx = path[0];
            int sy = path[1];
            queue.offer(new Node(sx, sy, 0));
        }

        int count = 0;
        while (!queue.isEmpty()) {
            Node nd = queue.poll();

            count = Math.max(count, nd.day);
            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                // 범위 + 익은 토마토 + 벽 체크
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || board[nx][ny] != 0) continue;

                // 익지 않은 토마토일 때만 확산
                board[nx][ny] = 1;
                queue.offer(new Node(nx, ny, nd.day + 1));
            }
        }
        return count;
    }
}