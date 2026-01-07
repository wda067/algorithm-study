import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {

    int x, y, broken, distance;
    boolean usedSkill;

    public Node(int x, int y, int distance, boolean usedSkill) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.usedSkill = usedSkill;
    }
}

public class Main {

    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        visited = new boolean[2][n][m];
        bfs(0, 0);
    }

    static void bfs(int sx, int sy) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sx, sy, 1, false));
        visited[0][sx][sy] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.x == n - 1 && cur.y == m - 1) {
                System.out.println(cur.distance);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (board[nx][ny] == 0) {
                    int state = cur.usedSkill ? 1 : 0;
                    if (!visited[state][nx][ny]) {
                        visited[state][nx][ny] = true;
                        queue.offer(new Node(nx, ny, cur.distance + 1, cur.usedSkill));
                    }
                }
                else if (board[nx][ny] == 1 && !cur.usedSkill && !visited[1][nx][ny]) {
                    visited[1][nx][ny] = true;
                    queue.offer(new Node(nx, ny, cur.distance + 1,true));
                }
            }
        }
        System.out.println(-1);
    }
}