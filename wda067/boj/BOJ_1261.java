import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 알고스팟 / 골드4
https://www.acmicpc.net/problem/1261
 */
public class BOJ_1261 {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    private static int N, M, answer;
    private static int[][] map;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist  = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dijkstra();
        System.out.println(answer);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Node(0, 0, 0));
        dist[0][0] = 0;  // 시작 지점의 거리는 0

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int r = cur.r;
            int c = cur.c;
            int cost = cur.cost;

            // if (cost != dist[r][c]) {
            //     continue;
            // }

            if (r == N - 1 && c == M - 1) {
                answer = cost;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                // 벽을 비용으로 계산
                int newDist = cost + map[nr][nc];
                if (newDist < dist[nr][nc]) {  // 다음 노드까지의 최단 거리 갱신
                    dist[nr][nc] = newDist;
                    pq.add(new Node(nr, nc, newDist));
                }
            }
        }
    }

    private static class Node {
        int r, c, cost;

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
}
