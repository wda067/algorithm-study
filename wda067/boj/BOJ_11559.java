import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
백준 / Puyo Puyo / 골드4
https://www.acmicpc.net/problem/11559
 */
public class BOJ_11559 {

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static char[][] field;
    private static boolean[][] visited;
    private static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        field = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                field[i][j] = s.charAt(j);
            }
        }

        int count = 0;
        while (true) {
            visited = new boolean[12][6];
            flag = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (field[i][j] != '.' && !visited[i][j]) {
                        bfs(i, j, field[i][j]);
                    }
                }
            }

            if (!flag) {
                break;
            }

            fall();
            count++;
        }

        System.out.println(count);
    }

    private static void bfs(int r, int c, char color) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> list = new ArrayList<>();
        q.add(new int[]{r, c});
        list.add(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nextR = curR + dr[dir];
                int nextC = curC + dc[dir];

                if (nextR < 0 || nextR >= 12 || nextC < 0 || nextC >= 6) {
                    continue;
                }

                if (!visited[nextR][nextC] && field[nextR][nextC] == color) {
                    visited[nextR][nextC] = true;
                    q.add(new int[]{nextR, nextC});
                    list.add(new int[]{nextR, nextC});
                }
            }
        }

        if (list.size() >= 4) {
            flag = true;
            for (int[] pos : list) {
                field[pos[0]][pos[1]] = '.';
            }
        }
    }

    private static void fall() {
        for (int j = 0; j < 6; j++) {
            for (int i = 11; i >= 1; i--) {
                if (field[i][j] == '.') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (field[k][j] != '.') {
                            field[i][j] = field[k][j];
                            field[k][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }
}
