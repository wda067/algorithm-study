import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 벽 부수고 이동하기 / 골드3
https://www.acmicpc.net/problem/2206
 */
public class BOJ_2206 {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int N, M;
    private static int[][] map;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = arr[j] - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0)); // (0,0) 출발, 거리 1, 벽 부숨 여부 0
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.r == N - 1 && cur.c == M - 1) {
                return cur.dst;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + dr[dir];
                int nc = cur.c + dc[dir];
                int nd = cur.dst + 1;
                int nb = cur.broken; // 0 or 1

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                // 다음 칸이 빈 칸(0)
                if (map[nr][nc] == 0 && !visited[nr][nc][nb]) {
                    visited[nr][nc][nb] = true;
                    q.add(new Node(nr, nc, nd, nb));
                }

                // 다음 칸이 벽(1)인데, 아직 벽을 안 부쉈다면 1번 부수고 이동 가능
                if (map[nr][nc] == 1 && nb == 0 && !visited[nr][nc][1]) {
                    visited[nr][nc][1] = true;
                    q.add(new Node(nr, nc, nd, 1));
                }
            }
        }

        return -1;
    }

    private static class Node {
        int r, c, dst, broken;

        Node(int r, int c, int dst, int broken) {
            this.r = r;
            this.c = c;
            this.dst = dst;
            this.broken = broken;
        }
    }
}
