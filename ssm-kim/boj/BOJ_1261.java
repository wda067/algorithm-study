import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point {
    int x, y, weight;

    public Point(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }
}

public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] dist;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        int answer = dijkstra();
        System.out.println(answer);
    }

    static int dijkstra() {
        // 최소 비용 배열 초기화
        dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        // 최소 힙: 벽 부순 횟수가 적은 경로부터 탐색
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        pq.offer(new Point(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            // 이미 더 적은 비용으로 방문한 경우 스킵
            if (cur.weight > dist[cur.x][cur.y]) continue;

            // 목적지 도착 (최소 힙이므로 첫 도착이 최소값)
            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.weight;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 벽(1)이면 +1, 빈칸(0)이면 +0
                int nextCost = cur.weight + board[nx][ny];

                // 더 적은 비용으로 갈 수 있으면 갱신
                if (nextCost < dist[nx][ny]) {
                    dist[nx][ny] = nextCost;
                    pq.offer(new Point(nx, ny, nextCost));
                }
            }
        }
        return -1;
    }
}